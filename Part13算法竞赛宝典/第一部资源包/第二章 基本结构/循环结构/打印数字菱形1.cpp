//打印数字菱形1
#include <iostream>
using namespace std;

int main()
{
  for(int i=-3;i<=3;i++)//控制输出行数
  {
    int k=abs(i);      
    for(int j=1;j<=k;j++)//控制输出每行的空格数
     cout<<' ';
    for(int j=1;j<=7-2*k;j++) //控制输出的数字
      cout<<k+1;
    cout<<endl;                    
  }
  system("pause");
  return 0;
}
