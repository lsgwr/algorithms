//安排车厢 
#include <cstdio> 
#include <cstdlib>
#include <iostream>
#define MAXN 1010 
#define IntMax 1000000000 
using namespace std;
  
int n,m,k; 
int sumb[MAXN]; 
int sumg[MAXN]; 
char stu[MAXN]; 
int dp[MAXN]; 
  
int abs(int x) 
{ 
  return x>=0? x:-x; 
} 
  
int ok(int j,int i) 
{ 
  int sumboy = sumb[i] - sumb[j]; 
  int sumgirl = sumg[i] - sumg[j]; 
  return (i-j <= m) && (sumboy==0 || sumgirl==0 || abs(sumboy-sumgirl)<=k); 
} 
  
int main() 
{ 
  freopen("carriage.in","r",stdin);
  freopen("carriage.out","w",stdout);  
  cin>>n>>m>>k; 
  sumb[0]=0; 
  sumg[0]=0; 
  dp[0]=0; 
  for(int i = 1; i <= n; i++)//初始化并求前序列和 
  { 
    scanf("%c\n",stu+i); 
    if(stu[i] == 'B') 
    {
      sumb[i]=sumb[i-1]+1; 
      sumg[i]=sumg[i-1]; 
    }  
    if(stu[i] == 'G') 
    {
      sumg[i]=sumg[i-1]+1; 
      sumb[i]=sumb[i-1]; 
    } 
  }  

  for(int i = 1; i <= n; i++) //动规 
  { 
    dp[i]=IntMax; 
    for(int j = 0; j < i; j++) 
      if(ok(j,i) && dp[j]+1 < dp[i])  
        dp[i] = dp[j]+1;  
  }       
  cout<<dp[n]<<'\n'; 
  return 0; 
}
