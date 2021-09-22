#include <iostream>
using namespace std;

int main()
{
  static char s[]={"abcdef"};
  char *p=s;
  *(p+2)+=3;
  cout<<*p<<","<<*(p+2)<<endl;
  getchar();     
}
