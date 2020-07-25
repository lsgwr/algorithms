//整型数据的溢出
#include <iostream>
using namespace std;

int main()
{
  int a=2147483647;
  a=a+1;
  cout<<"a ="<<a<<endl; 
  system("pause");
  return 0;
}
