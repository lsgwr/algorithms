//会长的爱好
#include <bits/stdc++.h>
using namespace std;
const int MAXN = 300000;
int a[MAXN], t[MAXN];

int lowbit(int x)
{
  return x & (-x);
}

void updata(int x,int n)
{
  while (x <= n)
  {
    t[x] = a[x];
    for (int i=1; i<lowbit(x); i<<=1)
      t[x] = max(t[x], t[x-i]);
    x += lowbit(x);
  }
}

int query(int x, int y)
{
  int ans = 0;
  while (y >= x)
  {
    ans = max(a[y], ans);
    for (--y; y-lowbit(y)>=x; y-=lowbit(y))
      ans=max(t[y],ans);
  }
  return ans;
}

int main()
{
  int n, m,x,y;
  char c;
  while (scanf("%d%d",&n,&m)!=EOF)
  {
    memset(t,0,sizeof(t));
    for (int i=1; i<=n; i++)
    {
      scanf("%d",&a[i]);
      updata(i,n);
    }
    for (int i=1; i<=m; i++)
    {
      scanf("%c",&c);
      getchar;
      if(c == 'Q')
      {
        scanf("%d%d",&x,&y);
        printf("%d\n", query(x, y));
      }
      if(c == 'U')
      {
        scanf("%d%d",&x,&y);
        a[x] = y;
        updata(x,n);
      }
    }
  }
  return 0;
}

