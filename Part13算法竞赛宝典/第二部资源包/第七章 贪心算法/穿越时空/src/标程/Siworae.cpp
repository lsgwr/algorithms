//穿越时空 
#include <iostream>
#include <cstdlib>
#include <algorithm>
using namespace std;
#define M 113

int k,n;
int s[M][M],v[M];

int main()
{
  freopen("Siworae.in","r",stdin);
  freopen("Siworae.out","w",stdout);  
  int i,j,t;
  int x1,y1,x2,y2,mx,my;
  int ans,tx;
  scanf("%d",&t);
  while(t--)
  {
    scanf("%d%d",&n,&k);
    memset(v,0,sizeof(v));//记录每列时空乱流数
    memset(s,0,sizeof(s));//记录时空乱流分布
    mx=0;my=0;//统计当前图区域
    for(i=0;i<n;i++)
    {
      scanf("%d%d%d%d",&x1,&y1,&x2,&y2);
      if(x1<=x2)
        for(j=x1;j<=x2;j++)
        {
          s[j][y1]=i+1;
          v[j]++;
        }
      else
        for(j=x2;j<=x1;j++)
        {
          s[j][y1]=i+1;
          v[j]++;
        }
      if(x1>mx) 
        mx=x1;
      if(x2>mx) 
        mx=x2;
      if(y1>my) 
        my=y1;
      if(y2>my) 
        my=y2;
    }
    ans=0;
    for(i=0;i<=mx;i++)
    {
      if(v[i]<=k) 
        continue;
      for(j=0;j<v[i]-k;j++)//该行需移走的时空乱流数
      {
        y1=0,y2=-1;
        for(x1=0;x1<=my;x1++)
        {
          if(s[i][x1]==0) 
            continue;
          for(x2=i,tx=0;s[x2][x1]==s[i][x1];x2++)
             if(v[x2]>k)
               tx++;       
          if(tx>y1)
          {
            y1=tx;
            y2=x1;
          }
        }
        for(x2=i+1,x1=y2;s[x2][x1]==s[i][x1];x2++)
        {
          s[x2][x1]=0;
          v[x2]--;
        }
        ans++;
      }
    }
    printf("%d\n",ans);
  }
  return 0;
}
