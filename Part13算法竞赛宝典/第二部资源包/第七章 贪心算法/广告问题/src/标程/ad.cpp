//广告问题 
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define MAXD 20010
#define MAXN 1010
#define D 10000
int a[MAXN], b[MAXN], K, N, r[MAXN], hash[MAXD];

int cmp(const void *_p, const void *_q)
{
  int *p = (int *)_p;
  int *q = (int *)_q;
  return b[*p] - b[*q];
}

void init()
{
  int i, j, k;
  scanf("%d%d", &K, &N);
  for(i = 0; i < N; ++ i)
  {
    scanf("%d%d", &a[i], &b[i]);
    if(a[i] > b[i])
      k = a[i], a[i] = b[i], b[i] = k;
    a[i] += D, b[i] += D;
  }
}

void solve()
{
  int i, j, k, e, ans;
  for(i = 0; i < N; ++ i)
    r[i] = i;
  qsort(r, N, sizeof(r[0]), cmp);
  memset(hash, 0, sizeof(hash));
  ans = 0;
  for(i = 0; i < N; i ++)
  {
    k = 0;
    e = r[i];
    for(j = a[e]; j <= b[e]; ++ j)
      if(hash[j])
        ++ k;
    for(j = b[e]; j >= a[e] && k < K; -- j)
      if(!hash[j])
      {
        hash[j] = 1;
        ++ k;
        ++ ans;
      }
  }
  printf("%d\n", ans);
  for(i = 0; i <= 20000; ++ i)
    if(hash[i])
      printf("%d\n", i - D);
}

int main()
{
  freopen("ad.in","r",stdin);
  freopen("ad.out","w",stdout);  
  int t, tt;
  scanf("%d", &t);
  for(tt = 0; tt < t; ++ tt)
  {
    init();
    if(tt)
      printf("\n");
    solve();
  }
  return 0;
}
