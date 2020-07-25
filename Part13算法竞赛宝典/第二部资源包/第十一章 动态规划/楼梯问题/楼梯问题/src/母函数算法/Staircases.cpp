//楼梯问题－母函数算法 
#include <iostream>  
using namespace std;

long long ans[510]={1,1};  

int main()
{ 
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  int i,j,N;  
  cin>>N;
  for(i=2;i<=N;i++)  
    for(j=N;j>=0;j--)  
      if(i+j<=N) 
        ans[i+j]+=ans[j]; 
  cout<<ans[N]-1;  
  return 0;  
}
