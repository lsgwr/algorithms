//田忌赛马 －普通贪心
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define nMax 1010
#define Max(a,b) (a>b?a:b)
#define Min(a,b) (a<b?a:b)

int n,tian[nMax],king[nMax];

int cmp(const void * a, const void *b)
{
  return *(int *)a - *(int *)b;
}

int main()
{
  freopen("horse.in","r",stdin);
  freopen("horse.out","w",stdout);    
  while (scanf("%d", &n) && n)
  {
    for (int i = 0; i < n; ++ i)
	   scanf("%d", &tian[i]);
    for (int i = 0; i < n; ++ i)
	  scanf("%d", &king[i]);
    qsort(tian, n, sizeof(int), cmp);
    qsort(king, n, sizeof(int), cmp);
    int res,max1,max2,min1,min2,cnt; 
    res = 0,max1=max2=n-1, min1=min2=0,cnt=0;
    while ((cnt++) < n)
    {
	  if (tian[max1] > king[max2])
      {
		res += 200;
		max1 --;
		max2 --;
      }
      else if (tian[max1] < king[max2])
      {
		res -= 200;
		min1 ++;
		max2 --;
      }
      else
      {
		if (tian[min1] > king[min2])
		{
		  res += 200;
		  min1 ++;
		  min2 ++;
        }
		else
		{
		  if (tian[min1] < king[max2])
		    res -= 200;
		  min1 ++;
		  max2 --;
		}
      }
    }
    printf("%d\n", res);
  }
  return 0;
}
