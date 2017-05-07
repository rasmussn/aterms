#include "aterm1.h"
#include <stdio.h>

int main(int argc, char **argv){
   ATerm bottomOfStack;
   ATerm program;
   FILE * file;

   ATinit(argc, argv, &bottomOfStack);
  	
   printf("Hellno!\n");
  	
   file = fopen(SRCDIR "/test/" "test.baf", "rb");
   if (!file) {
      printf("ERROR: in test_bafio, path is %s\n", SRCDIR "/test/" "test.baf");
      ATerror("cannot open file \"test.baf\"");
   }

   program = ATreadFromBinaryFile(file);
   /*printf("program term is:\n%s\n\n", ATwriteToString(program));*/

#if 0
   testIntegerEncoding();
   printf("\n");
  	
   testDoubleEncoding();
   printf("\n");
  	
   testWriting();
   printf("\n");
  	
   testReading();
   printf("\n");
  	
   testChunkification();
   printf("\n");
	
   testDeepNesting();
   printf("\n");
#endif
	
   return 0;
}
