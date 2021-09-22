//求名次
#include <iostream>
using namespace std;
#define n 10000
int i,j,mingci;
int a[n+1];

int main()
{
  freopen("billing.in","r",stdin);
  freopen("billing.out","w",stdout);
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i];
  for(i=1;i<=n;i++)
  {
    mingci=1;//假设第i位同学是第1名 
    for(j=1;j<=n;j++)//与所有同学比较，包括自己 
      if(a[j]>a[i])//如有成绩比自己高的，名次加1 
        mingci=mingci+1;
    cout<<a[i]<<' '<<mingci<<endl;
  }
  return 0;
}
