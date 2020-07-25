//计算1+2+3+…+100的值
#include <iostream>
using namespace std;

int main()
{
  int i=1,sum=0;
  while(i<=100)  //当i<=100时，则执行循环体内的语句
  {
    sum=sum+i;
    i++;
  }   
  cout<<sum<<endl;
  system("pause");
  return 0;
}
