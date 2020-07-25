//近似整数－暴力枚举 
#include<iostream>
#include<cmath>
#include<cstdlib>
using namespace std;

int main()
{
  freopen("Approximation.in", "r", stdin);
  freopen("Approximation.out", "w", stdout);    
  double min,a,temp;
  int i,j,n,d,l;
  while(scanf("%lf%d",&a,&l)!=EOF)
  {
    min=10.0;
    for(i=1;i<=l;i++)
    {
      j=(int)(i/a);
      if(j>l)
        continue;
      temp=fabs(a-(double)i/(double)j);
      if(temp<min)
      {
        min=temp;
        n=i;
        d=j;
      }
      j++;
      temp=fabs(a-(double)i/(double)j);
      if(temp<min)
      {
        min=temp;
        n=i;
        d=j;
      }
    }
    printf("%d %d\n",n,d);
  }
  return 0;
}
