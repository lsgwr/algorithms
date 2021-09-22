//打印三角形
#include <iostream>
using namespace std;

int main()
{
  for(int i=1;i<=4;i++)//输出行数 
  {
    for(int j=1;j<=4-i;j++)//控制每行输出的空格数 
      cout<<' ';
    for(int j=1;j<=2*i-1;j++)//控制每行输出的*的个数 
      cout<<'*';
    cout<<endl;                    
  }
  system("pause");
  return 0;
}
