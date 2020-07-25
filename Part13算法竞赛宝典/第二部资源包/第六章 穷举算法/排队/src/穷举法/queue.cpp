/*
程序名称：奶牛排队 
*/ 
#include <iostream>
using namespace std;
int a[30001],i,n,num,minx;

int main()
{
  freopen("queue.in","r",stdin);
  freopen("queue.out","w",stdout);
  cin>>n;
  for(i=1;i<=n;i++)
  {
    cin>>num;
    a[i]=a[i-1]+num-1;//a[i]表示从第1头到第i头编号为2的奶牛个数 
  }
  minx=n;
  for(i=0;i<=n;i++) //穷举i的位置 
    if(minx>a[i]+n-i-(a[n]-a[i]))//第1~i头奶牛编号全变为编号1，
        minx=a[i]+n-i-(a[n]-a[i]);//第i+1~n头奶牛全变为编号2 
  
  if(n==a[i])//编号全为2，无需改变 
    minx=0;
  cout<<minx<<endl;
  return 0;             
}
