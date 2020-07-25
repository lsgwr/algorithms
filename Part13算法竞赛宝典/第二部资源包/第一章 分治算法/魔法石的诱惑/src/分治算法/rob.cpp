//魔法石的诱惑_分治算法 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int solve(int n) 
{
  int ans = 0;
  while (n > 0) 
  {
    ans = ans + n / 5;
    n = n / 5;
  }
  return ans;
}
  
void run() 
{
  freopen("rob.in","r",stdin);
  freopen("rob.out","w",stdout);   
  int Q, i;
  scanf("%d", &Q);
  int start = 1;
  int end = 500000000;
  int ans = 500000001;
  int mid;
  int t;
  while (start <= end)//二分查找 
  {
    int mid = (end - start) / 2 + start;
    int t = solve(mid);
    if (t == Q && mid < ans) 
      ans = mid;
    if (t > Q) 
      end = mid - 1;
    else if ( t < Q ) 
      start = mid + 1;
    else 
      end = mid - 1;
  }
  if (ans != 500000001)
    printf("%d\n", ans);
  else
    printf("No solution\n");
}

int main() 
{
  run();
  return 0;
}
