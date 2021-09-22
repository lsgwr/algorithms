#include <iostream>
using namespace std;

int fun(int x,int y)
{
  return(x+y);  
}

int main()
{
  int a=2,b=5,c=8;
  cout<<fun((int)fun(a+c,b),a-c)<<endl;
  getchar();     
}
