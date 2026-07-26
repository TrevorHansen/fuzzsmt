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

/* The (_ FloatingPoint eb sb) sort of the SMT-LIB 2 FloatingPoint theory.
 * Unlike BVType this sort is indexed by two numbers, and operands of an
 * fp operator must agree on both of them.
 */
public class FPType extends SMTType
{

  protected String smtlib2_name;

  protected int eb; /* number of exponent bits, > 1 */

  protected int sb; /* significand bits, hidden bit included, > 1 */

  public FPType (int eb, int sb){
    assert (eb > 1);
    assert (sb > 1);
    this.eb = eb;
    this.sb = sb;
    this.smtlib2_name = "(_ FloatingPoint " + eb + " " + sb + ")";
  }

  public String toString (boolean smtlib1){
    assert (!smtlib1); /* SMT-LIB 1 has no floating point theory */
    return this.smtlib2_name;
  }

  /* The Float16/32/64/128 abbreviation of this sort, or null if the sort
   * is not one of the four the theory gives a short name to.  Emitting the
   * abbreviation now and then exercises a separate parser path.
   */
  public String getAbbreviation (){
    if (eb == 5 && sb == 11)
      return "Float16";
    if (eb == 8 && sb == 24)
      return "Float32";
    if (eb == 11 && sb == 53)
      return "Float64";
    if (eb == 15 && sb == 113)
      return "Float128";
    return null;
  }

  public int getExponentBits (){
    return this.eb;
  }

  public int getSignificandBits (){
    return this.sb;
  }

  public boolean equals (Object o){
    assert (o != null);

    if (! (o instanceof FPType))
      return false;

    return this.eb == ((FPType) o).eb && this.sb == ((FPType) o).sb;
  }

  public int hashCode (){
    return 31 * this.eb + this.sb;
  }

}
