//奶牛的命运 
#include <cstdio>
#include <limits.h>
#include <cstdlib>
#include <string.h>
#include <iostream>
using namespace std;

long h[1001+10],p[1001+10],pro[1001+10];
long milk[1001+10][250+1];
char del[1001+10];
long k,i,j,round,n;
long ob,Min,ans,ans2,now,change;//change统计有多少天没有杀奶牛了 
char allk;

int main()
{
  freopen("poorcow.in","r",stdin);
  freopen("poorcow.out","w",stdout);  
  scanf("%ld",&k);
  for(round=1;round<=k;round++)
  {
    memset(h,0,sizeof(h));//由于数据有多组，所以要初始化 
    memset(milk,0,sizeof(milk));//m数组为产奶周期 
    memset(del,0,sizeof(del));
    memset(pro,0,sizeof(pro));
    ans=0;
    ans2=0;
    change=0;
    scanf("%ld",&n);//输入奶牛数 
    for(i=1;i<=n;i++)
    {
      scanf("%ld",&p[i]);//输入奶牛产奶周期 
      for(j=1;j<=p[i];j++)
        scanf("%ld",&milk[i][j]);
    }
    while(1)
    {
      ans++;
      for(i=1;i<=n;i++)
        if(!del[i])//若该奶牛没有被杀 
        {
          h[i]=h[i]%p[i]+1;
          pro[i]=milk[i][h[i]];
        }
      Min=LONG_MAX;
      for(i=1;i<=n;i++)
        if(!del[i])
          if(pro[i]<Min)
            Min=pro[i];//找到产奶最少的那只牛 
      now=0;
      for(i=1;i<=n;i++)//看还有没有更多的奶牛产奶一样少 
        if(!del[i])
          if(pro[i]==Min) 
          {
            now++;
            ob=i;
          }
      if(now==1)//若当天只有一头奶牛产奶最少 
      {
        del[ob]=1;//杀了该奶牛 
        ans2=ans;
        change=0;
      }
      else
        change++;
      if(change>500)//若一直没有奶牛被杀，则跳出循环 
        break;
      allk=1;
      for(i=1;i<=n;i++)
        if(!del[i])
          allk=0;
      if(allk)//若全部被杀，跳出循环 
        break;
    }
    ans=0;
    for(i=1;i<=n;i++)
      if(!del[i])
        ans++;
    printf("%ld %ld\n",ans,ans2);
  }
  return 0;
}
