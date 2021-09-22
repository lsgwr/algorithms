//判断闰年2
#include <iostream>
using namespace std;

int main()
{
  int year;
  cin>>year;
  if((year%400==0) || ((year%4==0) && (year%100!=0)))
    cout<<year <<"是闰年"<<endl;
  else
    cout<<year<<"不是闰年"<<endl;   
  system("pause");
  return 0;
}
