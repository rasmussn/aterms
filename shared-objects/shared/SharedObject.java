/* Copyright (c) 2003, CWI, LORIA-INRIA All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 */
  
package shared;

public interface SharedObject {
  SharedObject duplicate();
  boolean equivalent(SharedObject o);
  int hashCode();
}
