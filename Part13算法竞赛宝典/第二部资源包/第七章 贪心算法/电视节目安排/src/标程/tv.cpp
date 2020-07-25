//电视节目安排 
#include<cstdio>
#include<cstring>
#include<cstdlib>
const int MAXN = 105;
typedef struct
{
    int s, e;
}T;

T Ti[MAXN];

int cmp( const void *_p, const void *_q)
{
    T *p = ( T *)_p;
    T *q = ( T *)_q;
    return p -> e - q -> e;
}
int n;
int main()
{
  freopen("tv.in","r",stdin);
  freopen("tv.out","w",stdout);
  while( scanf( "%d", &n), n)
  {
    int cur;
    for( int i = 0; i < n; i ++)
      scanf( "%d%d", &Ti[i].s, &Ti[i].e);
    qsort( Ti, n, sizeof Ti[0], cmp);
    cur = Ti[0].e;
    int ans = 1;
    for( int i = 1; i < n; i ++)
    {
      if( Ti[i].s >= cur) 
      {
        cur = Ti[i].e;
        ans ++;
      }
    }
    printf( "%d\n", ans);
  }
  return 0;
}
