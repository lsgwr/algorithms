//判断闰年1
#include <iostream>
using namespace std;

int main()
{
  int year,leap;
  cin>>year;
  if(year%400==0)
    cout<<"是闰年";
  else if(year%100!=0)
  {
    if (year%4==0)   
      cout<<"是闰年";
    else
      cout<<"不是闰年";
  }
  else
    cout<<"不是闰年";     
  system("pause");
  return 0;
}
