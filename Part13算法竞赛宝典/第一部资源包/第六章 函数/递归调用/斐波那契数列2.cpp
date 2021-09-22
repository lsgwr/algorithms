//斐波那契数列改进算法 
#include <iostream>
#include <stdlib.h>
using namespace std;

int a[5000];
int f(int n)
{  
  if(n<3)
    return 1;
  if(a[n]>0)//若已求出第n项的值 
    return a[n];//直接返回该值 
  a[n]=f(n-1)+f(n-2);//保存递归求出的值   
  return a[n];   
}

int main()
{
  int n;
  cin>>n;
  cout<<f(n);   
  system("pause");   
  return 0;
}
