//²¹Ìì¼Æ»® 
#include<cstdlib>
#include<cstring>
#include<iostream>
#define N 510
using namespace std;

int du[N],ans[N*3];
int map[N][N];
int m,ans_tot,st;

void Init()
{
  scanf("%d",&m);
  int x,y;
  for (int i=1;i<=m;i++)
  {
    scanf("%d%d",&x,&y);
    map[x][y]++;
    map[y][x]++;
    du[x]++;
    du[y]++;
  }
  st=0;
  for (int i=1;i<=500;i++)
    if (du[i]%2==1)
    {
      st=i;
      break;
    }
  if (st==0)
  {
    for (int i=1;i<=500;i++)
      if (du[i])
      {
        st=i;
        break;
      }
  }    
}

void Dfs(int x)
{
  for (int i=1;i<=500;i++)
    if (map[x][i])
    {
      map[x][i]--;
      map[i][x]--;
      du[i]--;
      du[x]--;
      Dfs(i);
    }
  ans[++ans_tot]=x;
}

void Do()
{
  Dfs(st);
  for (int i=ans_tot;i>=1;i--) 
    printf("%d\n",ans[i]);
}

int main()
{
  freopen("fence.in","r",stdin);
  freopen("fence.out","w",stdout);
  Init();
  Do();
  fclose(stdin); fclose(stdout);
  return 0;
}

