//天网
#include <bits/stdc++.h>
using namespace std;
#define Max 200001

struct data
{
  int l,r,max;
} node[3*Max];

int score[Max];//保存分数

void BuildTree(int left,int right,int num)//建树
{
  node[num].l=left;
  node[num].r=right;
  if(left==right)
  {
    node[num].max=score[left];
  }
  else
  {
    BuildTree(left,(left+right)>>1,2*num);
    BuildTree(((left+right)>>1)+1,right,2*num+1);
    node[num].max=max(node[2*num].max,node[2*num+1].max);
  }
}

void Update(int stu,int val,int num)//更新成绩
{
  if(node[num].l==node[num].r)
  {
    node[num].max=val;
    return;
  }
  if(stu<=node[2*num].r)
    Update(stu,val,2*num);
  else
    Update(stu,val,2*num+1);
  node[num].max=max(node[num*2].max,node[num*2+1].max);
}

int Query(int left,int right,int num)//询问操作
{
  if(node[num].l==left && node[num].r==right)
    return node[num].max;
  if(right<=node[2*num].r)
    return Query(left,right,2*num);
  if(left>=node[2*num+1].l)
    return Query(left,right,2*num+1);
  int mid=(node[num].l+node[num].r)>>1;
  return max(Query(left,mid,2*num),Query(mid+1,right,2*num+1));
}

int main()
{
  int N,M;
  while(scanf("%d%d",&N,&M)!=EOF)
  {
    for(int i=1; i<=N; i++)
      scanf("%d",&score[i]);
    getchar();
    char c;
    int s,e;
    BuildTree(1,N,1);
    for(int i=0; i<M; i++)
    {
      scanf("%c%d%d",&c,&s,&e);
      getchar();
      if(c=='U')
        Update(s,e,1);
      if(c=='Q')
        printf("%d\n",Query(s,e,1));
    }
  }
  return 0;
}

