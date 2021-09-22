//数的划分－递归 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;
int n,k,sum=0;

//x表示要枚举的数，kk表示份数，nn表示剩下的数
void work(int x,int kk,int nn)
{
  if(kk==k)
    ++sum;
  else
    for(int i=x;i<=nn/(k+1-kk);++i) 
      work(i,kk+1,nn-i);//穷举的数最大到nn/(k+1-kk)，以保证数由小到大
}

int main()
{
  freopen("Numdismantle.in","r",stdin);
  freopen("Numdismantle.out","w",stdout);
  cin>>n>>k;
  work(1,1,n);
  cout<<sum<<'\n';
  return 0;
}
