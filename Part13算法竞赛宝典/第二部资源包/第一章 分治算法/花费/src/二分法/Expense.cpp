//Ivan Zhang
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <algorithm>
using namespace std;

int n, m;
int data[100001];
int high, low, mid;

bool judge()
{
  int i;
  int temp = 0;
  int plan = 1;
  for (i = 1; i <= n; i++)
  {
    temp += data[i];
    if (temp > mid)
    {
      plan++;
      temp = data[i];
    }
  }
  if (plan > m)
    return 0;
  else
    return 1;
}

int main()
{
  freopen("Expense.in","r",stdin);
  freopen("Expense.out","w",stdout);  
  scanf("%d%d", &n, &m);
  int i;
  for (i = 1; i <= n; i++)
  {
    scanf("%d", &data[i]);
    high += data[i];
    if (low < data[i])
	  low = data[i];
  }
  while (low <= high)//==
  {
    mid = (low + high) >> 1;
    if (!judge())
      low = mid + 1;
    else
      high = mid - 1;
  }
  printf("%d\n", low);
  return 0;
}
