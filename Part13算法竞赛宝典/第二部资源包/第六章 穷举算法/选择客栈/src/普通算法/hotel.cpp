//选择客栈 －朴素算法
#include <cstdio>
#include <iostream>
#include <cstdlib>
using namespace std;

const int N = 200000 + 10;
int n, k, price, s, c[N], v[N];

int main() 
{
  freopen("hotel.in", "r", stdin);
  freopen("hotel.out", "w", stdout);
  scanf("%d %d %d", &n, &k, &price);
  for(int i=1; i<=n; i++)
    scanf("%d %d", c+i, v+i);
  for(int i=1; i<n; i++) 
  {
    int m = v[i];
    for(int j=i+1; j<=n; j++) 
    {
      if(m > v[j])//找出区间消费值最小的咖啡店 
        m = v[j];
      s += (c[i] == c[j] && m <= price);//如满足条件则加1 
    }
  }
  printf("%d\n", s);
  return 0;
}
