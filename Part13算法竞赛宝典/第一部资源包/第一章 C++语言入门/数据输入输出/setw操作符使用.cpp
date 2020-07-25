//setw操作符的使用
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  double a=234.34;
  cout<<setw(10)<<1234567890<<endl;
  cout<<setw(4)<<a<<endl;
  cout<<setw(6)<<a<<endl;
  cout<<setw(8)<<a<<endl;
  cout<<setw(10)<<a<<endl;
  system("pause");
  return 0;
}
