#include <iostream>
using namespace std;

int main()
{
  int a=10;
  int b1=a<<2;//左移两位，即10*2^2=40 
  int b2=a>>2;//右移两位，即10/2^2=2 
  cout<<"b1="<<b1<<" b2="<<b2<<endl;
  system("pause");
}
