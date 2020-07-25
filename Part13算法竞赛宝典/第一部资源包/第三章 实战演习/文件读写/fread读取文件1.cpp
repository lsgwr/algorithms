//fread¶ÁÈ¡ÎÄ¼ş1
#include <stdio.h>
#include <stdlib.h>
char a[1100000];

int main()
{
  FILE *in=fopen("a.in","rb");
  FILE *out=fopen("a.out","w");
  int n=fread(a,1,1100000,in);
  for(int i=0;i<n;i++)
    fprintf(out,"%c",a[i]);
  return 0;
}
