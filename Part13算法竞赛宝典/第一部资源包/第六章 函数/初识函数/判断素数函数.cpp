//判断素数函数
#include <iostream>
using namespace std;
void prime(int number);//对子函数的声明

int main()
{
  int num;  
  cin>>num;
  prime(num);
}

void prime(int number)  //无需返回值时，需在前面加上void
{
  int n,flag=1;//flag为是否素数的标记
  for(n=2;n<number/2;n++)
    if(number%n==0)
      flag=0;
  if(flag)  //如果flag为真
     cout<<number<<"是一个素数"<<endl;     
  else
     cout<<number<<"不是一个素数"<<endl;
  system("pause");
  return 0;
}
