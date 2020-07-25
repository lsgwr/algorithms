//统计不同数据的个数的优化算法，利用下标 
#include <iostream>
using namespace std;
int b[20+1];
int i,t,temp,sum;

int main()
{
  freopen("number.in","r",stdin);
  freopen("number.out","w",stdout);
  t=0;
  while(cin>>temp)
  {
    b[temp]++;
    ++sum;      
  }
  cout<<sum<<endl;
  for(i=0;i<=20;i++)
    cout<<b[i]<<' ';  
  return 0;
}
