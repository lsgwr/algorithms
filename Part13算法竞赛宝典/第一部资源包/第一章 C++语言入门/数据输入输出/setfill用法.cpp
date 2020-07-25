//setfill”√∑®
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  double a=234.123;
  cout<<a<<endl;
  cout<<setw(10)<<setfill('*')<<setprecision(4)<<a<<endl;
  cout<<setw(10)<<setprecision(10)<<a<<endl;
  system("pause");
  return 0;
}
