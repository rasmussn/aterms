/* This C code is generated by the AsfSdfCompiler version 1.3 */

#include  "asc-support.h"
static Symbol lf_AUX_Build_General_AsFix2ME_ADT4_1sym ;
static ATerm lf_AUX_Build_General_AsFix2ME_ADT4_1 ( ATerm arg1 , ATerm arg2 , ATerm arg3 ) ;
static Symbol ef9sym ;
static funcptr ef9 ;
static Symbol ef10sym ;
static funcptr ef10 ;
static Symbol ef1sym ;
static funcptr ef1 ;
static Symbol lf2sym ;
static ATerm lf2 ( ATerm arg1 ) ;
static Symbol ef11sym ;
static funcptr ef11 ;
static Symbol ef4sym ;
static funcptr ef4 ;
static Symbol ef5sym ;
static funcptr ef5 ;
static Symbol ef2sym ;
static funcptr ef2 ;
static Symbol lf3sym ;
static ATerm lf3 ( ATerm arg1 ) ;
static Symbol ef3sym ;
static funcptr ef3 ;
static Symbol lf4sym ;
static ATerm lf4 ( ATerm arg1 ) ;
static Symbol ef6sym ;
static funcptr ef6 ;
static Symbol ef7sym ;
static funcptr ef7 ;
static Symbol ef8sym ;
static funcptr ef8 ;
void register_AUX_Build_General_AsFix2ME_ADT4 ( ) {
lf_AUX_Build_General_AsFix2ME_ADT4_1sym = ATmakeSymbol ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-attrs\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"ModuleName\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"Attributes\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"ConsOption\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)"
 , 3 , ATtrue ) ;
ATprotectSymbol ( lf_AUX_Build_General_AsFix2ME_ADT4_1sym ) ;
lf2sym = ATmakeSymbol ( "listtype(sort(\"Attribute\"),ql(\",\"))" , 1 , ATtrue ) ;
ATprotectSymbol ( lf2sym ) ;
lf3sym = ATmakeSymbol ( "listtype(sort(\"CHAR\"))" , 1 , ATtrue ) ;
ATprotectSymbol ( lf3sym ) ;
lf4sym = ATmakeSymbol ( "listtype(sort(\"ATerm\"),ql(\",\"))" , 1 , ATtrue ) ;
ATprotectSymbol ( lf4sym ) ;
register_prod ( ATparse ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-attrs\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"ModuleName\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"Attributes\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"ConsOption\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)" ) , lf_AUX_Build_General_AsFix2ME_ADT4_1 , lf_AUX_Build_General_AsFix2ME_ADT4_1sym ) ;
register_prod ( ATparse ( "listtype(sort(\"Attribute\"),ql(\",\"))" ) , lf2 , lf2sym ) ;
register_prod ( ATparse ( "listtype(sort(\"CHAR\"))" ) , lf3 , lf3sym ) ;
register_prod ( ATparse ( "listtype(sort(\"ATerm\"),ql(\",\"))" ) , lf4 , lf4sym ) ;
}
void resolve_AUX_Build_General_AsFix2ME_ADT4 ( ) {
ef1 = lookup_func ( ATreadFromString ( "prod(id(\"Kernel-Sdf-Syntax\"),w(\"\"),[ql(\"{\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"Attribute\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\")),w(\"\"),ql(\"}\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Attributes\"),w(\"\"),no-attrs)" ) ) ;
ef1sym = lookup_sym ( ATreadFromString ( "prod(id(\"Kernel-Sdf-Syntax\"),w(\"\"),[ql(\"{\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"Attribute\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\")),w(\"\"),ql(\"}\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Attributes\"),w(\"\"),no-attrs)" ) ) ;
ef2 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef2sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef3 = lookup_func ( ATreadFromString ( "prod(id(\"GEN-LexConsFuncs\"),w(\"\"),[ql(\"afun\"),w(\"\"),ql(\"(\"),w(\"\"),iter(sort(\"CHAR\"),w(\"\"),l(\"*\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),no-attrs)" ) ) ;
ef3sym = lookup_sym ( ATreadFromString ( "prod(id(\"GEN-LexConsFuncs\"),w(\"\"),[ql(\"afun\"),w(\"\"),ql(\"(\"),w(\"\"),iter(sort(\"CHAR\"),w(\"\"),l(\"*\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),no-attrs)" ) ) ;
ef4 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"ATermList\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef4sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"ATermList\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef5 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[ql(\"[\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\")),w(\"\"),ql(\"]\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATermList\"),w(\"\"),no-attrs)" ) ) ;
ef5sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[ql(\"[\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\")),w(\"\"),ql(\"]\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATermList\"),w(\"\"),no-attrs)" ) ) ;
ef6 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef6sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef7 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AT-Literal\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef7sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AT-Literal\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef8 = lookup_func ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"modname-to-lit\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"ModuleName\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AT-Literal\"),w(\"\"),no-attrs)" ) ) ;
ef8sym = lookup_sym ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"modname-to-lit\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"ModuleName\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AT-Literal\"),w(\"\"),no-attrs)" ) ) ;
ef9 = lookup_func ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-attr-list\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"Attribute\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\",\"),w(\"\"),sort(\"ConsOption\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerms\"),w(\"\"),no-attrs)" ) ) ;
ef9sym = lookup_sym ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-attr-list\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"Attribute\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\",\"),w(\"\"),sort(\"ConsOption\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerms\"),w(\"\"),no-attrs)" ) ) ;
ef10 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\"))],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerms\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"avoid\")],w(\"\"),l(\"}\")))" ) ) ;
ef10sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"*\"))],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerms\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"avoid\")],w(\"\"),l(\"}\")))" ) ) ;
ef11 = lookup_func ( ATreadFromString ( "prod(id(\"Kernel-Sdf-Syntax\"),w(\"\"),[],w(\"\"),l(\"->\"),w(\"\"),sort(\"Attributes\"),w(\"\"),no-attrs)" ) ) ;
ef11sym = lookup_sym ( ATreadFromString ( "prod(id(\"Kernel-Sdf-Syntax\"),w(\"\"),[],w(\"\"),l(\"->\"),w(\"\"),sort(\"Attributes\"),w(\"\"),no-attrs)" ) ) ;
}
static ATerm constant0 = NULL ;
void init_AUX_Build_General_AsFix2ME_ADT4 ( ) {
ATprotect ( & constant0 ) ;
}
ATerm lf_AUX_Build_General_AsFix2ME_ADT4_1 ( ATerm arg0 , ATerm arg1 , ATerm arg2 ) {
{
ATerm tmp [ 4 ] ;
FUNC_ENTRY ( lf_AUX_Build_General_AsFix2ME_ADT4_1sym , ATmakeAppl ( lf_AUX_Build_General_AsFix2ME_ADT4_1sym , arg0 , arg1 , arg2 ) ) ;
if ( check_sym ( arg1 , ef1sym ) ) {
{
ATerm atmp10 = arg_0 ( arg1 ) ;
if ( check_sym ( atmp10 , lf2sym ) ) {
{
ATerm atmp100 = arg_0 ( atmp10 ) ;
if ( not_empty_list ( atmp100 ) ) {
( tmp [ 0 ] = ( * ef8 ) ( arg0 ) ) ;
( tmp [ 1 ] = ( * ef9 ) ( lf2 ( make_list ( atmp100 ) ) , arg2 ) ) ;
if ( check_sym ( tmp [ 1 ] , ef10sym ) ) {
( tmp [ 2 ] = arg_0 ( tmp [ 1 ] ) ) ;
if ( check_sym ( tmp [ 2 ] , lf4sym ) ) {
( tmp [ 3 ] = arg_0 ( tmp [ 2 ] ) ) ;
if ( not_empty_list ( tmp [ 3 ] ) ) {
FUNC_EXIT ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 5 , char_table [ 97 ] , char_table [ 116 ] , char_table [ 116 ] , char_table [ 114 ] , char_table [ 115 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef4sym , ( * ef5 ) ( lf4 ( cons ( make_list ( tmp [ 3 ] ) , make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 2 , char_table [ 105 ] , char_table [ 100 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef6sym , make_nf1 ( ef7sym , tmp [ 0 ] ) ) ) ) ) ) ) ) ) ) ) ) ) ) ;
}
}
}
if ( term_equal ( ( * ef9 ) ( lf2 ( make_list ( atmp100 ) ) , arg2 ) , ( constant0 ? constant0 : ( constant0 = ( * ef10 ) ( lf4 ( make_list ( null ( ) ) ) ) ) ) ) ) {
FUNC_EXIT ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 5 , char_table [ 97 ] , char_table [ 116 ] , char_table [ 116 ] , char_table [ 114 ] , char_table [ 115 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef4sym , ( * ef5 ) ( lf4 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 2 , char_table [ 105 ] , char_table [ 100 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef6sym , make_nf1 ( ef7sym , tmp [ 0 ] ) ) ) ) ) ) ) ) ) ) ) ) ) ;
}
}
else {
( tmp [ 0 ] = ( * ef8 ) ( arg0 ) ) ;
FUNC_EXIT ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 5 , char_table [ 97 ] , char_table [ 116 ] , char_table [ 116 ] , char_table [ 114 ] , char_table [ 115 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef4sym , ( * ef5 ) ( lf4 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 2 , char_table [ 105 ] , char_table [ 100 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef6sym , make_nf1 ( ef7sym , tmp [ 0 ] ) ) ) ) ) ) ) ) ) ) ) ) ) ;
}
}
}
}
}
if ( check_sym ( arg1 , ef11sym ) ) {
( tmp [ 0 ] = ( * ef8 ) ( arg0 ) ) ;
FUNC_EXIT ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 5 , char_table [ 97 ] , char_table [ 116 ] , char_table [ 116 ] , char_table [ 114 ] , char_table [ 115 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef4sym , ( * ef5 ) ( lf4 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf3 ( ( ATerm ) ATmakeList ( 2 , char_table [ 105 ] , char_table [ 100 ] ) ) ) , lf4 ( make_list ( make_nf1 ( ef6sym , make_nf1 ( ef7sym , tmp [ 0 ] ) ) ) ) ) ) ) ) ) ) ) ) ) ;
}
FUNC_EXIT ( make_nf3 ( lf_AUX_Build_General_AsFix2ME_ADT4_1sym , arg0 , arg1 , arg2 ) ) ;
}
}
ATerm lf4 ( ATerm arg0 ) {
CONS_ENTRY ( lf4sym , ATmakeAppl ( lf4sym , arg0 ) ) ;
CONS_EXIT ( make_nf1 ( lf4sym , arg0 ) ) ;
}
ATerm lf3 ( ATerm arg0 ) {
CONS_ENTRY ( lf3sym , ATmakeAppl ( lf3sym , arg0 ) ) ;
CONS_EXIT ( make_nf1 ( lf3sym , arg0 ) ) ;
}
ATerm lf2 ( ATerm arg0 ) {
CONS_ENTRY ( lf2sym , ATmakeAppl ( lf2sym , arg0 ) ) ;
CONS_EXIT ( make_nf1 ( lf2sym , arg0 ) ) ;
}

