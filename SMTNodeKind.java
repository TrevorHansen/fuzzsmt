/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2009  Robert Daniel Brummayer
 *
 *  This file is part of FuzzSMT.
 *
 *  FuzzSMT is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  FuzzSMT is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public enum SMTNodeKind { 

  /* input */
  CONST("constant", 0),
  VAR("variable", 0), 
  /* boolean */
  TRUE("true", 0),
  FALSE("false", 0),
  NOT("not", 1), 
  AND("and", 2),
  OR("or", 2),
  IMPLIES("implies","=>", 2),
  XOR("xor", 2),
  IFF("iff", "=", 2),
  IF_THEN_ELSE("if_then_else", "ite", 3), // if-then-else on boolean operands 
  /* bit-vector operators: */
  /* unary operators */
  BVNOT("bvnot", 1),
  BVNEG("bvneg", 1),
  /* unary operators with index */
  // put a bracket infront of some of these because it's easiest..
  EXTRACT("extract","(_ extract", 1),
  REPEAT("repeat","(_ repeat", 1),
  ZERO_EXTEND("zero_extend","(_ zero_extend", 1),
  SIGN_EXTEND("sign_extend","(_ sign_extend", 1),
  ROTATE_LEFT("rotate_left","(_ rotate_left", 1),
  ROTATE_RIGHT("rotate_right","(_ rotate_right", 1),
  /* commutative binary operators */
  BVAND("bvand", 2),
  BVNAND("bvnand", 2),
  BVOR("bvor", 2),
  BVNOR("bvnor", 2),
  BVXOR("bvxor", 2),
  BVXNOR("bvxnor", 2),
  BVADD("bvadd", 2),
  BVMUL("bvmul", 2),
  BVCOMP("bvcomp", 2),
  /* non-commutative binary operators */
  BVULT("bvult", 2),
  BVULE("bvule", 2),
  BVUGT("bvugt", 2),
  BVUGE("bvuge", 2),
  BVSLT("bvslt", 2),
  BVSLE("bvsle", 2),
  BVSGT("bvsgt", 2),
  BVSGE("bvsge", 2),
  /* overflow-detection predicates (SMT-LIB 2 FixedSizeBitVectors).
   * Binary, take two bit-vectors of equal width and return Bool.
   * Keep these contiguous, immediately after BVSGE: the BV term and
   * predicate layers select them via EnumSet.range(BVULT, BVSDIVO). */
  BVUADDO("bvuaddo", 2),
  BVSADDO("bvsaddo", 2),
  BVUSUBO("bvusubo", 2),
  BVSSUBO("bvssubo", 2),
  BVUMULO("bvumulo", 2),
  BVSMULO("bvsmulo", 2),
  BVSDIVO("bvsdivo", 2),
  BVSHL("bvshl", 2),
  BVLSHR("bvlshr", 2),
  BVASHR("bvashr", 2),
  BVSUB("bvsub", 2),
  BVUDIV("bvudiv", 2),
  BVUREM("bvurem", 2),
  BVSDIV("bvsdiv", 2),
  BVSREM("bvsrem", 2),
  BVSMOD("bvsmod", 2),
  CONCAT("concat", 2),
  /* unary overflow-detection predicate (SMT-LIB 2): (_ BitVec m) -> Bool.
   * Kept outside the BVNOT..CONCAT term range on purpose so it is only
   * emitted at the Boolean/predicate layer, not wrapped as a BV term. */
  BVNEGO("bvnego", 1),
  /* interpreted predicates */
  LT("<", 2),
  GT(">", 2),
  LE("<=", 2),
  GE(">=", 2),
  EQ("=", 2),
  DISTINCT("distinct", -1),
  /* interpreted functions */
  PLUS("+", 2),
  UNMINUS("~","-", 1),
  BINMINUS("-", 2),
  MUL("*", 2),
  DIV("/", 2),
  /* array operators */
  SELECT("select", 2),
  STORE("store", 3),
  /* if-then-else on terms */
  ITE("ite", 3),
  UFUNC("extrafun", -1),
  UPRED("extrapred", -1),
  /* floating point operators (SMT-LIB 2 FloatingPoint theory).  These are
   * appended at the end of the enum so that the EnumSet.range selections
   * used by the bit-vector, arithmetic and array layers are unaffected.
   * The arity recorded here does NOT count the leading RoundingMode
   * argument of fp.add, fp.sub, fp.mul, fp.div, fp.fma, fp.sqrt and
   * fp.roundToIntegral; FuzzSMT.fpNeedsRoundingMode identifies those.
   *
   * FP_ABS..FP_MAX are the term operators and must stay contiguous: the
   * floating point term layer selects them via
   * EnumSet.range(FP_ABS, FP_MAX). */
  FP_ABS("fp.abs", 1),
  FP_NEG("fp.neg", 1),
  FP_SQRT("fp.sqrt", 1),
  FP_ROUND_TO_INTEGRAL("fp.roundToIntegral", 1),
  FP_ADD("fp.add", 2),
  FP_SUB("fp.sub", 2),
  FP_MUL("fp.mul", 2),
  FP_DIV("fp.div", 2),
  FP_REM("fp.rem", 2),
  FP_MIN("fp.min", 2),
  FP_MAX("fp.max", 2),
  FP_FMA("fp.fma", 3),
  /* FP_LEQ..FP_IS_POSITIVE are the predicates, likewise contiguous: the
   * floating point predicate layer selects them via
   * EnumSet.range(FP_LEQ, FP_IS_POSITIVE).  fp.eq is IEEE 754 equality,
   * which is not the same as the core = on floating point terms. */
  FP_LEQ("fp.leq", 2),
  FP_LT("fp.lt", 2),
  FP_GEQ("fp.geq", 2),
  FP_GT("fp.gt", 2),
  FP_EQ("fp.eq", 2),
  FP_IS_NORMAL("fp.isNormal", 1),
  FP_IS_SUBNORMAL("fp.isSubnormal", 1),
  FP_IS_ZERO("fp.isZero", 1),
  FP_IS_INFINITE("fp.isInfinite", 1),
  FP_IS_NAN("fp.isNaN", 1),
  FP_IS_NEGATIVE("fp.isNegative", 1),
  FP_IS_POSITIVE("fp.isPositive", 1);

  protected String smtlib1_name;
  protected String smtlib2_name;

  protected int arity; /* -1 is used to indicate n-ary operators */


  SMTNodeKind (String string, int arity){
    this.smtlib1_name = string;
    this.smtlib2_name = string;
    this.arity = arity;
  }

  SMTNodeKind (String smtlib1_name, String smtlib2_name, int arity){
	    this.smtlib1_name = smtlib1_name;
	    this.smtlib2_name = smtlib2_name;
	    this.arity = arity;
	  }


   public String getString(boolean smtlib1){
	   if (smtlib1)
		   return this.smtlib1_name;
	   else
		   return this.smtlib2_name;
	  }
 

  public int getArity() {
    return this.arity;
  }

}
