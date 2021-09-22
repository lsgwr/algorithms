//二分法求方程根
#include <iostream>
#include <math.h>
using namespace std;

int main()
{
  float x0,x1,x2,fx0,fx1,fx2;
  do
  {
    cin>>x1>>x2;
    fx1=x1*((2*x1-4)*x1+3)-6;
    fx2=x2*((2*x2-4)*x2+3)-6;
  }while(fx1*fx2>0);//必须输入正确值时
  do
  {
    x0=(x1+x2)/2;
    fx0=x0*((2*x0-4)*x0+3)-6;
    if((fx0*fx1)<0)//取互为相反数的那一段
    {
      x2=x0;
      fx2=fx0;
    }
    else
    {
      x1=x0;
      fx1=fx0;
    }
  }while(fabs(fx0)>=1e-5);
  cout<<x0;
  system("pause");
  return 0;                  
}
