 //全局数组与局部数组 
#include <iostream>
using namespace std;
int a[10];

int fun()//另一个函数体 
{
  a[0]=0;//a[]为全局数组，可以被调用 
  //b[0]=0; 错误的调用，因为b[]为局部数组 
}

int main() 
{
  int i,b[10];
  for(i=0;i<=9;i++)
    cout<<a[i]<<' ';//输出的值均为0 
  cout<<endl;  
  for(i=0;i<=9;i++)
    cout<<b[i]<<' ';//输出的值末知 
  system("pause");
  return 0;
}
