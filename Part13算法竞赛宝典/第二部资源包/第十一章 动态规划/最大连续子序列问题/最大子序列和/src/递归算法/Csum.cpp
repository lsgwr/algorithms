//最大连续子序列和－递归 
#include <iostream>
#include <cstdlib>
using namespace std;

int a[100001];
int n,sum=0,Max=-INT_MAX;

void work(int x)
{
  if(x>n)  
    return ;
  sum+=a[x];
  if(sum>Max) 
    Max=sum;  
  work(x+1);    
}

int main()
{
  freopen("Csum.in","r",stdin);
  freopen("Csum.out","w",stdout);
  int i;
  cin>>n;
  for(i=1;i<=n;++i)
    cin>>a[i];
  for(i=1;i<=n;++i)
  {    
    sum=0;
    work(i);
  }
  cout<<Max<<endl;
  return 0;    
}
