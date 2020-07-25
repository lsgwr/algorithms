//求中间数
#include <iostream>
using namespace std;

int main()
{
  freopen("mid.in","r",stdin);
  freopen("mid.out","w",stdout);  
  int a,b,c;
  cin>>a>>b>>c;
  if((a-b)*(b-c)>=0)
    cout<<b<<endl;
  else if((b-a)*(a-c)>=0)
    cout<<a<<endl;
  else
    cout<<c<<endl;             
  return 0;
}              
