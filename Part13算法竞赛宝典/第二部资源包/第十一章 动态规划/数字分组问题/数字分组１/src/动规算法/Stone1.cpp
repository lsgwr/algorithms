//Êý×Ö·Ö×é£±-DP 
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <limits.h>
using namespace std;

int a[22];
int dp[2500000];

int main()
{
  freopen("Stone1.in","r",stdin);  
  freopen("Stone1.out","w",stdout);  
  int n;
  cin>>n;
  int sum = 0;
  for(int i=0; i<n; i++)
  {
    cin>>a[i];
    sum += a[i];
  }
  for(int i=0; i<n; i++)
    for(int k=sum; k>=a[i]; k--)
      dp[k] = max( dp[k],dp[k-a[i]] + a[i]);
  int mmin = INT_MAX;
  for(int i=0; i<=sum; i++)
    if( dp[i] && abs(sum-dp[i]-dp[i]) < mmin )
	  mmin = abs(sum-dp[i]-dp[i]);
  cout<<mmin<<'\n';
  return 0;
}
