//树状数组求逆序对
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;
int t[10000000];
int n,ans=0;

void modify(int k)
{
  for(; k<=n;k+=(-k)&k) 
   t[k]+=1;//个数加一 
}

int GetSum(int k)
{
  int sum=0;
  for(;k>0;k-=(-k)&k)
    sum+=t[k];
  return sum;
}

int main()
{
  freopen("reverse.in","r",stdin);
  freopen("reverse.out","w",stdout);
  int num;
  scanf("%d",&n);
  for(int i=1; i<=n ;++i)
  {
    scanf("%d",&num);
    modify(num);//个数加一
    ans+=i-GetSum(num);
  }
  printf("%d\n",ans);
  return 0;
}
