//最大子序列和 方法3
#include <iostream>
using namespace std;
#define MAX 100000
int n,i,j,Min,MaxN,s[MAX+1],a[MAX+1];

int main()
{
  freopen("sum.in","r",stdin);
  freopen("sum.out","w",stdout);  
  cin>>n;
  s[0]=0;
  for(int i=1;i<=n;i++)
  {
    cin>>a[i];
    s[i]=s[i-1]+a[i];
  } 
  Min=0;
  MaxN=a[1];
  for(j=1;j<=n;j++)
  {
    if(s[j]<Min)
      Min=s[j];
    if(s[j]-Min>MaxN)
      MaxN=s[j]-Min;
  }
  cout<<MaxN<<endl;  
  return 0;
}
