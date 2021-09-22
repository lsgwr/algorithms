//选择客栈 －优化算法２
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;
const int K = 60;
int n, k, price, c, v, s, f;
int a[K], b[K], u[K];

int main() 
{
  freopen("hotel.in", "r", stdin);
  freopen("hotel.out", "w", stdout);
  scanf("%d %d %d", &n, &k, &price);
  for(int i=1; i<=n; i++) 
  {
    scanf("%d %d", &c, &v);
    if(v <= price)//如有符合最低消费的咖啡店 
      f = i;
    if(f >= u[c]) 
      b[c] = a[c];
    s += b[c];
    a[c]++;//颜色为 c 的客栈数加一 
    u[c] = i;//更新存储上一个颜色为 c 的客栈编号 
  }
  printf("%d\n", s);
  return 0;
}
