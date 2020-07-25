//选数统计 
#include<iostream>
#include <stdlib.h>
using namespace std;
int T,k,i,j,t,N,M,p;
long long a[11][2001],ans;

int main()
{
  freopen("ChoiceNum.in","r",stdin);
  freopen("ChoiceNum.out","w",stdout);   
  scanf("%d%d",&N,&M);
  memset(a,0,sizeof(a));
  for (i=1;i<=M;i++) 
    a[1][i]=1;
  p=1;
  for (k=2;k<=N;k++)
  {
    for (i=p*2;i<=M;i++)
      for (j=p;j<=i/2;j++)
        a[k][i]+=a[k-1][j];
    p*=2;
  }
  ans=0;
  for (i=p;i<=M;i++) 
    ans+=a[N][i];
  printf("%I64d\n",ans);
  return 0;
}
