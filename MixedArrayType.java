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

/* Array sort whose index and element are drawn independently from the
 * scalar sorts the logic mixes -- bit-vectors and floating-point formats.
 * BVArrayType stays the type of the pure bit-vector arrays; this one
 * appears only where at least one side is a floating-point sort, which the
 * ABVFP logics allow.  SMT-LIB 1 has no floating point, so there is no
 * smtlib1 spelling. */
public class MixedArrayType extends ArrayType
{

  protected SMTType indexType;

  protected SMTType elementType;

  protected String smtlib2_name;

  public MixedArrayType (SMTType indexType, SMTType elementType){
    assert (indexType instanceof BVType || indexType instanceof FPType);
    assert (elementType instanceof BVType || elementType instanceof FPType);
    assert (indexType instanceof FPType || elementType instanceof FPType);
    this.indexType = indexType;
    this.elementType = elementType;
    this.smtlib2_name = indexType.toString (false) + " " +
                        elementType.toString (false);
  }

  public String toString (boolean smtlib1){
    assert (!smtlib1);
    return this.smtlib2_name;
  }

  public SMTType getIndexType (){
    return this.indexType;
  }

  public SMTType getElementType (){
    return this.elementType;
  }

  public boolean equals (Object o){
    MixedArrayType type;
    assert (o != null);

    if (! (o instanceof MixedArrayType))
      return false;

    type = (MixedArrayType) o;

    return this.indexType.equals (type.indexType) &&
           this.elementType.equals (type.elementType);
  }

}
