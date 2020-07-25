/*
程序名称：十进制数转换为N进制数
程序说明：采用除N反向取余的方式，注意特殊数据0的处理
*/ 
#include <iostream>
using namespace std;

string fun(int x,int n)
{
  const string a="0123456789ABCDEF";
  string s="";
  if(x==0)
    return "0";
  while(x>0)
  {
    s=a[x%n]+s;//后取的余数放在前面 
    x=x/n;
  }
  return s;
}

int main()
{ 
  int x,n;
  cin>>x>>n;
  cout<<fun(x,n)<<endl;
}
