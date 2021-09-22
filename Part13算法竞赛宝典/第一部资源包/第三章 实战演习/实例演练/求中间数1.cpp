/*
程序名称：三实数找中间数(方法1)  
程序说明：逐层判断法，锻炼逻辑思维 
*/ 
#include <iostream>
using namespace std;
int main()
{
  freopen("mid.in","r",stdin);
  freopen("mid.out","w",stdout);  
  int a,b,c;
  cin>>a>>b>>c;
  if(a>b)
  {
    if(a>c)
    {
      if(b>c)
        cout<<b<<endl;
      else
        cout<<c<<endl;
    }
    else
      cout<<a<<endl;
  }
  else
  {
    if(b>c)
    {
      if(a>c)
        cout<<a<<endl;
      else
        cout<<c<<endl;
    }
    else
      cout<<b<<endl;
  }
  return 0;
}     
