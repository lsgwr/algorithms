//逃亡－分治算法 
#include <cstdio>
#include <cstdlib>
#include <math.h>
#include <iostream>
using namespace std;

int main()
{
  freopen("escape.in","r",stdin);
  freopen("escape.out","w",stdout);  
  float s,a,b,c,c0,c1,t1,t2,t3,t4 ;
  cin>>s>>a>>b;   
  c0=0;c1=s;
  do
  {
    c=(c0+c1)/2.0;
    t3=c/b;//甲乘车到C的时间 
    t1=t3+(s-c)/a;//甲用的总时间 
    t4=(c-t3*a)/(a+b);//小车从c回头与乙相遇的时间 
    t2=t3+t4+(s-(t3+t4)*a)/b;//乙用的总时间 
    if(t1<t2)
      c1=c;
    else
      c0=c;                        
  }while(fabs(t1-t2)>1e-4);
  printf("%4.2f\n",t1);
  return 0;
}
