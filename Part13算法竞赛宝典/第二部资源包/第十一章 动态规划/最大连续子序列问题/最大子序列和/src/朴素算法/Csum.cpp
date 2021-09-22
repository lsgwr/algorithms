//最大连续子序列和－朴素算法 
#include <iostream>
#include <cstdlib>
using namespace std;
int a[100001];

int main()
{
  freopen("Csum.in","r",stdin);
  freopen("Csum.out","w",stdout);  
  int i,j;
  int sum=0,Max=-INT_MAX,n;
  cin>>n;

  for(i=1;i<=n;++i)
    cin>>a[i];  
  for(i=1;i<=n;++i)   
  {
    for (j=i;j<=n;++j)
    {
      sum+=a[j];
      if(sum>Max) 
        Max=sum;
    }    
    sum=0;
  }
  cout<<Max<<endl;
  return 0;    
}
