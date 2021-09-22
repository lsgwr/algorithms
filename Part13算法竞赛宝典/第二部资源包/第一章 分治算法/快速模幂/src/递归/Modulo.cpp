//¿ìËÙÄ£ÃÝ £­µÝ¹é 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <algorithm>
using namespace std;
#pragma warning(disable:4996)

int a, b, c;

long long mi(long long a, int b)
{
  if (b == 1)
    return a % c;
  long long t = mi(a, b / 2);
  return (b & 1) ? (t*t*a) % c : (t*t) % c;
}

int main()
{
  freopen("Modulo.in", "r", stdin);
  freopen("Modulo.out","w",stdout);
  scanf("%d%d%d", &a, &b, &c);
  int ans = mi(a, b);
  printf("%d\n", ans%c);
  return 0;
}
