/*城市交通 

  f[i][j][m] = min{f[i][k][t] + f[k][j][m - t]} 
    
  d[m][i][j] 表示i 到 j间添加 m 条路的中间结点k
  l[m][i][j] 表示i 到 k所添加的路条数
  r[m][i][j] 表示k 到 j所添加的路条数
  显然 l[m][i][j] + r[m][i][j] == m
  深搜进行输出  
*/
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <algorithm>
using namespace std;

const int MAXN = 50 + 10;
const int INF = 10000 + 10;

typedef int int2[MAXN][MAXN];

int n, M;
int2 f[11], d[11];
int2 l[11], r[11];

struct Que
{
  int x, y, t;
}q[MAXN];
int cnt;

void pushinq(int x, int y, int t)
{
  int temp;
  Que &q1 = q[++cnt];
  q1.x = x;
  q1.y = y;
  q1.t = t;
  if (q1.x>q1.y)
    temp = q1.x, q1.x = q1.y, q1.y = temp;
}

void init1()
{
  memset(l, 0, sizeof(l));
  memset(r, 0, sizeof(r));
  memset(d, 0, sizeof(d));
  for (int i = 1; i <= n; ++i)
    for (int j = 1; j <= n; ++j)
      for (int k = 0; k <= M; ++k)
        f[k][i][j] = INF;
}

int comp (const void *x, const void *y)
{
  Que &a = *(Que *)x;
  Que &b = *(Que *)y;
  if (a.x == b.x)
    return a.y - b.y;
  else
    return a.x - b.x;
}

void init()
{
  for (int i = 1; i <= n; ++i)
    for (int j = 1; j <= n; ++j)
      if (f[0][i][j] != INF)
        for (int m = 1; m <= M; ++m)
          f[m][i][j] = f[m][i][j] = f[m - 1][i][j] / 2;
}

void print()
{
  for (int k = 0; k <= M; ++k)
  {
    printf("%d:\n", k);
    for (int i = 1; i <= n; ++i)
    {
      for (int j = 1; j <= n; ++j)
        printf("%d ", f[k][i][j]);
      printf("\n");
    }
    printf("\n\n");
  }
}

bool dfs(const int &m,const int &i, const int &j)
{
  if (d[m][i][j])
  {// 如果i j之间有结点，那么进行添加
   // 返回值表示是否能连成一条固有线段 只有能了才添加
    if (l[m][i][j] && dfs(l[m][i][j], i, d[m][i][j]))
      pushinq(i,d[m][i][j],l[m][i][j]);
    if (r[m][i][j] && dfs(r[m][i][j], d[m][i][j], j))
      pushinq(d[m][i][j],j,r[m][i][j]);
    return 0;
  }
  return 1;
}

int main()
{
  freopen("traffic.in", "r", stdin);
  freopen("traffic.out", "w", stdout);
  int x, y, w;
  scanf("%d %d", &n, &M); 
  init1();
  while (scanf("%d %d %d", &x, &y, &w) != EOF && x)
  {
    f[0][x][y] = f[0][y][x] = w;
    //print();
  }
  init();
  if (M == 0)//针对 0 的特判， 就是最初始的floyd
  {
    for (int k = 1; k <= n; ++k)
      for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= n; ++j)
          if(f[0][i][k]!=INF&&f[0][k][j]!=INF &&(f[0][i][j] == INF
                        || f[0][i][j] > f[0][i][k] + f[0][k][j]))
             f[0][i][j] = f[0][i][k] + f[0][k][j];
  }
  else//五重循环
  { 
    for (int m = 1; m <= M; ++m)
      for (int t = 1; t <= m; ++t)
        for (int k = 1; k <= n; ++k)
          for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
              if (f[m - t][i][k] != INF && f[t][k][j] != INF &&
                 (f[m][i][j]==INF||f[m][i][j]>f[m-t][i][k]+f[t][k][j]))
              {
                f[m][i][j] = f[m - t][i][k] + f[t][k][j];
                d[m][i][j] = k;
                l[m][i][j] = m-t;
                r[m][i][j] = t;
              }
  }
  //print();
  printf("%d\n", f[M][1][n]);
  if (M)//显然M == 0 时无添加路径
  {
    dfs(M, 1, n); // 深搜进去找所添加的路径
    qsort(&q[1], cnt, sizeof(Que), comp);
    for (int i = 1; i <= cnt; ++i)
      for (int j = 1; j <= q[i].t; ++j)
      {
        //这重循环是因为一条路上可以添加不止1条路径
        printf("%d %d\n", q[i].x, q[i].y);
      }
  }
  return 0;
}
