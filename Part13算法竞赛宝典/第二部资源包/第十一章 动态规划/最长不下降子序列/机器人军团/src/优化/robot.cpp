//最长不下降序列－动规优化算法 
#include <cstdlib>
#include <iostream>
using namespace std;
int a[100001];
int Link[100001];//链接,注意linux下有一个link的函数，首字母大写以防止冲突 
int Long[100001];
int n,Max;

int find(int key)//二分查找 
{
  int L=1,R=Max,mid;
  while(L<=R)
  {
     mid=(L+R)/2;
     if(a[Long[mid]]<key)
       L=mid+1;
     else R=mid-1;
  }
  return L-1;
}

int main()
{
  freopen("robot.in","r",stdin);
  freopen("robot.out","w",stdout);  
  int i,j,k,l;
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i];
  Max=1;//初始最长序号数为1 
  Long[Max]=1;//初始为第一个元素 
  Link[1]=1;//初始链为第一个元素 
  for(i=2;i<=n;i++)
  {
    if(a[i]>=a[Long[Max]])//接在既定序列之后 
    { 
      Max++;
      Long[Max]=i;
      Link[i]=Long[Max-1];
    }
    else//在既定序列中查找位置并插入 
    {
      l=find(a[i]);
      Long[l+1]=i;
      Link[i]=Long[l];
    }
  }
  cout<<Max<<'\n';
  k=Max;
  i=Long[Max];
  while(Max>0)
  {
    Long[Max]=a[i];//改为 Long[max]=i 可输出序号 
    i=Link[i];
    Max--;
  }
  //for(i=1;i<=k;i++)
  //cout<<Long[i]<<' ';
  //system("pause");
  return 0;
}
