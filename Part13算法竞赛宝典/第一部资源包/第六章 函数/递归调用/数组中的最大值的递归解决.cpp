//数组中的最大值的递归解决 
#include <iostream>
using namespace std;

int maxn(int a[],int n)
{  
  if(n==1)
    return a[n-1];
  else//此处可用一临时变量进行时间的优化 
    return maxn(a,n-1)>a[n-1]?maxn(a,n-1):a[n-1];    
}

int main()
{
  int n;
  cin>>n;
  int a[n];
  for(int i=0;i<n;++i)
    cin>>a[i];
  cout<<maxn(a,n)<<endl;//输出最大值  
  system("pause");
}
