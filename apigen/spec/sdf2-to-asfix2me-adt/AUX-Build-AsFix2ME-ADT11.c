/* This C code is generated by the AsfSdfCompiler version 1.3 */

#include  "asc-support.h"
static Symbol lf_AUX_Build_AsFix2ME_ADT11_1sym ;
static ATerm lf_AUX_Build_AsFix2ME_ADT11_1 ( ATerm arg1 , ATerm arg2 ) ;
static Symbol ef1sym ;
static funcptr ef1 ;
static Symbol ef4sym ;
static funcptr ef4 ;
static Symbol ef5sym ;
static funcptr ef5 ;
static Symbol ef2sym ;
static funcptr ef2 ;
static Symbol lf2sym ;
static ATerm lf2 ( ATerm arg1 ) ;
static Symbol ef3sym ;
static funcptr ef3 ;
static Symbol lf3sym ;
static ATerm lf3 ( ATerm arg1 ) ;
static Symbol ef6sym ;
static funcptr ef6 ;
static Symbol ef7sym ;
static funcptr ef7 ;
static Symbol ef8sym ;
static funcptr ef8 ;
void register_AUX_Build_AsFix2ME_ADT11 ( ) {
lf_AUX_Build_AsFix2ME_ADT11_1sym = ATmakeSymbol ( "prod(id(\"Build-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-iter-plus\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"AFun\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"Symbol\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)"
 , 2 , ATtrue ) ;
ATprotectSymbol ( lf_AUX_Build_AsFix2ME_ADT11_1sym ) ;
lf2sym = ATmakeSymbol ( "listtype(sort(\"CHAR\"))" , 1 , ATtrue ) ;
ATprotectSymbol ( lf2sym ) ;
lf3sym = ATmakeSymbol ( "listtype(sort(\"ATerm\"),ql(\",\"))" , 1 , ATtrue ) ;
ATprotectSymbol ( lf3sym ) ;
register_prod ( ATparse ( "prod(id(\"Build-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-iter-plus\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"AFun\"),w(\"\"),ql(\",\"),w(\"\"),sort(\"Symbol\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)" ) , lf_AUX_Build_AsFix2ME_ADT11_1 , lf_AUX_Build_AsFix2ME_ADT11_1sym ) ;
register_prod ( ATparse ( "listtype(sort(\"CHAR\"))" ) , lf2 , lf2sym ) ;
register_prod ( ATparse ( "listtype(sort(\"ATerm\"),ql(\",\"))" ) , lf3 , lf3sym ) ;
}
void resolve_AUX_Build_AsFix2ME_ADT11 ( ) {
ef1 = lookup_func ( ATreadFromString ( "prod(id(\"CC-Sdf-Syntax\"),w(\"\"),[sort(\"CharClass\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Symbol\"),w(\"\"),no-attrs)" ) ) ;
ef1sym = lookup_sym ( ATreadFromString ( "prod(id(\"CC-Sdf-Syntax\"),w(\"\"),[sort(\"CharClass\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Symbol\"),w(\"\"),no-attrs)" ) ) ;
ef2 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef2sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\"),w(\"\"),ql(\"(\"),w(\"\"),iter-sep(l(\"{\"),w(\"\"),sort(\"ATerm\"),w(\"\"),ql(\",\"),w(\"\"),l(\"}\"),w(\"\"),l(\"+\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef3 = lookup_func ( ATreadFromString ( "prod(id(\"GEN-LexConsFuncs\"),w(\"\"),[ql(\"afun\"),w(\"\"),ql(\"(\"),w(\"\"),iter(sort(\"CHAR\"),w(\"\"),l(\"*\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),no-attrs)" ) ) ;
ef3sym = lookup_sym ( ATreadFromString ( "prod(id(\"GEN-LexConsFuncs\"),w(\"\"),[ql(\"afun\"),w(\"\"),ql(\"(\"),w(\"\"),iter(sort(\"CHAR\"),w(\"\"),l(\"*\")),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),no-attrs)" ) ) ;
ef4 = lookup_func ( ATreadFromString ( "prod(id(\"Build-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-charclass\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"CharClass\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)" ) ) ;
ef4sym = lookup_sym ( ATreadFromString ( "prod(id(\"Build-AsFix2ME-ADT\"),w(\"\"),[ql(\"build-asfix-charclass\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"CharClass\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),no-attrs)" ) ) ;
ef5 = lookup_func ( ATreadFromString ( "prod(id(\"Sorts-Sdf-Syntax\"),w(\"\"),[sort(\"Sort\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Symbol\"),w(\"\"),no-attrs)" ) ) ;
ef5sym = lookup_sym ( ATreadFromString ( "prod(id(\"Sorts-Sdf-Syntax\"),w(\"\"),[sort(\"Sort\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"Symbol\"),w(\"\"),no-attrs)" ) ) ;
ef6 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef6sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AFun\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"ATerm\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef7 = lookup_func ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AT-Literal\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef7sym = lookup_sym ( ATreadFromString ( "prod(id(\"ATerm-Syntax\"),w(\"\"),[sort(\"AT-Literal\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AFun\"),w(\"\"),attrs(l(\"{\"),w(\"\"),[l(\"constructor\")],w(\"\"),l(\"}\")))" ) ) ;
ef8 = lookup_func ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"sort-to-lit\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"Sort\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AT-Literal\"),w(\"\"),no-attrs)" ) ) ;
ef8sym = lookup_sym ( ATreadFromString ( "prod(id(\"Build-General-AsFix2ME-ADT\"),w(\"\"),[ql(\"sort-to-lit\"),w(\"\"),ql(\"(\"),w(\"\"),sort(\"Sort\"),w(\"\"),ql(\")\")],w(\"\"),l(\"->\"),w(\"\"),sort(\"AT-Literal\"),w(\"\"),no-attrs)" ) ) ;
}
void init_AUX_Build_AsFix2ME_ADT11 ( ) {
}
ATerm lf_AUX_Build_AsFix2ME_ADT11_1 ( ATerm arg0 , ATerm arg1 ) {
{
ATerm tmp [ 1 ] ;
FUNC_ENTRY ( lf_AUX_Build_AsFix2ME_ADT11_1sym , ATmakeAppl ( lf_AUX_Build_AsFix2ME_ADT11_1sym , arg0 , arg1 ) ) ;
if ( check_sym ( arg1 , ef1sym ) ) {
{
ATerm atmp10 = arg_0 ( arg1 ) ;
FUNC_EXIT ( make_nf2 ( ef2sym , ( * ef3 ) ( lf2 ( ( ATerm ) ATmakeList ( 3 , char_table [ 108 ] , char_table [ 101 ] , char_table [ 120 ] ) ) ) , lf3 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf2 ( ( ATerm ) ATmakeList ( 4 , char_table [ 105 ] , char_table [ 116 ] , char_table [ 101 ] , char_table [ 114 ] ) ) ) , lf3 ( make_list ( ( * ef4 ) ( atmp10 ) ) ) ) ) ) ) ) ;
}
}
if ( check_sym ( arg1 , ef5sym ) ) {
{
ATerm atmp10 = arg_0 ( arg1 ) ;
( tmp [ 0 ] = make_nf1 ( ef7sym , ( * ef8 ) ( atmp10 ) ) ) ;
FUNC_EXIT ( make_nf2 ( ef2sym , arg0 , lf3 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf2 ( ( ATerm ) ATmakeList ( 4 , char_table [ 105 ] , char_table [ 116 ] , char_table [ 101 ] , char_table [ 114 ] ) ) ) , lf3 ( make_list ( make_nf2 ( ef2sym , ( * ef3 ) ( lf2 ( ( ATerm ) ATmakeList ( 4 , char_table [ 115 ] , char_table [ 111 ] , char_table [ 114 ] , char_table [ 116 ] ) ) ) , lf3 ( make_list ( make_nf1 ( ef6sym , tmp [ 0 ] ) ) ) ) ) ) ) ) ) ) ) ;
}
}
FUNC_EXIT ( make_nf2 ( lf_AUX_Build_AsFix2ME_ADT11_1sym , arg0 , arg1 ) ) ;
}
}
ATerm lf3 ( ATerm arg0 ) {
CONS_ENTRY ( lf3sym , ATmakeAppl ( lf3sym , arg0 ) ) ;
CONS_EXIT ( make_nf1 ( lf3sym , arg0 ) ) ;
}
ATerm lf2 ( ATerm arg0 ) {
CONS_ENTRY ( lf2sym , ATmakeAppl ( lf2sym , arg0 ) ) ;
CONS_EXIT ( make_nf1 ( lf2sym , arg0 ) ) ;
}

