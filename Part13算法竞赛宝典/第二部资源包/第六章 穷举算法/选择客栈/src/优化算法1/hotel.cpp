//选择客栈 －优化算法１
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;
const int N = 200000 + 10, K = 60;
int n, k, price, c, v, s;
int a[K], f[K][N];
int main() 
{
  freopen("hotel.in", "r", stdin);
  freopen("hotel.out", "w", stdout);
  scanf("%d %d %d", &n, &k, &price);
  for(int i=1; i<=n; i++) 
  {
    scanf("%d %d", &c, &v);
    for(int j=0; j<k; j++)//穷举所有颜色 
      f[j][i] = f[j][i-1] + (j == c);//颜色相等则加一 
    if(v <= price) 
      s += f[c][a[i] = i] - 1;//如咖啡店满足最低消费
    else
      s += f[c][a[i] = a[i-1]];
  }
  printf("%d\n", s);
  return 0;
}
