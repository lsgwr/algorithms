//getc and putc
#include <stdio.h>

int main()
{
  FILE *in=fopen("i.txt","rb");
  FILE *out=fopen("o.txt","wb");
  char c;
  c=getc(in);
  putc(c,out);
  return 0;
}
