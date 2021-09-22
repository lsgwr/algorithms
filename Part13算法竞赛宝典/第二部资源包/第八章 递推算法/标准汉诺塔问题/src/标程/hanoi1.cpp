//标准汉诺塔问题 
#include <iostream>
using namespace std;

int main()
{
  freopen("hanoi1.in","r",stdin);
  freopen("hanoi1.out","w",stdout);  
  int i,m,n;
  cin>>n;
  m=1;
  for(i=1;i<=n;i++)
    m*=2;
  cout<<m-1<<endl;
  return 0;  
} 
