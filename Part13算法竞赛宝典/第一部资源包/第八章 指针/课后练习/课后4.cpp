#include <iostream>
using namespace std;

int main()
{
  static char a[]="Language",b[]="programe";
  char *p1,*p2;
  int k;
  p1=a;p2=b;
  for(k=0;k<=7;k++)
    if(*(p1+k)==*(p2+k))
      cout<<*(p1+k);
  getchar();
}
