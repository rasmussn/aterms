package apigen.gen.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import apigen.adt.ADT;
import apigen.adt.Alternative;
import apigen.adt.Field;
import apigen.adt.ListType;
import apigen.adt.NormalListType;
import apigen.adt.SeparatedListType;
import apigen.adt.Type;
import apigen.adt.api.Separators;
import apigen.gen.StringConversions;
import aterm.ATermFactory;
import aterm.ATermList;

public class FactoryGenerator extends JavaGenerator {
    private String className;
    private ADT adt;
    private String apiName;

    public FactoryGenerator(
        ADT adt,
        String directory,
        String apiName,
        String pkg,
        List standardImports,
        boolean verbose,
        boolean folding) {
        super(directory, className(apiName), pkg, standardImports, verbose);
        this.className = className(apiName);
        this.adt = adt;
        this.apiName = apiName;
    }

    public static String className(String apiName) {
        return StringConversions.makeCapitalizedIdentifier(apiName) + "Factory";
    }

    protected void generate() {
        printPackageDecl();

        imports.add("aterm.pure.PureFactory");
        printImports();

        genFactoryClass(adt);
    }

    private void genFactoryClass(ADT api) {
        println("public class " + className + " extends PureFactory");
        println("{");
        genFactoryPrivateMembers(api);
        genFactoryEmptyLists(api);
        genFactoryConstructor(api);
        genFactoryConstructorWithSize(api);
        genFactoryInitialize(api);
        genAlternativeMethods(api);
        genFactoryMakeLists(api);
        genTypeFromTermMethods(api);
        genTypeFromMethods(api);
        println("}");
    }

    private void genTypeFromTermMethods(ADT api) {
        Iterator types = api.typeIterator();

        while (types.hasNext()) {
            Type type = (Type) types.next();

            if (type instanceof NormalListType) {
                genListTypeFromTermMethod((NormalListType) type);
            } else if (type instanceof SeparatedListType) {
                genSeparatedListTypeFromTermMethod((SeparatedListType) type);
            } else {
                genTypeFromTermMethod(type);
            }
        }
    }

    private void genTypeFromMethods(ADT api) {
        Iterator types = api.typeIterator();

        while (types.hasNext()) {
            Type type = (Type) types.next();

            genTypeFromStringMethod(type);
            genTypeFromFileMethod(type);
        }
    }

    private void genFactoryInitialize(ADT api) {
        println("  private void initialize()");
        println("  {");
        genFactoryInitialization(api);
        println("  }");
    }

    private void genFactoryConstructorWithSize(ADT api) {
        println("  public " + className + "(int logSize)");
        println("  {");
        println("     super(logSize);");
        genFactoryConstructorBody(api);
        println("  }");
    }

    private void genFactoryConstructorBody(ADT api) {
        println("     initialize();");
    }

    private void genFactoryConstructor(ADT api) {
        println("  public " + className + "()");
        println("  {");
        println("     super();");
        genFactoryConstructorBody(api);
        println("  }");
    }

    private void genFactoryEmptyLists(ADT api) {
        Iterator types = api.typeIterator();

        while (types.hasNext()) {
            Type type = (Type) types.next();
            if (type instanceof ListType) {
                String className = TypeGenerator.className(type);
                println("  static protected " + className + " empty" + className + ";");
            }
        }

    }

    private void genFactoryMakeLists(ADT api) {
        Iterator types = api.typeIterator();

        while (types.hasNext()) {
            Type type = (Type) types.next();
            if (type instanceof ListType) {
                String className = TypeGenerator.className(type);
                String elementClassName =
                    TypeGenerator.className(((ListType) type).getElementType());

                String empty = "empty" + className;

                if (type instanceof NormalListType) {
                    genFactoryMakeEmptyList(className, empty);
                    genFactoryMakeSingletonList(className, elementClassName, empty);
                    genFactoryMakeManyList(className, elementClassName);
                    genFactoryMakeManyTermList(className);
                } else if (type instanceof SeparatedListType) {
                    SeparatedListType lType = (SeparatedListType) type;
                    genFactoryMakeEmptyList(className, empty);
                    genFactoryMakeSingletonSeparatedList(
                        lType,
                        className,
                        elementClassName,
                        empty);
                    genFactoryMakeManySeparatedList(lType, className, elementClassName);
                    genFactoryMakeManySeparatedTermList(lType, className);
                    genFactoryReverseSeparated(lType, className, elementClassName);
                }
            }
        }
    }

    private void genFactoryReverseSeparated(
        SeparatedListType type,
        String className,
        String elementClassName) {
        String classNameImpl = TypeImplGenerator.className(type);
        String makeClassName = "make" + className;

        println("  public " + className + " reverse(" + className + " arg) {");
        println("    if (arg.isEmpty() || arg.isSingle()) {");
        println("         return arg;");
        println("    }");
        println();
        println("    int length = arg.getLength();");
        println("    " + className + "[] nodes = new " + className + "[length];");
        println();
        println("    for (int i = 0; i < length; i++) {");
        println("      nodes[i] = arg;");
        println("      arg = arg.getTail();");
        println("    }");
        println();
        println("    " + className + " result = " + makeClassName + "(nodes[0].getHead());");
        println();
        println("    for (int i = 1; i < length; i++) {");
        println("      Module _head = nodes[i].getHead();");

        Iterator separators = type.separatorFieldIterator();
        while (separators.hasNext()) {
            Field separator = (Field) separators.next();
            String fieldId = separator.getId();
            String fieldName = AlternativeImplGenerator.getFieldId(fieldId);
            String fieldType = TypeGenerator.className(separator.getType());
            String capitalizedFieldId =
              StringConversions.makeCapitalizedIdentifier(fieldId);
            String fieldGetter = "get" + capitalizedFieldId + "()";
            println("    " + fieldType + " " + fieldName + " = nodes[i-1]." + fieldGetter + ";");
        }

        String seps = buildOptionalSeparatorArguments(type);
        println(
            "        result = make" + className + "(_head, " + seps + "result);");
        println("    }");
        println();
        println("    return result;");
        println("  }");
    }

    private void genFactoryMakeManyTermList(String className) {
        println(
            "  protected "
                + className
                + " make"
                + className
                + "(aterm.ATerm head, aterm.ATermList tail, aterm.ATermList annos) {");
        println("    synchronized (proto" + className + ") {");
        println("      proto" + className + ".initHashCode(annos,head,tail);");
        println("      return (" + className + ") build(proto" + className + ");");
        println("    }");
        println("  }");
    }

    private void genFactoryMakeManyList(String className, String elementClassName) {
        println(
            "  public "
                + className
                + " make"
                + className
                + "("
                + elementClassName
                + " head, "
                + className
                + " tail) {");
        println(
            "    return ("
                + className
                + ") make"
                + className
                + "((aterm.ATerm) head, (aterm.ATermList) tail, empty);");
        println("  }");
    }

    private void genFactoryMakeManySeparatedList(
        SeparatedListType type,
        String className,
        String elementClassName) {
        String formalSeps = buildFormalTypedArgumentList(type.separatorFieldIterator());
        String actualSeps =
            buildActualTypedAltArgumentList(type.separatorFieldIterator());
        if (formalSeps.length() > 0) {
            formalSeps += ", ";
            actualSeps += ", ";
        }

        println(
            "  public "
                + className
                + " make"
                + className
                + "("
                + elementClassName
                + " head, "
                + formalSeps
                + className
                + " tail) {");
        println(
            "    return ("
                + className
                + ") make"
                + className
                + "((aterm.ATerm) head, "
                + actualSeps
                + "(aterm.ATermList) tail, empty);");
        println("  }");
    }

    private void genFactoryMakeManySeparatedTermList(
        SeparatedListType type,
        String className) {
        String formalSeps = buildFormalTypedArgumentList(type.separatorFieldIterator());
        String actualSeps =
            buildActualTypedAltArgumentList(type.separatorFieldIterator());
        if (formalSeps.length() > 0) {
            formalSeps += ", ";
            actualSeps += ", ";
        }

        println(
            "  protected "
                + className
                + " make"
                + className
                + "(aterm.ATerm head, "
                + formalSeps
                + "aterm.ATermList tail, aterm.ATermList annos) {");
        println("    synchronized (proto" + className + ") {");
        println(
            "      proto"
                + className
                + ".initHashCode(annos, head, "
                + actualSeps
                + "tail);");
        println("      return (" + className + ") build(proto" + className + ");");
        println("    }");
        println("  }");
    }

    private void genFactoryMakeSingletonList(
        String className,
        String elementClassName,
        String empty) {

        println(
            "  public "
                + className
                + " make"
                + className
                + "("
                + elementClassName
                + " elem ) {");
        println(
            "    return (" + className + ") make" + className + "(elem, " + empty + ");");
        println("  }");
    }

    private void genFactoryMakeSingletonSeparatedList(
        SeparatedListType type,
        String className,
        String elementClassName,
        String empty) {
        String separators = buildActualNullArgumentList(type.separatorFieldIterator());
        if (separators.length() > 0) {
            separators += ", ";
        }

        println(
            "  public "
                + className
                + " make"
                + className
                + "("
                + elementClassName
                + " elem ) {");
        println(
            "    return ("
                + className
                + ") make"
                + className
                + "(elem, "
                + separators
                + empty
                + ");");
        println("  }");
    }
    private void genFactoryMakeEmptyList(String className, String empty) {
        println("  public " + className + " make" + className + "() {");
        println("    return " + empty + ";");
        println("  }");
    }

    private void genFactoryPrivateMembers(ADT api) {

        Iterator types = api.typeIterator();
        while (types.hasNext()) {
            Type type = (Type) types.next();
            String typeClassName = TypeGenerator.className(type.getId());
            Iterator alts = type.alternativeIterator();

            if (type instanceof ListType) {
                String protoVar = "proto" + typeClassName;
                println("  private " + typeClassName + " " + protoVar + ";");
                println("  private aterm.ATerm pattern" + typeClassName + "Many;");
            } else {
                while (alts.hasNext()) {
                    Alternative alt = (Alternative) alts.next();
                    String altClassName = AlternativeGenerator.className(type, alt);

                    String protoVar = "proto" + altClassName;
                    String funVar = "fun" + altClassName;

                    println("  private aterm.AFun " + funVar + ";");
                    println("  private " + typeClassName + " " + protoVar + ";");
                    println("  private aterm.ATerm pattern" + altClassName + ";");
                }
            }
        }
    }

    private void genAlternativeMethods(ADT api) {
        Iterator types = api.typeIterator();
        while (types.hasNext()) {
            Type type = (Type) types.next();

            if (type instanceof ListType) {
                if (type instanceof SeparatedListType) {
                    genSeparatedListToTerm((SeparatedListType) type);
                }
            } else {
                Iterator alts = type.alternativeIterator();
                while (alts.hasNext()) {
                    Alternative alt = (Alternative) alts.next();

                    genInternalMakeMethod(type, alt);
                    genMakeMethod(type, alt);
                    genAltFromTerm(type, alt);
                    genAltToTerm(type, alt);
                }
            }
        }
    }

    private void genMakeMethod(Type type, Alternative alt) {
        String altClassName = AlternativeGenerator.className(type, alt);

        String funVar = "fun" + altClassName;
        print("  public " + altClassName + " make" + altClassName + "(");
        printFormalTypedAltArgumentList(type, alt);
        println(") {");
        print("    aterm.ATerm[] args = new aterm.ATerm[] {");
        printActualTypedArgumentList(type, alt);
        println("};");
        println("    return make" + altClassName + "( " + funVar + ", args, empty);");
        println("  }");
        println();
    }

    private void genInternalMakeMethod(Type type, Alternative alt) {
        String altClassName = AlternativeGenerator.className(type, alt);
        String protoVar = "proto" + altClassName;

        println(
            "  protected "
                + altClassName
                + " make"
                + altClassName
                + "(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {");
        println("    synchronized (" + protoVar + ") {");
        println("      " + protoVar + ".initHashCode(annos,fun,args);");
        println("      return (" + altClassName + ") build(" + protoVar + ");");
        println("    }");
        println("  }");
        println();
    }

    private String patternVariable(String className) {
        return "pattern" + className;
    }

    private void genAltToTerm(Type type, Alternative alt) {
        String className = AlternativeGenerator.className(type, alt);
        String classImplName = AlternativeImplGenerator.className(type, alt);

        println("  protected aterm.ATerm toTerm(" + classImplName + " arg) {");
        println("    java.util.List args = new java.util.LinkedList();");

        Iterator fields = type.altFieldIterator(alt.getId());
        print(buildAddFieldsToListCalls(fields));
        println("    return make(" + patternVariable(className) + ", args);");
        println("  }");
        println();
    }

    private String buildAddFieldsToListCalls(Iterator fields) {
        String result = "";

        for (int i = 0; fields.hasNext(); i++) {
            Field field = (Field) fields.next();
            String field_type = field.getType();
            String getArgumentCall =
                "arg.get"
                    + StringConversions.makeCapitalizedIdentifier(field.getId())
                    + "()";

            if (field_type.equals("str")) {
                result += "    args.add(" + getArgumentCall + ");";
            } else if (field_type.equals("int")) {
                result += "    args.add(new Integer(" + getArgumentCall + "));";
            } else if (field_type.equals("real")) {
                result += "    args.add(new Double (" + getArgumentCall + "));";
            } else if (field_type.equals("term")) {
                result += "    args.add((aterm.ATerm)" + getArgumentCall + ");";
            } else if (field_type.equals("list")) {
                result += "    args.add((aterm.ATermList)" + getArgumentCall + ");";
            } else {
                result += "    args.add((" + getArgumentCall + ").toTerm());";
            }
        }

        return result;
    }

    private void genFactoryInitialization(ADT api) {
        Iterator types = api.typeIterator();
        int listTypesCount = 0;

        while (types.hasNext()) {
            Type type = (Type) types.next();

            if (type instanceof NormalListType) {
                genNormalListTypeInitialization(listTypesCount, type);
                listTypesCount++;
            } else if (type instanceof SeparatedListType) {
                genSeparatedListTypeInitialization(
                    listTypesCount,
                    (SeparatedListType) type);
                listTypesCount++;
            } else {
                Iterator alts = type.alternativeIterator();
                while (alts.hasNext()) {
                    Alternative alt = (Alternative) alts.next();
                    String altClassName = AlternativeGenerator.className(type, alt);
                    String protoVar = "proto" + altClassName;
                    String funVar = "fun" + altClassName;
                    String afunName = type.getId() + "_" + alt.getId();

                    println();
                    println(
                        "    "
                            + patternVariable(altClassName)
                            + " = parse(\""
                            + StringConversions.escapeQuotes(
                                alt.buildMatchPattern().toString())
                            + "\");");
                    println(
                        "    "
                            + funVar
                            + " = makeAFun(\""
                            + "_"
                            + afunName
                            + "\", "
                            + type.getAltArity(alt)
                            + ", false);");
                    println("    " + protoVar + " = new " + altClassName + "(this);");
                }
            }
            println();
        }

        Iterator bottoms = api.bottomTypeIterator();

        while (bottoms.hasNext()) {
            String type = (String) bottoms.next();

            if (!converter.isReserved(type)) {
                println(
                    "    "
                        + StringConversions.makeCapitalizedIdentifier(type)
                        + ".initialize(this);");
            }
        }
    }

    private void genNormalListTypeInitialization(int listTypesCount, Type type) {
        String className = TypeGenerator.className(type);
        genInitializedPrototype(className);
        genInitializeEmptyList(listTypesCount, className);
    }

    private void genSeparatedListTypeInitialization(
        int listTypesCount,
        SeparatedListType type) {
        String className = TypeGenerator.className(type);
        genInitializedPrototype(className);
        genInitializeEmptySeparatedList(type, listTypesCount, className);
        genInitializeManyPattern(type, className);
    }

    private void genInitializeManyPattern(SeparatedListType type, String className2) {
        Alternative alt = type.getManyAlternative();

        println(
            "    pattern"
                + TypeGenerator.className(type)
                + "Many"
                + " = parse(\""
                + StringConversions.escapeQuotes(alt.buildMatchPattern().toString())
                + "\");");
    }

    private void genInitializeEmptySeparatedList(
        SeparatedListType type,
        int listTypesCount,
        String className) {
        String emptyHashCode = buildInitialEmptyListHashcode(listTypesCount).toString();
        println(
            "    proto"
                + className
                + ".init("
                + emptyHashCode
                + ", null, null, "
                + buildAmountOfSeparatorsNullExpressions(type)
                + "null);");
        println(
            "    empty"
                + className
                + " = ("
                + className
                + ") build(proto"
                + className
                + ");");
        println(
            "    empty"
                + className
                + ".init("
                + emptyHashCode
                + ", empty"
                + className
                + ", null, "
                + buildAmountOfSeparatorsNullExpressions(type)
                + "null);");
    }

    private String buildAmountOfSeparatorsNullExpressions(SeparatedListType type) {
        String result = "";
        Iterator fields = type.separatorFieldIterator();
        while (fields.hasNext()) {
            Field field = (Field) fields.next();
            result += "null, ";
        }
        return result;
    }

    private void genInitializeEmptyList(int listTypesCount, String className) {
        String emptyHashCode = buildInitialEmptyListHashcode(listTypesCount).toString();
        println(
            "    proto" + className + ".init(" + emptyHashCode + ", null, null, null);");
        println(
            "    empty"
                + className
                + " = ("
                + className
                + ") build(proto"
                + className
                + ");");
        println(
            "    empty"
                + className
                + ".init("
                + emptyHashCode
                + ", empty"
                + className
                + ", null, null);");
    }

    private void genInitializedPrototype(String className) {
        println("    proto" + className + " = new " + className + "(this);");
    }

    private Integer buildInitialEmptyListHashcode(int listTypesCount) {
        return new Integer(42 * (2 + listTypesCount));
    }

    private void genAltFromTerm(Type type, Alternative alt) {
        String subClassName = AlternativeGenerator.className(type, alt);
        String superClassName = TypeGenerator.className(type);
        Iterator fields;
        int argnr;

        println(
            "  public "
                + superClassName
                + " "
                + subClassName
                + "FromTerm(aterm.ATerm trm)");
        println("  {");
        println(
            "    java.util.List children = trm.match("
                + patternVariable(AlternativeGenerator.className(type, alt))
                + ");");
        println();
        println("    if (children != null) {");
        print("      " + superClassName + " tmp = make" + subClassName + "(");

        fields = type.altFieldIterator(alt.getId());
        argnr = 0;
        while (fields.hasNext()) {
            Field field = (Field) fields.next();
            String fieldType;
            String fieldClass;
            print(buildFieldMatchResultRetriever(argnr, field));

            if (fields.hasNext()) {
                print(", ");
            }
            argnr++;
        }
        println(");");
        println("      return tmp;");
        println("    }"); // endif      
        println("    else {");
        println("      return null;");
        println("    }");
        println("  }");
    }

    private String buildFieldMatchResultRetriever(int argnr, Field field) {
        String fieldType = field.getType();
        String fieldClass = TypeGenerator.className(fieldType);
        String result = "";

        if (fieldType.equals("str")) {
            result = "(String) children.get(" + argnr + ")";
        } else if (fieldType.equals("int")) {
            result = "((Integer) children.get(" + argnr + ")).intValue()";
        } else if (fieldType.equals("real")) {
            result = "((Double) children.get(" + argnr + ")).doubleValue()";
        } else if (fieldType.equals("term")) {
            result = "(aterm.ATerm) children.get(" + argnr + ")";
        } else if (fieldType.equals("list")) {
            result = "(aterm.ATermList) children.get(" + argnr + ")";
        } else {
            result = fieldClass + "FromTerm( (aterm.ATerm) children.get(" + argnr + "))";
        }

        return result;
    }

    private void genTypeFromTermMethod(Type type) {
        String class_name = TypeGenerator.className(type);

        println(
            "  public " + class_name + " " + class_name + "FromTerm(aterm.ATerm trm)");
        println("  {");
        println("    " + class_name + " tmp;");
        genAltFromTermCalls(type);
        println();
        println(
            "    throw new RuntimeException(\"This is not a "
                + class_name
                + ": \" + trm);");
        println("  }");
    }

    protected void genAltFromTermCalls(Type type) {
        Iterator alts = type.alternativeIterator();
        while (alts.hasNext()) {
            Alternative alt = (Alternative) alts.next();
            String alt_class_name = AlternativeGenerator.className(type, alt);
            println("    tmp = " + alt_class_name + "FromTerm(trm);");
            println("    if (tmp != null) {");
            println("      return tmp;");
            println("    }");
            println();
        }
    }

    protected void genTypeFromStringMethod(Type type) {
        String className = TypeGenerator.className(type);

        println("  public " + className + " " + className + "FromString(String str)");
        println("  {");
        println("    aterm.ATerm trm = parse(str);");
        println("    return " + className + "FromTerm(trm);");
        println("  }");
    }

    protected void genTypeFromFileMethod(Type type) {
        String className = TypeGenerator.className(type);

        println(
            "  public "
                + className
                + " "
                + className
                + "FromFile(java.io.InputStream stream) throws java.io.IOException {");
        println("    return " + className + "FromTerm(readFromFile(stream));");
        println("  }");
    }

    private void genListTypeFromTermMethod(NormalListType type) {
        String className = ListTypeGenerator.className(type);
        String elementType = type.getElementType();
        String elementTypeName = TypeGenerator.className(elementType);

        if (converter.isReserved(elementType)) {
            // TODO: implement lists of builtins
            throw new RuntimeException(
                "List of " + elementType + " not yet implemented, sorry!");
        }

        println("  public " + className + " " + className + "FromTerm(aterm.ATerm trm)");
        println("  {");
        println("     if (trm instanceof aterm.ATermList) {");
        println("        aterm.ATermList list = ((aterm.ATermList) trm).reverse();");
        println("        " + className + " result = make" + className + "();");
        println("        for (; !list.isEmpty(); list = list.getNext()) {");
        println(
            "          "
                + elementTypeName
                + " elem = "
                + elementTypeName
                + "FromTerm(list.getFirst());");
        println("           if (elem != null) {");
        println("             result = make" + className + "(elem, result);");
        println("           }");
        println("           else {");
        println(
            "             throw new RuntimeException(\"Invalid element in "
                + className
                + ": \" + elem);");
        println("           }");
        println("        }");
        println("        return result;");
        println("     }");
        println("     else {");
        println(
            "       throw new RuntimeException(\"This is not a "
                + className
                + ": \" + trm);");
        println("     }");
        println("  }");
    }

    private String buildGetNextElementCall(SeparatedListType type) {
        Separators separators = type.getSeparators();
        String result = "getNext()";

        for (; !separators.isEmpty(); separators = separators.getTail()) {
            result += ".getNext()";
        }

        return result;
    }

    private void genSeparatedListTypeFromTermMethod(SeparatedListType type) {
        String className = ListTypeGenerator.className(type);
        String makeClassName = "make" + className;
        String manyPattern = "pattern" + className + "Many";
        String elementClass = TypeGenerator.className(type.getElementType());
        String elementClassFromTerm = elementClass + "FromTerm";
        Separators separators = type.getSeparators();
        int headLength = 1 + separators.getLength(); // on the ATerm level
        int tailIndex = 1 + type.countSeparatorFields(); // on the abstract level

        println("public " + className + " " + className + "FromTerm(aterm.ATerm trm)");
        println("  {");
        println("    if (!(trm instanceof aterm.ATermList)) {");
        println(
            "       throw new IllegalArgumentException(\"This is not a "
                + className
                + ": \" + trm);");
        println("     }");
        println();
        println("    aterm.ATermList list = (aterm.ATermList) trm;");
        println("     if (list.isEmpty()) {");
        println("       return " + makeClassName + "();");
        println("     }");
        println();
        println("     if (list.getLength() == 1) {");
        println(
            "      return "
                + makeClassName
                + "("
                + elementClassFromTerm
                + "(list.getFirst()));");
        println("     }");
        println();
        println("     int length = (list.getLength() / " + headLength + ") + 1;");
        println("     java.util.List[] nodes = new java.util.List[length-1];");
        println();
        println("    for (int i = 0; i < length - 1; i++) {");
        println("        java.util.List args = list.match(" + manyPattern + ");");
        println("         if (args == null) {");
        println(
            "             throw new IllegalArgumentException(\"This is not a "
                + className
                + ": \" + trm);");
        println("         }");
        println("         nodes[i] = args;");
        println("        list = (aterm.ATermList) args.get(" + tailIndex + ");");
        println("     }");
        println();
        println("     if (list.getLength() != 1) {");
        println(
            "         throw new IllegalArgumentException(\"This is not a "
                + className
                + ": \" + trm);");
        println("    }");
        println();
        println(
            "    "
                + className
                + " result = "
                + makeClassName
                + "("
                + elementClassFromTerm
                + "(list.getFirst()));");
        println();
        println("   for (int i = length - 2; i >= 0; i--) {");
        println("       java.util.List children = nodes[i];");
        println(
            "       Module head = "
                + elementClassFromTerm
                + "((aterm.ATerm) children.get(0));");

        genFromTermSeparatorFieldAssigments(type);

        String separatorArgs = buildOptionalSeparatorArguments(type);

        println(
            "       result = " + makeClassName + "(head, " + separatorArgs + "result);");
        println("   }");
        println();
        println("   return result;");
        println(" }");
    }

    private String buildOptionalSeparatorArguments(SeparatedListType type) {
        Iterator separatorFields = type.separatorFieldIterator();
        String separatorArgs = buildActualTypedAltArgumentList(separatorFields);
        if (separatorArgs.length() > 0) {
            separatorArgs += ", ";
        }
        return separatorArgs;
    }

    private void genSeparatedListToTerm(SeparatedListType type) {
        String className = TypeGenerator.className(type);
        String classImplName = TypeImplGenerator.className(type);
        String manyPattern = "pattern" + className + "Many";

        println("  public aterm.ATerm toTerm(" + classImplName + " arg) {");
        println("   if (arg.isEmpty()) {");
        println("      return getEmpty();");
        println("    }");
        println();
        println("    if (arg.isSingle()) {");
        println("      return makeList(arg.getHead().toTerm());");
        println("    }");
        println();
        println("    int length = arg.getLength();");
        println("    " + classImplName + "[] nodes = new " + classImplName + "[length];");
        println();
        println("    for (int i = 0; i < length; i++) {");
        println("        nodes[length-i-1] = arg;");
        println("        arg = arg.getTail();");
        println("    }");
        println();
        println("    aterm.ATermList result = makeList(nodes[0].getHead().toTerm());");
        println("    for (int i = 1; i < length; i++) {");
        println("      java.util.List args = new java.util.LinkedList();");
        println("      args.add(nodes[i].getHead().toTerm());");

        Iterator separators = type.separatorFieldIterator();
        while (separators.hasNext()) {
            Field separator = (Field) separators.next();
            String fieldId = separator.getId();
            String capitalizedFieldId =
                StringConversions.makeCapitalizedIdentifier(fieldId);
            String fieldGetter = "get" + capitalizedFieldId + "()";
            println("      args.add(nodes[i]." + fieldGetter + ".toTerm());");
        }

        println("      args.add(result);");
        println("      result = (aterm.ATermList) " + manyPattern + ".make(args);");
        println("    }");
        println();
        println("    return result;");
        println("  }");
    }

    private void genFromTermSeparatorHeadAssignment(SeparatedListType type) {
        println(
            "            head = "
                + buildFieldMatchResultRetriever(0, type.getManyField("head"))
                + ";");
    }

    private void genFromTermSeparatorTailAssignment(int tailIndex) {
        println(
            "              list = (aterm.ATermList) children.get(" + tailIndex + ");");
    }

    private int genFromTermSeparatorFieldAssigments(SeparatedListType type) {
        Iterator fields = type.separatorFieldIterator();
        int i;
        for (i = 1; fields.hasNext(); i++) {
            Field field = (Field) fields.next();
            String fieldId = AlternativeImplGenerator.getFieldId(field.getId());
            String fieldType = TypeGenerator.className(field.getType());
            println(
                "        "
                    + fieldType
                    + " "
                    + fieldId
                    + " = "
                    + buildFieldMatchResultRetriever(i, field)
                    + ";");
        }
        return i;
    }
}
