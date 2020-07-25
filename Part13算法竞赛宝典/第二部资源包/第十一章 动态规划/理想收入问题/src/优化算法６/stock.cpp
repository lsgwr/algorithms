//理想收入问题－优化算法6
#include <stdio.h>
#include <stdlib.h>

FILE *in=fopen("stock.in","rb");
FILE *out=fopen("stock.out","w");

int main()
{
  int n;
  fscanf(in,"%d",&n);
  double v,f,g;
  int i;
  /*f[i]=max{ f[i-1] , f[j]/v[j+1]*v[i](0<=j<i) }*/
  f=1;g=0; //f[0]=1,g[0]=1
  for(i=1;i<=n;i++)
  {
    fscanf(in,"%lf",&v);
    if(f/v > g)
      g=f/v;
    if(g*v > f)
      f=g*v;
  }
  fprintf(out,"%.4lf\n",f);
  return 0;
}
