//…±À¿ªµ»À
#include <iostream>
using namespace std;

int jo(int m,int k)
{
int s=0,t=2*m;
  for(int i=1;i<=m;++i)
  {
    s=(s-1+k)%t;
    --t;
    if(s<m)
     return 0;
   }
 return 1;
}

int main()
{
  int m,i,k;
  cin>>m; 
  k=m+1;
  while(!jo(m,k))
    ++k;
  cout<<k<<endl;
return 0;
}
