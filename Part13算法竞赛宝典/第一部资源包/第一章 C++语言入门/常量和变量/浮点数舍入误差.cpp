//浮点数舍入误差演示 
#include <iostream>
using namespace std;

int main()
{
  float a,b,c;
  a=2345678900.000;//定义一个很大的浮点数
  b=3;//定义一个很小的浮点数
  cout<<a<<endl;
  c=a+b;
  cout<<c<<endl;
  system("pause");
  return 0;
}
