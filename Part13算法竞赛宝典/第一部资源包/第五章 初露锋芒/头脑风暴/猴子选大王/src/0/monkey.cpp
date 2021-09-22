//猴子选大王 
#include <iostream>
using namespace std;
#define MAX 10000000
long a[MAX+1];
long m,n,i,Count,k;
int main()
{
  freopen("monkey.in","r",stdin);
  freopen("monkey.out","w",stdout);  
  cin>>m>>n;
  for(i=1;i<=m;i++)
    a[i]=1;
  k=m;//报数前，指向该报数的前一只猴子 
  for(i=1;i<=m;i++)//出圈M只猴子 
  {
    Count=0;//报数前，个数为0 
    while(Count<n)//当前报数<n，继续报数 
    {
      if(k==m)//从上一个位置移到下一个位置 
        k=1;
      else
        k++;
      if(a[k]==1)//当前猴子在圈内，报数 
        Count++;
    }
    a[k]=0;//猴子K出圈 
  }
  cout<<k<<endl;//最后一只出圈的是大王 
  return 0;
}
