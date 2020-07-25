//迭代法求平方根
#include <iostream>
#include <math.h>
using namespace std;

int main()
{	
  float a,xn0,xn1;
  cin>>a;
  xn0=a/2;
  xn1=(xn0+a/xn0)/2;
  do
  {
     xn0=xn1;
     xn1=(xn0+a/xn0)/2;
  }while(fabs(xn0-xn1)>=1e-5);
  cout<<xn1<<endl;
  system("pause");
  return 0;
}
