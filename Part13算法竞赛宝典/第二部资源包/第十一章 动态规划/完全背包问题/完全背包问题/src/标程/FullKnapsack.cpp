//完全背包问题 
#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{ 
  freopen("FullKnapsack.in","r",stdin);
  freopen("FullKnapsack.out","w",stdout);
  int m,n,j,i,t,c[300],w[300],f[2000]={0};
  cin>>m>>n;
  for(i=1;i<=n;i++)
    cin>>w[i]>>c[i];
  for(i=1;i<=m;i++)
    for(j=1;j<=n;j++)
       if(i>=w[j])
       { 
         t=f[i-w[j]]+c[j];
         if(t>f[i])
            f[i]=t;
       }  
  cout<<f[m]<<endl; 
}
