//数列 
#include <iostream>
using namespace std;

int a[10+1];
int pow(int k,int n)//k的n次方 
{
  int temp=k,x;
  if (n==0)
    return 1;
  for(int i=1;i<n;i++)
    k=k*temp;
  return k;
}

int main()
{
  int n,i=-1,j,k,ans=0;
  cin>>k>>n;
  do//将n转为二进制 
  {
    i=i+1;
    a[i]=n%2;
    n=n/2;
  }while(!(n==0));
    
  for(j=0;j<=i;j++)//累加 
    if(a[j]==1)
      ans+=pow(k,j);
  cout<<ans<<endl;
  return 0;
} 
