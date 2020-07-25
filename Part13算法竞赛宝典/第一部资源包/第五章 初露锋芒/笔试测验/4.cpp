#include <iostream>
using namespace std;

int main()
{
  int x=4,y=5,z;
  z=y+6;
  while((z-x)%4!=0)
  {
    cout<<z<<" ";
    z+=7;
  }
  cout<<endl;
}
