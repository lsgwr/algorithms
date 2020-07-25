//理想收入问题－优化算法８ 
#include <stdio.h>
FILE *in=fopen("stock.in","rb"),*out=fopen("stock.out","w");
int main()
{
  int n,i; 
  double v,f=1,g=0;
  fscanf(in,"%d",&n);
  for(i=1;i<=n;i++)
  {
    fscanf(in,"%lf",&v);
    if(f<g*v) 
      f=g*v;
    else      
      g=f/v;
  } 
  fprintf(out,"%.4lf\n",f);
  return 0;
}
