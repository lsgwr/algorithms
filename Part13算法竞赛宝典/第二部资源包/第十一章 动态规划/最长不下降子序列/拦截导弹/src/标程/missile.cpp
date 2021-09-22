//导弹拦截 
#include <cstdio>
#include <cstdlib>
#include <string.h>
#include <iostream>
using namespace std;
#define N 10001
int f[N],a[N],longC,Count;//longc为最长序列数,Count为准备套数 
//注意count不要小写 
int main()
{
  int i,j,n=1;
  freopen("missile.in","r",stdin);
  freopen("missile.out","w",stdout);
  while(scanf("%d",&a[n])!=EOF) 
     n++;  //输入数组 
  //最少数量等于反向的LIS 
  for(i=1;i<n;i++) 
  {
    f[i]=1;
    for(j=1;j<=i;j++)
      if(a[i]<a[j]&&f[i]<f[j]+1)
        f[i]=f[j]+1;
    if(longC<f[i]) 
      longC=f[i];
  }  
  memset(f,0,sizeof(f));  
  //最长不下降子序列 
  for(i=1;i<=n;i++)
  {
    f[i]=1;
    for(j=1;j<=i;j++)
      if(a[i]>a[j]&&f[i]<f[j]+1)               
        f[i]=f[j]+1;       
    if(Count<f[i])                 
      Count=f[i];
  }
  printf("%d %d\n",longC,Count);       
  return 0;    
}
