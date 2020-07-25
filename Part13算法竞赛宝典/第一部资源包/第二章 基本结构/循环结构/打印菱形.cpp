//打印菱形
#include <iostream>
using namespace std;

int main()
{
  for(int i=-3;i<=3;i++)//输出行数
  {
    int k=abs(i);      
    for(int j=1;j<=k;j++)//控制输出每行空格数
     cout<<' ';
    for(int j=1;j<=7-2*k;j++)//控制输出每行*号数
      cout<<'*';
    cout<<endl;                    
  }
  system("pause");
  return 0;
}
