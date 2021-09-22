//求最大子序列和 方法4
#include <iostream>
using namespace std;

int n,i,j,Min,MaxN,s[100000+1],a[100000+1],ma=-2147483647;

int main()
{
  freopen("sum.in","r",stdin);
  freopen("sum.out","w",stdout);  
  cin>>n;
  s[0]=0;
  for(i=1;i<=n;i++)
  {
    cin>>a[i];
    s[i]=s[i-1]+a[i];
    if(ma<=a[i])
        ma=a[i];
  } 
  Min=a[1];
  MaxN=a[1];
  for(j=1;j<=n;j++)
  {
    if(s[j]<Min)
      Min=s[j];
    if(s[j]-Min>MaxN)
      MaxN=s[j]-Min;
  }
  if(MaxN==0)
cout<<ma<<endl;
else
    cout<<MaxN<<endl;  
  return 0;
}
