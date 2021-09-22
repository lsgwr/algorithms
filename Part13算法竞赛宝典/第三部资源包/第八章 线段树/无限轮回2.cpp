//无限轮回－线段树
#include <bits/stdc++.h>
using namespace std;
#define LL(x) ((x)<<1)  //两倍；
#define RR(x) ((x)<<1|1)  //两倍+1；

struct Seg_Tree
{
  int left,right,val;
  int calmid()
  {
    return (left+right)/2;
  }
} tt[15000];

int val[5001];//保存序列

void build(int left,int right,int idx)
{
  tt[idx].left = left;
  tt[idx].right = right;
  tt[idx].val = 0;
  if(left == right)
    return ;
  int mid = tt[idx].calmid();
  build(left,mid,LL(idx));
  build(mid+1,right,RR(idx));
}

//查询在[left，right]区间中有多少数，即比left大的逆序数
int query(int left,int right,int idx)
{
  if(left == tt[idx].left && right == tt[idx].right)
    return tt[idx].val;
  int mid = tt[idx].calmid();
  if(right <= mid)
    return query(left,right,LL(idx));
  else if(mid < left)
    return query(left,right,RR(idx));
  else
    return query(left,mid,LL(idx)) + query(mid+1,right,RR(idx));
}

//更新所有包含id这个数的区间的val值，都+1；
void update(int id,int idx)
{
  tt[idx].val ++;
  if(tt[idx].left == tt[idx].right)
    return ;
  int mid = tt[idx].calmid();
  if(id <= mid)
    update(id,LL(idx));
  else
    update(id,RR(idx));
}

int main()
{
  int n;
  while(scanf("%d",&n) == 1)
  {
    build(0,n-1,1);
    int sum = 0;
    for(int i=0; i<n; i++)
    {
      scanf("%d",&val[i]);
      sum += query(val[i],n-1,1);//此时val[i]还未插入树中
      update(val[i],1);//此时插入val[i]，即更新树
    }
    int ret = sum;
    for(int i=0; i<n; i++)
    {
      sum = sum - val[i] + (n - val[i] - 1);
      ret=min(ret,sum);
    }
    printf("%d\n",ret);
  }
  return 0;
}

