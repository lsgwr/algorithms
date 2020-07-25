//求中间数
#include <iostream>
using namespace std;

int main()
{
  freopen("mid.in","r",stdin);
  freopen("mid.out","w",stdout);  
  int a,b,c;
  cin>>a>>b>>c;
  if(a>b && b>c)
    cout<<b<<endl;
  if(c>b && b>a)
    cout<<b<<endl;
  if(b>a && a>c)
    cout<<a<<endl;
  if(c>a && a>b)
    cout<<a<<endl;
  if(a>c && c>b)
    cout<<c<<endl;
  if(b>c && c>a)
    cout<<c<<endl;          
  return 0;
}                
