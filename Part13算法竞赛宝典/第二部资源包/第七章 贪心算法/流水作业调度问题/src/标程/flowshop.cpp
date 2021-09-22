//流水作业调度问题
#include <iostream>
#include <cstdlib>
#include <algorithm>
using namespace std;
const int maxn = 10005;

struct node
{
  int x,y;
}a[maxn],b[maxn],c[maxn];

bool cmp1(const node &p, const node &q) 
{
  return p.x < q.x;
}

bool cmp2(const node &p, const node &q)
{
  return p.y > q.y;
}

int n,s1sum[maxn],sum;

int main()
{
  freopen("flowshop.in","r",stdin);
  freopen("flowshop.out","w",stdout);
  while(scanf("%d",&n) && n)
  {
    for(int i=0;i<n;++i)
      scanf("%d%d",&a[i].x,&a[i].y);
    int len1 = 0, len2 = 0;
    for(int i=0;i<n;++i)
    {
      if(a[i].x < a[i].y)
      {
        b[len1].x = a[i].x; //子集1 x<y
        b[len1++].y = a[i].y;
      }
      else
      {
        c[len2].x = a[i].x;  //子集2
        c[len2++].y = a[i].y;
      }
    }
    sort(b,b+len1,cmp1);//子集1作业顺序：x不减
    sort(c,c+len2,cmp2);//子集2作业顺序：y不增
    for(int i = len1;i<len1+len2;++i)
      b[i] = c[i-len1];
    s1sum[0] = 0;
    for(int i=0;i<n;++i)
      s1sum[i+1] = s1sum[i]+b[i].x;
    sum = 0;
    for(int i=0;i<n;++i)
    {
      if(sum<s1sum[i+1]) 
        sum = s1sum[i+1]+b[i].y;
      else 
        sum+=b[i].y;
    }
    printf("%d\n",sum);
  }
  return 0;
}
