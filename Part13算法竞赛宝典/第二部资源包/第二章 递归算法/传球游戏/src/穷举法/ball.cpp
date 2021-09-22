//传球游戏－穷举法 
#include <iostream>
#include <stdlib.h>

using namespace std;

int i, j, m, n;
int sum;

int c(int m, int n)//计算C(m,i)
{
  int i;
  long long s;
  int p1,p2;
  if (n == 0)
    return 1;
  s = 1;	
  
  /*for (i = m; i >= m - n + 1; i--)//如5取3，则根据乘法原理先计算5*4*3的值  
    s = s * i;
  for (i = n; i >= 2; i--)//除去相同的排列，如123,132,321,312,213,231算一种 
    s = s / i;*/

  p1 = 2;
  for (p2 = m; p2 >= m - n + 1; p2--)
  {
    s *= p2;
    if (s%p1 == 0 && p1<=n)
    {
	  s /= p1;
      p1++;
    }
  }
  return s;
}

int main()
{
  freopen("ball.in","r",stdin);
  freopen("ball.out","w",stdout);  
  cin >> n >> m;//n个人m次
  for (i = 0; i <= m; i++)
  {
    j = m - i;
    if (abs(j - i) % n == 0)//球最终传回来 
      sum = sum + c(m, i);
  }
  cout << sum << "\n";
  return 0;
}

