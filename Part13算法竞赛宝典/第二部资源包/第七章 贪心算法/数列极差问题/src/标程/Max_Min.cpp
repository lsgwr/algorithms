//数列极差问题 
#include <stdio.h>
#include <algorithm>
#include <math.h>
using namespace std;
 
#define MAX 99999999
 
bool cmp (const int a, const int b)
{
  return a > b;
}
 
int main()
{
  freopen("Max_Min.in","r",stdin);
  freopen("Max_Min.out","w",stdout);    
  int n, i, a[50001], tempn, b[50001], min, max;
  while (scanf("%d", &n) == 1 && n)
  {
    for (i = 0; i < n; i++)
    {
      scanf("%d", &a[i]);
      b[i] = a[i];
    }	
    tempn = n;
	while (tempn > 1)
	{
	  sort(a, a+n);
	  a[1] = a[0]*a[1]+1;
	  a[0] = MAX;
	  tempn--;
	}
	max = a[1];
		
	tempn = n;
	while (tempn > 1)
	{
	  sort(b, b+n, cmp);
	  b[1] = b[0]*b[1]+1;
	  b[0] = 0;
	  tempn--;
	}
	min = b[1];
	printf("%0.lf\n", fabs(max-min));
  }
  return 0;
}
