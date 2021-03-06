/* Copyright (c) 2003, CWI, LORIA-INRIA All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation; either version 2, or 
 * (at your option) any later version.
 */
using System;

namespace SharedObjects
{
	/// <summary>
	/// Summary description for SharedObjectWithID.
	/// </summary>
	public interface SharedObjectWithID : SharedObject
	{
		int getUniqueIdentifier();
		void setUniqueIdentifier(int uniqueId);
	}
}