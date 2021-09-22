/*
程序名称：N进制转为十进制 
*/ 
#include <iostream>
using namespace std;

int fun(int n,string s)
{
  int i,t=0;
  for(i=0;i<=s.size();i++)
  {
    if(s[i]>='0' && s[i]<='9')
      t=t*n+s[i]-48;
    else if(s[i]>='A' && s[i]<='F')
      t=t*n+s[i]-55;
    else if(s[i]>='a' && s[i]<='f')
      t=t*n+s[i]-87;
  }
  return t;        
}

int main()
{
  int n;
  string str;
  cin>>n;
  cin>>str;
  cout<<fun(n,str)<<endl;
  system("pause");
}
