//楼梯问题－动规算法１ 
#include <iostream>
using namespace std;
long long f[501][501];

int main()
{ 
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  int i,j,k,n;
  long long sum=0;
  cin>>n;
  for(i=1;i<=n;i++)
    f[i][i]=1;
  for(i=1;i<=n;i++)
    for(j=1;j<i;j++)
      for(k=1;k<=min(i-j,j-1);k++)
        if(i-j>=k) 
          f[i][j]+=f[i-j][k];
  for(i=1;i<n;i++)
    sum=sum+f[n][i];
  cout<<sum<<endl;
  return 0;
}
