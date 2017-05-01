#ifndef ATYPES_H
#define ATYPES_H

#include "abool.h"

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

typedef unsigned int ShortHashNumber;

#if AT_64BIT == 1
typedef long MachineWord;
typedef unsigned long HashNumber;
#define ADDR_TO_SHORT_HNR(a) (((ShortHashNumber)((long)(a) & 0xFFFF) >> 2) ^ (((long)(a) >> 32)))

#elif AT_64BIT == 0
typedef int MachineWord;
typedef unsigned int HashNumber;
#define ADDR_TO_SHORT_HNR(a) (((ShortHashNumber)(a)) >> 2)

#endif /* AT_64BIT */

#define ADDR_TO_HNR(a) (((HashNumber)(a)) >> 2)

#ifdef __cplusplus
}
#endif/* __cplusplus */ 

#endif /* ATYPES_H */
