//近似整数－追赶法 
#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <algorithm>
using namespace std;

int L;
double f;

int main()
{
  freopen("Approximation.in", "r", stdin);
  freopen("Approximation.out", "w", stdout);
  scanf("%lf%d", &f, &L);
  int ansn, ansd;
  int n = 1, d = 1;
  double min = 99999999, cha;
  while(n <= L && d <= L)
  {
    cha = f - (double)n / d;
    if (min > fabs(cha))
    {
      min = fabs(cha);
      ansn = n;
      ansd = d;
    }
    if (cha > 0)
      n++;
    else
      d++;
  }
  printf("%d %d\n", ansn, ansd);
}
