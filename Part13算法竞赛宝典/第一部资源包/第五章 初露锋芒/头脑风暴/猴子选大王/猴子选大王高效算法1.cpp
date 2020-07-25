/*猴子选大王-高效算法1
程序说明： 
程序作者：ZXH (2010-4-20)
程序备注：输入文件为monkey.in,输出文件为monkey.out,M,N<=10000000
*/ 
#include <iostream>
using namespace std;
unsigned long m,k,n,r,q;
int main()
{
  freopen("monkey.in","r",stdin);
  freopen("monkey.out","w",stdout);  
  cin>>m>>k;
  n=m*k;//猴王最终报名为k*m
  while(n>m)
  {
    r=(n-m)%(k-1);
    if(r==0)
      r=k-1;
    q=(n-m-r)/(k-1);
    n=k*q+r;            
  }
  cout<<n<<endl;
  return 0; 
}
