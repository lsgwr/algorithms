//致命武器
#include <bits/stdc++.h>
using namespace std;
#define lson l,m,k<<1
#define rson m+1,r,k<<1|1
#define N 100001

int st[N<<2];//st数组里面记录的是标记，0,1,2,3;0代表杂色，不是纯色
int flag;

void down(int &k)
{
  st[k<<1]=st[k<<1|1]=st[k];//左儿子和右儿子均设为该颜色
  st[k]=0;//设为杂色
}

void build(int l,int r,int k)//建线段树，均初始为1
{
  st[k]=flag;
  if(l==r)
    return ;
  int m=(l+r)>>1;//二分
  build(lson);
  build(rson);
}

void updata(int &L,int &R,int l,int r,int k)//更新L-R，要从1-n,st[1]开始
{
  if(L<=l && R>=r)//如果更新部分恰好被包含在某线段树内
  {
    st[k]=flag;//则整个一段都更新为新颜色
    return ;
  }
  if(st[k])//如果该结点为纯色，则左右儿子颜色也为该纯色
    down(k);
  int m=(l+r)>>1;
  if(L<=m)
    updata(L,R,lson);//递归更新左儿子
  if(R>m)
    updata(L,R,rson);//递归更新右儿子
}

int query(int l,int r,int k)
{
  if(st[k])  //为纯色时，直接成段相乘得出结果，并返回
    return st[k]*(r-l+1);
  int m=(l+r)>>1,t1,t2;
  t1=query(lson);
  t2=query(rson);
  return t1+t2;
}

int main()
{
  int T,n,t=1;
  int L,R,q;
  scanf("%d",&T);
  while(T--)
  {
    scanf("%d%d",&n,&q);
    flag=1;
    build(1,n,1);
    while(q--)
    {
      scanf("%d%d%d",&L,&R,&flag);
      updata(L,R,1,n,1);
    }
    printf("Case %d: The total value of the hook is %d.\n",t++,query(1,n,1));
  }
  return 0;
}

