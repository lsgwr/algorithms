#include <iostream>
using namespace std;

int main()
{
  int i,*p;
  p=&i;
  *p=8;
  cout<<i<<endl;
  getchar();
}
