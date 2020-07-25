//取余法求二进制数
#include <iostream>
using namespace std;

void put(int number)
{ int i;
  if(number>0)
  { i=number%2;
    number=number/2;
    put(number);
    cout<<i;
  }      
}

int main()
{
  int number;
  cin>>number;
  if(number==0)
   cout<<0;
  put(number);
  system("pause");     
}
