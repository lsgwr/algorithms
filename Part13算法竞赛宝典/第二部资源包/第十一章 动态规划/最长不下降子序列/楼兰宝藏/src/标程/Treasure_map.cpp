//楼兰宝藏 
#include <iostream>
#include <cstdlib>
using namespace std;

int a[100001],b[100001];
int link[100001],v[100001],p[100001];//v[]用来统计记录序列元素下标 
int tot;

void swap(int &a,int &b)//交换a与b当前的值
{
  int t;
  t=a;a=b;b=t;
}

void init(int n)//初始化，将数组倒叙 
{
  int t,i;
  for(i=1;i<=n;i++)
  {t=b[i],b[i]=b[n-i+1],b[n-i+1]=t;}
}

int find(int key)//二分查找下标
{
  int mid,L,R;
  L=1,R=tot;
  while(L<=R)
  {
    mid=(L+R)/2;
    if(b[v[mid]]<key)
      L=mid+1;
    else
      R=mid-1;
  }
  return L-1;
}

void sort(int L,int R)
{
  if(L>=R)  
    return ;
  int i,j,k;
  k=a[(L+R)/2];
  i=L,j=R;
  while(i<=j)
  {
    while(a[i]<k)   i++;
    while(a[j]>k)   j--;
    if(i<=j)
    {
      swap(a[i++],a[j--]);
      swap(b[i++],b[j--]);             
    }
  }
  sort(L,j);
  sort(i,R);
}

int main()
{
  freopen("Treasure_map.in","r",stdin);
  freopen("Treasure_map.out","w",stdout);  
  int i,j,n,m,k,L;
  cin>>n>>m>>k;//有一个m*n的矩阵，有 k个宝藏 
  for(i=1;i<=k;i++)
    cin>>a[i]>>b[i],p[i]=i;//宝藏的坐标a为横坐标，b为纵坐标 
  sort(1,n);//对横坐标进行排序， 然后以之为权找最长不上升子序列 
  init(n);
  tot=1,v[tot]=1,link[1]=p[1];
  //tot指最长不上升序列的长度，p[]表示序列的下标，link[]表示连接 
  for(i=2;i<=k;i++)
  {
    if(b[p[i]]<=b[v[tot]])
    {
      tot++;
      v[tot]=p[i];
      link[p[i]]=v[tot-1];
    }
    else
    {
      L=find(b[p[i]]);
      v[L+1]=p[i];
      link[p[i]]=v[L];
    }
  }
  cout<<tot<<endl;//只需打印长度 
  return 0;
}
