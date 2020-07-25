//试图改变常量a的错误的程序
#include <iostream>
using namespace std;

int main()
{
  const int a=34;  //定义了一个int型的常量a ，a等于34
  a=a*5;           //试图将a乘以5后再将其值赋给a
  cout<<a<<endl;   
  system("pause");
  return 0;
}
