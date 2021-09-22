//逆序输出数组元素值
#include <iostream>
using namespace std;

int main()
{
  int i,a[10];
  for(i=0;i<=9;i++)
    a[i]=i;
  for(i=9;i>=0;i--)
    cout<<a[i]<<' ';
  system("pause");
  return 0;    
} 
