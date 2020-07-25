//求最大子序列和 方法1 
#include <iostream>
using namespace std;
#define MAX 100000
int n,i,j,k,maxn,sum,a[MAX+1];

int main()
{
  freopen("sum.in","r",stdin);
  freopen("sum.out","w",stdout);  
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i]; 
  maxn=a[1];
  for(i=1;i<=n;i++)
    for(j=i;j<=n;j++)
    {
      sum=0;
      for(k=i;k<=j;k++)
        sum=sum+a[k];
      if(sum>maxn)
        maxn=sum;                   
    }
  cout<<maxn<<endl;  
  return 0;
}
