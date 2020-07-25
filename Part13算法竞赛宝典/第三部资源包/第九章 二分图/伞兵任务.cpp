//É¡±øÈÎÎñ
#include <stdio.h>
#include <string.h>
int g[125][125];
bool v[125];
int link[125];
int m,n;
int find(int x)
{
  int i;
  for (i=1;i<=m;i++)
  {
    if (g[x][i] && !v[i])
    {
      v[i]=1;
      if (!link[i] || find(link[i]))
      {
        link[i]=x;
        return 1;
      }
    }
  }
  return 0;
}

int main ()
{
  int t,i,x,y;
  scanf("%d",&t);
  while (t--)
  {
    scanf("%d %d",&m,&n);
    memset(g,0,sizeof(g));
    for (i=0;i<n;i++)
    {
      scanf("%d %d",&x,&y);
      g[x][y]=1;
    }
    int sum=0;
    memset(link,0,sizeof(link));
    for (i=1;i<=m;i++)
    {
      memset(v,0,sizeof(v));
      if (find(i)) 
        sum++;
    }
    printf ("%d\n",m-sum);
  }
  return 0;
}
