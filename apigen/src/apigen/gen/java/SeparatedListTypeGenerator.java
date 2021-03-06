package apigen.gen.java;

import java.util.Iterator;

import apigen.adt.Field;
import apigen.adt.SeparatedListType;
import apigen.gen.StringConversions;

public class SeparatedListTypeGenerator extends TypeGenerator {
	private SeparatedListType type;
	private String typeName;
	private String elementTypeName;
	private String factory;
	private String elementType;

	public SeparatedListTypeGenerator(JavaGenerationParameters params,
			SeparatedListType type) {
		super(params, type);
		this.type = type;
		typeName = TypeGenerator.className(type);
		elementType = type.getElementType();
		elementTypeName = TypeGenerator.qualifiedClassName(params, elementType);
		factory = FactoryGenerator.qualifiedClassName(params, type
				.getModuleName());
	}

	protected void generate() {
		printPackageDecl();
		printImports();
		genSeparatedListTypeClass();
	}

	public String getTypeName() {
		return typeName;
	}

	public String getFactory() {
		return factory;
	}

	private void genSeparatedListTypeClass() {
		println("public class " + getTypeName()
				+ " extends aterm.pure.ATermListImpl {");

		genFactoryField();
		genSeparatorFields();
		genSeparatorsGettersAndSetters();
		genConstructor(getTypeName());
		genGetFactoryMethod();
		genTermField();
		genToTerm();
		genToString();
		genGetters();
		genPredicates();
		genSharedObjectInterface();
		genGetEmptyMethod();
		genOverrideInsertMethod();
		genReverseMethods();
		genConcatMethods();
		genAppendMethods();
		genElementAtMethod();
		println("}");
	}

	private void genElementAtMethod() {
		String elementName = StringConversions.capitalize(elementType);
		String converted = getConverter().makeATermToBuiltinConversion(
				elementType, "elementAt(index)");
		println("  public " + elementTypeName + " get" + elementName
				+ "At(int index) {");
		println("    return (" + elementTypeName + ") " + converted + ";");
		println("  }");
		println();
	}

	private void genAppendMethods() {
		genAppendMethod();
		genOverrideAppendMethod();
	}

	private void genOverrideAppendMethod() {
		println("  public aterm.ATermList append(aterm.ATerm elem) {");
		println("    " + buildUnsupportedOperationException("append", "ATerm"));
		println("  }");
		println();
	}

	private String buildUnsupportedOperationException(String operation,
			String argument) {
		StringBuffer buf = new StringBuffer();
		buf.append("throw new java.lang.UnsupportedOperationException(");
		buf.append("\"Cannot " + operation + " " + argument + ", use typed "
				+ operation + " instead.\");");
		return buf.toString();
	}

	private void genAppendMethod() {
		String typeName = getTypeName();
		String formalSeps = buildFormalSeparatorArguments(type);
		String actualSeps = buildActualSeparatorArguments(type);

		println("  public " + typeName + " append(" + formalSeps
				+ elementTypeName + " elem) {");
		println("    return " + buildFactoryGetter() + ".append" + typeName
				+ "(this, " + actualSeps + "elem);");
		println("  }");
		println();
	}

	protected void genConstructor(String className) {
		println("  public " + className + "(" + factory
				+ " factory, aterm.ATermList annos, aterm.ATerm first, "
				+ buildFormalSeparatorArguments(type)
				+ "aterm.ATermList next) {");
		println("     super(factory.getPureFactory(), annos, first, next);");
		println("     this.factory = factory;");
		genSeparatorInitAssignments(type);
		println("  }");
		println();
	}

	private void genSeparatorInitAssignments(SeparatedListType type) {
		Iterator<Field> fields = type.separatorFieldIterator();
		while (fields.hasNext()) {
			Field field = fields.next();
			String fieldId = JavaGenerator.getFieldId(field.getId());
			println("    this." + fieldId + " = " + fieldId + ";");
		}
	}

	private void genFactoryField() {
		println("  private " + factory + " factory = null;");
		println();
	}

	private void genGetEmptyMethod() {
		String className = TypeGenerator.className(type);
		println("  public aterm.ATermList getEmpty() {");
		println("    return (aterm.ATermList)" + buildFactoryGetter() + ".make"
				+ className + "();");
		println("  }");
		println();
	}

	private void genSharedObjectInterface() {
		genEquivalentMethod();
		genDuplicateMethod();
	}

	private void genGetters() {
		genGetHead();
		genGetTail();
	}

	private void genGetTail() {
		String className = TypeGenerator.className(type);

		println("  public " + className + " getTail() {");
		println("    return (" + className + ") getNext();");
		println("  }");
		println();
	}

	private void genGetHead() {
		println("  public " + elementTypeName + " getHead() {");
		String convertedValue = getConverter().makeATermToBuiltinConversion(
				elementType, "getFirst()");
		println("    return (" + elementTypeName + ")" + convertedValue + ";");
		println("  }");
		println();
	}

	private void genTermField() {
		println("  protected aterm.ATerm term = null;");
	}

	private void genToString() {
		println("  public String toString() {");
		println("    return toTerm().toString();");
		println("  }");
		println();
	}

	private void genReverseMethods() {
		genOverrideReverseMethod();
		genTypedReverseMethod();
	}

	private String buildFactoryGetter() {
		return AbstractTypeGenerator.getFactoryMethodName(
				getGenerationParameters(), type.getModuleName())
				+ "()";
	}

	private void genGetFactoryMethod() {
		println("  public " + getFactory() + " " + buildFactoryGetter() + " {");
		println("    return factory;");
		println("  }");
		println();
	}

	private void genTypedReverseMethod() {
		println("  public " + getTypeName() + " reverse" + getTypeName()
				+ "() {");
		println("    return " + buildFactoryGetter() + ".reverse(("
				+ getTypeName() + ")this);");
		println("  }");
		println();
	}

	private void genOverrideReverseMethod() {
		println("  public aterm.ATermList reverse() {");
		println("    return reverse" + getTypeName() + "();");
		println("  }");
		println();
	}

	private void genConcatMethods() {
		genOverrideConcatMethod();
		genTypedConcatMethod();
	}

	private void genTypedConcatMethod() {
		String formalSepArgs = buildFormalSeparatorArguments(type);
		String actualSepArgs = buildActualSeparatorArguments(type);

		String typeName = getTypeName();
		println("  public " + typeName + " concat(" + formalSepArgs + typeName
				+ " peer) {");
		println("    return " + buildFactoryGetter() + ".concat(this, "
				+ actualSepArgs + "peer);");
		println("  }");
		println();
	}

	private void genOverrideConcatMethod() {
		println("  public aterm.ATermList concat(aterm.ATermList peer) {");
		println("    "
				+ buildUnsupportedOperationException("concat", "ATermList"));
		println("  }");
		println();
	}

	protected void genToTerm() {
		println("  public aterm.ATerm toTerm() {");
		println("    if (term == null) {");
		println("      term = " + buildFactoryGetter() + ".toTerm(this);");
		println("    }");
		println("    return term;");
		println("  }");
		println();
	}

	protected void genOverrideInsertMethod() {
		println("  public aterm.ATermList insert(aterm.ATerm head) {");
		println("    " + buildUnsupportedOperationException("insert", "ATerm"));
		println("  }");
		println();
	}

	private String buildFormalSeparatorArguments(SeparatedListType type) {
		String result = buildFormalTypedArgumentList(type
				.separatorFieldIterator());
		// TODO: remove terminating ","!!
		if (result.length() > 0) {
			result += ", ";
		}

		return result;
	}

	private void genSeparatorsGettersAndSetters() {
		Iterator<Field> fields = type.separatorFieldIterator();
		while (fields.hasNext()) {
			Field field = fields.next();
			genSeparatorGetterAndSetter(field);
		}
	}

	private String buildSeparatorFieldGetter(Field field) {
		String fieldName = StringConversions.makeCapitalizedIdentifier(field
				.getId());
		return "get" + fieldName + "()";
	}

	private void genSeparatorGetterAndSetter(Field field) {
		String fieldName = StringConversions.makeCapitalizedIdentifier(field
				.getId());
		String fieldClass = TypeGenerator.qualifiedClassName(
				getJavaGenerationParameters(), field.getType());
		String fieldId = JavaGenerator.getFieldId(field.getId());
		String fieldGetter = buildSeparatorFieldGetter(field);

		// TODO: find a way to reuse generation of getters in
		// AlternativeGenerator for lists of builtins

		println("  public " + fieldClass + " " + fieldGetter + " {");
		println("    if (!isEmpty() && !isSingle()) {");
		println("      return " + fieldId + ";");
		println("    }");
		println("    throw new UnsupportedOperationException(\"This "
				+ getClassName() + " does not have a " + field.getId()
				+ ":\" + this);");
		println("  }");
		println();

		println("  public " + getClassName() + " set" + fieldName + "("
				+ fieldClass + " arg) {");
		println("    if (!isEmpty() && !isSingle()) {");
		String arglist = buildActualSeparatorArguments(type);
		arglist = arglist.replaceAll(fieldId, "arg");
		println("      return " + buildFactoryGetter() + ".make"
				+ getClassName() + "(getHead(), " + arglist + "getTail());");
		println("    }");
		println("    throw new RuntimeException(\"This " + getClassName()
				+ " does not have a " + fieldId + ".\");");
		println("  }");
		println();
	}

	protected void genPredicates() {
		genIsEmpty(TypeGenerator.className(type));
		genIsMany();
		genIsSingle();
	}

	private void genIsMany() {
		println("  public boolean isMany() {");
		println("    return !isEmpty();");
		println("  }");
		println();
	}

	private void genIsEmpty(String className) {
		println("  public boolean isEmpty() {");
		println("    return this == " + buildFactoryGetter() + ".make"
				+ className + "();");
		println("  }");
		println();
	}

	private void genIsSingle() {
		println("  public boolean isSingle() {");
		println("    return !isEmpty() && getNext().isEmpty();");
		println("  }");
		println();
	}

	private void genSeparatorFields() {
		Iterator<Field> fields = type.separatorFieldIterator();

		while (fields.hasNext()) {
			Field field = fields.next();
			genSeparatorField(field);
		}
		println();
	}

	protected void genEquivalentMethod() {
		println("  public boolean equivalent(shared.SharedObject object) {");
		println("    if (object instanceof " + getClassName() + ") {");
		println("      " + getClassName() + " peer = (" + getClassName()
				+ ") object;");
		println("      if (isEmpty() || isSingle()) {");
		println("        return super.equivalent(peer); ");
		println("      }");
		print("      return super.equivalent(peer) ");
		genSeparatorFieldsEquivalentConjunction();
		println(";");
		println("    }");
		println("    else {");
		println("      return false;");
		println("    }");
		println("  }");
		println();

	}

	protected void genDuplicateMethod() {
		println("  public shared.SharedObject duplicate() {");
		println("    return this;");
		println("  }");
		println();
	}

	private String buildActualSeparatorArguments(SeparatedListType type) {
		Iterator<Field> fields = type.separatorFieldIterator();
		String result = "";

		while (fields.hasNext()) {
			Field field = fields.next();
			String fieldId = JavaGenerator.getFieldId(field.getId());
			// TODO: remove terminating ", "!
			result += fieldId + ", ";
		}

		return result;
	}

	private void genSeparatorFieldsEquivalentConjunction() {
		Iterator<Field> fields = type.separatorFieldIterator();
		while (fields.hasNext()) {
			Field field = fields.next();
			String fieldId = JavaGenerator.getFieldId(field.getId());
			String fieldType = field.getType();
			String fieldGetter = buildSeparatorFieldGetter(field);
			String equivalenceTest = EquivalentBuilder.buildEquivalent(
					fieldType, fieldId, "peer." + fieldGetter);
			print(" && " + equivalenceTest);
		}
	}

	private void genSeparatorField(Field field) {
		String fieldClass = TypeGenerator.qualifiedClassName(
				getJavaGenerationParameters(), field.getType());
		String fieldId = JavaGenerator.getFieldId(field.getId());
		println("  private " + fieldClass + " " + fieldId + ";");
	}

}
