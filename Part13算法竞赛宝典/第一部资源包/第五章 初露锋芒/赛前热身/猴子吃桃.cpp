//猴子吃桃
#include <iostream>
using namespace std;

int main()
{
  int day=9,x1,x2=1;
  while(day>0)
  {
    x1=(x2+1)*2;//第１天的桃子数是第２的天的桃子数加１后的２倍
    x2=x1;
    day--;            
  }
  cout<<x1;
  //system("pause");
  return 0;
}
