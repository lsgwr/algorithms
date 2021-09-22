//求数据类型的最大值和最小值
#include <iostream>
#include <limits.h>  //c++可省略这一句 
#include <float.h>
using namespace std;

int main()
{
  int a=INT_MIN;
  int b=INT_MAX;
  cout<<a<<' '<<b<<endl;
  cout<<DBL_MAX<<endl;
  cout<<DBL_MIN<<endl;  
  system("pause");
  return 0;   
}
