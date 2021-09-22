//十进制小数转为N进制
#include <iostream>
#include <cstdlib>
#include <cstdio>
using namespace std;

double x;
int n;
string s;

void fun(double x,int n,int m)
{
  const string a="0123456789ABCDEF";
  int u;
  s='.';
  while(x>0 && m>0)
  { 
    m--;//可用小数位数减1
    x=x*n;
   
    s=s+a[int(x)];//取余数依次放后面
    x=x-int(x);//取X的小数部分
  }
}

int main()
{
  cin>>x>>n;   
  if (x<1)
    fun(x,n,20);
  cout<<s;
  system("pause");  
}
