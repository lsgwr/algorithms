//求中间数
#include <iostream>
using namespace std;

int main()
{
  freopen("mid.in","r",stdin);
  freopen("mid.out","w",stdout);  
  int a,b,c,d;
  cin>>a>>b>>c;
  if(b>a)
  {d=a;a=b;b=d;}
  if(c>a)
  {d=c;c=a;a=d;}
  if(b>c)
    cout<<b<<endl;
  else
    cout<<c<<endl;  
  return 0;
}               
