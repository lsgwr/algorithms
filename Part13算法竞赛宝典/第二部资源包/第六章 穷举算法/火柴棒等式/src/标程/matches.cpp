//火柴棒等式 
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;
int need[]={6,2,5,5,4,5,6,3,7,6};//保存十个数字使用的火柴数 
int M[2000];
int ans;

int match(int cur)//返回使用的火柴数 
{
  int ans=0,ret;
  if(cur==0) 
    return 6;
  while(cur>0)
  {
    ret=cur%10;
    ans+= need[ret];
    cur/=10;
  }
  return ans;
}

void GetMatch()//事先将数字从0到1999使用的各自火柴数存到match[] 
{
  for(int i=0;i<=1999;++i)
    M[i]=match(i);   
}

int main()
{
  freopen("matches.in","r",stdin);
  freopen("matches.out","w",stdout);
  int n=24;
  scanf("%d",&n);
  GetMatch();
  for(int i=0;i<=1000;i++)
    for(int j=i;j<=1000;j++)//此处有个优化 
    {
      int a=M[i];  
      int b=M[j];
      if(a+b>n-6)//右边至少需要两根火柴，＝和+号需要四根火柴 
        continue;
      int d=M[i+j];
      if(a+b+d+4==n && i!=j) 
        ans+=2;
      if(a+b+d+4==n && i==j)
        ans++;  
    }
  printf("%d\n",ans);
  return 0;
}
