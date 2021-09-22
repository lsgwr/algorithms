//用数组来处理求Fibonacci数列问题。
#include <iostream>
using namespace std;

int main()
{
  int i;
  int f[20]={1,1}; //a[0]=1,a[1]=1,其余１８个元素自动赋值为0
  for(i=2;i<20;i++)
     f[i]=f[i-2]+f[i-1];//依次递推 
  for(i=0;i<20;i++)
  {
     if(i%5==0) 
       cout<<endl;//５个数为一行
     cout<<f[i]<<"  "; 
  }    
  system("pause");
  return 0;
}
