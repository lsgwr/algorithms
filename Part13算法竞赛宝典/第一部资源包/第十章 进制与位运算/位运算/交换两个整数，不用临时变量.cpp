//交换两个整数，不用临时变量
#include <iostream>
using namespace std;

int main()
{
  int a=123;
  int b=789;
  a=a^b;
  b=b^a;
  a=a^b;
  cout<<a<<" "<<b;//输出结果为789 123 
  system("pause");
}
