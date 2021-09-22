//理想收入问题－优化算法１ 
#include <stdio.h>
#include <stdlib.h>

FILE *in=fopen("stock.in","rb");
FILE *out=fopen("stock.out","w");

int main()
{
  int n;
  fscanf(in,"%d",&n);
  double v[n+1],f[n+1],Maxfv[n+1];
  int i,j,k;
  for(i=1;i<=n;i++)
    fscanf(in,"%lf",&v[i]),f[i]=0;
    
  /*
  Maxfv[i]=max{ Maxfv[i-1] , f[j]/v[i](0<=j<i) }
  f[i]=Maxfv[i]*v[i]
  */
  f[0]=1;Maxfv[0]=0;
  for(i=1;i<=n;i++)
  {
    Maxfv[i]=Maxfv[i-1];
    for(j=0;j<i;j++)
      if(f[j]/v[i] > Maxfv[i])
        Maxfv[i]=f[j]/v[i];
    f[i]=Maxfv[i]*v[i];
  }
  fprintf(out,"%.4lf\n",f[n]);
  return 0;
}
