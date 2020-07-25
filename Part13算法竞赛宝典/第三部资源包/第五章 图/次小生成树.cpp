//次小生成树１ 
#include<stdio.h>
#include<stdlib.h>
#include <iostream>
using namespace std;
#define maxn 10000

struct data
{
  int x;
  int y;
  int w;
}sides[maxn+1]; //记录边的起点和终点，还有权值
int set[110];//并查集的数组
bool flag[maxn+1];//记录某条边在最小生成树中是否使用过，用过则赋值为1

int cmp(const void *a,const void *b)
{
  return ((data *)a)->w - ((data *)b)->w;
}

int find(int x) //寻找根节点；
{
  if(set[x]==x) 
    return x;
  set[x]=find(set[x]); //寻找的过程中，更新set[x]的值，路径压缩；
  return set[x];
}

int main()
{
  int n,i,j,ans,t,ans1,rx,ry,before,m;
  bool OK;//标志是否找到答案；
  scanf("%d%d",&n,&m);
  OK=0;
  for(i=1;i<=m;i++)
    scanf("%d%d%d",&sides[i].x,&sides[i].y,&sides[i].w);
  qsort(&sides[1],m,sizeof(sides[0]),cmp);   //贪心，排序，按权值；
  for(i=1;i<=n;i++) 
    set[i]=i;//并查集初始化；
  for(i=1;i<=m;i++)//初始化为未被使用过 
    flag[i]=0;
  ans=0;
  i=0;
  j=0;
  while(i<n-1)//kruscal
  {
    j++;
    rx=find(sides[j].x);//寻找根节点
    ry=find(sides[j].y);
    if(rx!=ry)
    {
      i++;
      ans+=sides[j].w;
      set[rx]=set[ry]; //合并集合 ；
      flag[j]=1;
    }   
  }
  if(m==n-1)//唯一最小生成树，直接输出结果 
  {
    printf("%d\n",ans);
    return 0;
  }
  //开始寻找次小生成树   
  before=1;
  flag[before]=0;
  for(t=1;t<=n-1;t++) //进行n-1次删边的操作。。
  {
    for(i=1;i<=n;i++)//重更新并查集数组 
      set[i]=i;
    i=0;
    j=0;
    ans1=0;
    while(i<n-1)//同样的操作，求最小生成树
    {
      j++;
      if(j!=before)//每次删除一条边
      {
        rx=find(sides[j].x);
        ry=find(sides[j].y);
        if(rx!=ry)
        {
          i++;
          ans1+=sides[j].w;
          set[rx]=set[ry];//并到同一集合 
        }   
      }
    }
    if(ans1==ans) 
    { 
      OK=1; break;
    }
    for(i=before+1;i<=m;i++)//寻找一条边，赋值before，给删除用的
      if(flag[i])//如果该边在最小生成树中 
      { 
        before=i; //确定下一个要删除的边 
        flag[before]=0; 
        break;
      } 
  }
  if(OK) 
    printf("Not Unique\n");
  else 
    printf("%d\n",ans);
  system("pause");
  return 0;
}
