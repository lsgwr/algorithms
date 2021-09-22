//并查集
#include <bits/stdc++.h>
using namespace std;

int father[20001];

int find(int x) //判断x所属的集合
{
  int root=x;
  while(father[root]>=0)//找到最终的根结点
    root=father[root];
  while(root!=x) //压缩路径
  {
    int temp=father[x];
    father[x]=root;
    x=temp;
  }
  return root;
}

void Union(int a,int b) //合并两个不同集合的元素
{
  if(father[a]>father[b])
    father[a]=b;
  else
    father[b]=a;
}

int main()
{
  int N,M,Q,a,b;
  cin>>N>>M>>Q;
  for(int i=1; i<=N; i++) //初始化
    father[i]=-1; //各结点的父结点均为-1
  for(int i=1; i<=M; i++)
  {
    cin>>a>>b;
    if(find(a)!=find(b))
      Union(a,b);//联通
  }
  for(int i=1; i<=Q; i++)
  {
    cin>>a>>b;
    find(a)==find(b)?cout<<"YES"<<'\n':cout<<"NO"<<'\n';
  }
  return 0;
}

