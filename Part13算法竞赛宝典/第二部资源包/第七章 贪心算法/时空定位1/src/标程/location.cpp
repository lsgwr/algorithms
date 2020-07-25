//Ê±¿Õ¶¨Î»1 
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <algorithm>
#include <math.h>
using namespace std;

const int MAXN = 610;

int N, n;
double data[MAXN];
double ll[MAXN];

void init()
{
  int i;
  for (i = 1; i <= n; i++)
    scanf("%lf", &data[i]);
}

bool cmp(double x, double y)
{
  return x > y;
}

int main()
{
  freopen("location.in", "r", stdin);
  freopen("location.out", "w", stdout);
  scanf("%d", &N);
  int i, j;
  double length;
  for (i = 1; i <= N; i++)
  {
    length = 0;
    scanf("%d", &n);
    init();
    sort(data + 1, data + 1 + n, cmp);
    for (j = 1; j <= n; j++)
    {
	  ll[j] = 2 * (sqrt(data[j] * data[j] - 1));
      length += ll[j];
      if (length >= 20)
		break;
    }
    printf("%d\n", j);
  }
  return 0;
}
