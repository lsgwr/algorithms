////理想收入问题－优化算法5
#include <stdio.h>
#include <stdlib.h>

FILE *in=fopen("stock.in","rb");
FILE *out=fopen("stock.out","w");

int main()
{
  int n;
  fscanf(in,"%d",&n);
  double v[n+1],f[n+1],g[n+1];
  int i;
  for(i=1;i<=n;i++)
    fscanf(in,"%lf",&v[i]);  
  /*
  g[i]=max{ g[i-1] , f[i-1]/v[i] }
  f[i]=max{ f[i-1] , g[i]*v[i] }
  */
  f[0]=1;g[0]=0;
  for(i=1;i<=n;i++)
  {
    g[i]=g[i-1];
    if(f[i-1]/v[i] > g[i])
      g[i]=f[i-1]/v[i];
    f[i]=f[i-1];
    if(g[i]*v[i] > f[i])
      f[i]=g[i]*v[i];
  }
  fprintf(out,"%.4lf\n",f[n]);
  return 0;
}
