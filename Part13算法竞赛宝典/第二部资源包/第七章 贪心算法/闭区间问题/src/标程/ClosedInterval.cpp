//闭区间问题 
#include <iostream>
#include <cstdio>
#include <algorithm>
using namespace std;
const int maxn=40002;

struct segment
{
  int begin, end;
  segment(int _b=0, int _e=0):begin(_b),end(_e){};
  inline bool operator<( const segment& ss ) const
  {//按照区间的右端点排序
    return end<ss.end || (end==ss.end)&&(begin<ss.begin);
  }
  inline void input()
  {
    scanf("%d %d",&begin, &end);
    if(begin>end)//交换，保证左端点值不比右端点大
    begin^=end, end^=begin, begin^=end;
  }
}seg[maxn];

int main()
{
  freopen("ClosedInterval.in","r",stdin);
  freopen("ClosedInterval.out","w",stdout);  
  int n;
  while(scanf("%d",&n)!=EOF)
  {
    int i, res=1, limit;
    for(i=0; i<n; i++)
      seg[i].input();
    sort(seg,seg+n);
    limit=seg[0].end;
    for(i=1; i<n; i++)
    {//seg[i].begin<=limit的所有区间都是相互相交的，
    //因为这些区间必然有公共点limit，即某一个区间的右端点
      if(seg[i].begin>limit)
        res++, limit=seg[i].end;
    }
    printf("%d\n",n-res);
  }
  return 0;
}
