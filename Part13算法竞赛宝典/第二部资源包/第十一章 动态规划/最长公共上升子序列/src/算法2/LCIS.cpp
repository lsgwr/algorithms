//LCIS 优化算法2 
#include <stdlib.h>
#include <iostream>
#include <math.h>
#include <algorithm>
#include <string.h>
#include<cstdio>
#define MEM(a) memset(a,0,sizeof(a))
typedef long long ll;
using namespace std;
const ll INF = 2147483648;//数据中出现了末尾为 2^31-1的情况
const int MAXN = 510;
int f[MAXN][2], ans[MAXN];
ll a[MAXN], b[MAXN];
struct zt
{
  int i, j;
  zt(){}
  zt(int x, int y) { i = x, j = y; }//构造函数
}from[MAXN][MAXN], t;//记录转移来源

inline int MAX(int a, int b)
{ 
  return (a > b) ? a : b; 
}

void work()
{
  int i, j, la, lb, flag = 0, temp, t1, t2;
  scanf("%d", &la);
  for (i = 1; i <= la; ++i) 
    cin >> a[i];
  scanf("%d", &lb);
  for (i = 1; i <= lb; ++i) 
    cin >> b[i];
  a[++la] = b[++lb] = INF;//末尾增加一个数
  for (j = 1; j <= lb; ++j)
  {
    temp = 0;
    t = zt(0, 0);
    for (i = 1; i <= la; ++i)
    {
	  if (a[i] == b[j])
	  {
		f[i][flag] = temp + 1;
		from[i][j] = t;
      }
      else
      {
		f[i][flag] = f[i][flag ^ 1];
		from[i][j] = zt(i, j - 1);
      }
      if (temp < f[i][flag ^ 1] && b[j]>a[i])
      {
		temp = f[i][flag ^ 1];
		t = zt(i, j - 1);
      }
    }
    flag ^= 1;
  }
  printf("%d\n", f[la][flag ^ 1] - 1);
  t = from[la][lb];
  i = f[la][flag ^ 1];
  while (t.i || t.j)
  {
    if (a[t.i] == b[t.j]) 
      ans[--i] = a[t.i];
    t = zt(from[t.i][t.j].i, from[t.i][t.j].j);
  }
  for (i = 1; i < f[la][flag ^ 1]; ++i) 
    printf("%d ", ans[i]);
  printf("\n");
}

int main()
{
  freopen("LCIS.in","r",stdin);
  freopen("LCIS.out","w",stdout);
  work();
  return 0;
}
