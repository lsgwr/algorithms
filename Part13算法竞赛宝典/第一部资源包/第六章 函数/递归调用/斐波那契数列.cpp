//ì³²¨ÄÇÆõÊıÁĞ
#include <iostream>
#include <stdlib.h>
using namespace std;

int f(int n)
{
  if(n<3)
    return 1;
  return f(n-1)+f(n-2);     
}

int main()
{
  int n;
  cin>>n;
  cout<<f(n);   
  system("pause"); 
  return 0;  
}
