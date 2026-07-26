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

/* The 0-ary RoundingMode sort of the SMT-LIB 2 FloatingPoint theory.
 * Its only terms are the five rounding mode constants and free constants
 * declared to be of this sort.
 */
public class RoundingModeType extends SMTType {

  public final static RoundingModeType roundingModeType =
    new RoundingModeType();

  private RoundingModeType() {}

  public String toString (boolean smtlib1) {
    assert (!smtlib1); /* SMT-LIB 1 has no floating point theory */
    return "RoundingMode";
  }

}
