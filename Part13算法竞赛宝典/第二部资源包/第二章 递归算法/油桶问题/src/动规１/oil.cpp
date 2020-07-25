//油桶问题－动规１ 
#include <iostream>
#include <cstdlib>
using namespace std;

int a[100+1];//存放各油桶容积 
char b[60000+1];
int i,j,m,n,MAX;
int main()
{
  freopen("oil.in","r",stdin);
  freopen("oil.out","w",stdout);
  cin>>n>>m;
  for(i=1;i<=n;i++)
    cin>>a[i];
  b[0]=1;
  MAX=0;
  for(i=1;i<=n;i++)
  {
    for(j=MAX;j>=0;j--)
      if(b[j])
      {
         b[j+a[i]]=1;
         if(j+a[i]==m)
         {
           cout<<"yes"<<endl;
           return 0;
         }
       }  
    MAX+=a[i];
  }
  cout<<"no"<<endl;
  return 0;
}
