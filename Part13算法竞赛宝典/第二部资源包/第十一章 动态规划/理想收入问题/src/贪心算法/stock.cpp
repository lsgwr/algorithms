//理想收入问题－贪心算法 
#include <stdio.h>
FILE *in=fopen("stock.in","rb"),*out=fopen("stock.out","w");
int main()
{
  int n;
  double m=1,v[2];
  fscanf(in,"%d %lf",&n,&v[0]);
  for(int i=1;i<n;i++)//注意第一个数早已输入 
  {
    fscanf(in,"%lf",&v[i&1]);
    if(v[i&1]>v[i-1&1])
      m*=v[i&1]/v[i-1&1];
  }
  fprintf(out,"%.4lf\n",m);
  return 0;
}
