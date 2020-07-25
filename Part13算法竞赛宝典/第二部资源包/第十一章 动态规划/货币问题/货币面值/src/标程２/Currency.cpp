//货币面值－动规２ 
#include <stdio.h>
#include <stdlib.h>

int Compare(const void * p, const void * q)
{
  return *(int *)p - *(int *)q;
}

int main()
{
  freopen("Currency.in","r",stdin);
  freopen("Currency.out","w",stdout);    
  int N,i,ans,value[100];
  while (scanf("%d", &N) != EOF)
  {
    for (i = 0; i < N; ++i)
  	  scanf("%d", &value[i]);
    qsort(value, N, sizeof(int), Compare);
    ans = 0;
    for (i = 0; i < N; ++i)
      if (value[i] > ans + 1)
		break;
  	  else
		ans += value[i];
    printf("%d\n", ans + 1);
  }
  return 0;
}
