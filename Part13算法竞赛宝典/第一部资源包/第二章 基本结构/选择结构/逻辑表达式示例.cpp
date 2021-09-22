//逻辑表达式示例 
#include <iostream>
using namespace std;

int main()
{
  int a=0;
  int b=1;
  int c=1;
  if(a && b++ && c++)
     b++;
  cout<<a<<b<<c<<endl;   
  system("pause"); 
  return 0;
}
