//理想收入问题－优化算法４ 
#include <stdio.h>
#include <stdlib.h>

FILE *in=fopen("stock.in","rb");
FILE *out=fopen("stock.out","w");

int main()
{
  int n;
  fscanf(in,"%d",&n);  
  double v[n+1],f[n+1];
  int i,j;
  for(i=1;i<=n;i++)
    fscanf(in,"%lf",&v[i]);
    
  /*f[i]=max{ f[i-1] , f[j]/v[j+1]*v[i](0<=j<i) }*/
  f[0]=1;
  for(i=1;i<=n;i++)
  {
    f[i]=f[i-1];
    for(j=0;j<i;j++)
      if(f[j]/v[j+1]*v[i] > f[i])
    f[i]=f[j]/v[j+1]*v[i];
  }
  fprintf(out,"%.4lf\n",f[n]);
  return 0;
}
