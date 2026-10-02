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

import java.util.ArrayList;

public class SMTNode
{

  /* While not null, every node constructed is appended here.  FuzzSMT sets
   * it while it re-runs the layers inside a get-value, so that it can pick
   * any of the let-bound terms it has just built as the term to ask for. */
  public static ArrayList<SMTNode> recorded = null;

  protected static int nodeCtr = 0;


  protected SMTType type;

  protected String name; 


  public SMTNode (SMTType type, String name){
    assert (type != null);
    assert (name != null);

    this.type = type;
    this.name = name;
    nodeCtr++;
    if (recorded != null)
      recorded.add (this);
  }

  public SMTType getType(){
    return this.type;
  }

  public String getName (){
    return this.name;
  }

  public static int getNodeCtr (){
    return nodeCtr;
  }

}
