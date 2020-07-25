//计算1+2+3+…+100的值
#include <iostream>
using namespace std;

int main()
{
  int sum=0,i;//定义循环变量i 
  for(i=1;i<=100;i++)//i赋初值；循环条件;i每次自增1 
  {
    sum+=i;//即sum=sum+i 
  }
  cout<<sum<<endl;
  system("pause");
  return 0;
}
