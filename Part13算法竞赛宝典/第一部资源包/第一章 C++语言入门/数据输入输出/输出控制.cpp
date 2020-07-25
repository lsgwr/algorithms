//Êä³ö¿ØÖÆ
#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
  double a=234.123456;
  cout<<a<<endl;
  cout.precision(7);
  cout<<a<<endl;
  cout.width(8);
  cout<<a<<endl;
  cout.fill('*');
  cout.width(10);
  cout.precision(5);
  cout<<a<<endl;
  system("pause");
}
