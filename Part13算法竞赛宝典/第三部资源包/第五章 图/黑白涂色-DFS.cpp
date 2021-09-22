//黑白涂色—朴素DFS
#include <bits/stdc++.h>
using namespace std;

int Map[110][110];
int color[110],tmp[110];//tmp[]临时存储最大团结点
int len,maxlen;
int index,n,k;

void DFS(int num)
{
  int i;
  if(num==n)  //num==n,搜寻完毕
  {
    if(len>maxlen)//更新最优解
    {
      maxlen=len;
      for(i=1,index=0; i<=n; i++)
        if(color[i])
          tmp[index++]=i;
    }
    return;
  }
  for(i=1; i<=n; i++)
    if(i!=num && Map[i][num] && color[i])//搜寻周围的点是否有被着色
      break;
  if(i>n) //i>n表示没有被着色
  {
    color[num]=1;  //周围点没有被着色，那这个点就可以着色了
    len++;         //长度加1
    DFS(num+1);    //搜寻下一个点
    color[num]=0;  //恢复搜索前状态
    len--;         //恢复搜索前状态
  }
  DFS(num+1);  //搜寻下一个点
}

int main()
{
  int t;
  scanf("%d",&t);
  while(t--)
  {
    memset(Map,0,sizeof(Map));
    memset(color,0,sizeof(color));
    len=maxlen=0;
    scanf("%d%d",&n,&k);
    while(k--)
    {
      int x,y;
      scanf("%d%d",&x,&y);
      Map[x][y]=Map[y][x]=1;
    }
    DFS(1);
    printf("%d\n%d",maxlen,tmp[0]);
    for(int i=1; i<maxlen; i++)
      printf(" %d",tmp[i]);
    printf("\n");
  }
  return 0;
}
