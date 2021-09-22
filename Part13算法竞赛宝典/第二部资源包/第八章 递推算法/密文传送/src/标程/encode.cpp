//密文传送 
#include <iostream>
#include <stdlib.h>
using namespace std;

int n;
char ss[21];
int num[21];
int ans;

int get_c(int x, int y)//公式为c(x,y)=c(i,y-1),其中i=1~x-1
{
  int s = 0;
  if(y<=2)//不到y==1后用x一个个累加，直接等差公式算更快
  {
    if (y == 2)
    {
      s = (x - 1)*x / 2;
      return s;
    }
    if (y == 1)
      return x;
  }
  for (int i = 1; i < x; i++)
  {
    s += get_c(i, y - 1);
  }
  return s;
}

int get_num(char x)
{
  return x - 'a' + 1;
}

int main()
{
  freopen("encode.in","r",stdin);
  freopen("encode.out","w",stdout);  
  int i, j;
  scanf("%s", &ss);
  n = strlen(ss);
  for (i = 1; i <= n; i++)
    num[i] = get_num(ss[i - 1]);
  if (n > 1)
  {
    for (i = 1; i < n; i++)
	  ans += get_c(26, i);
    for (i = 1; i < n; i++)
    {
      for (j = num[i - 1] + 1; j < num[i]; j++)
		ans += get_c(26 - j, n - i);
    }
    ans += num[n]-num[n - 1];
  }
  else
    ans = num[1];
  printf("%d\n", ans);
}

