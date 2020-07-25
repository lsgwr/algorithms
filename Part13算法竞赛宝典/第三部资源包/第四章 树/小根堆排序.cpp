//小根堆排序
#include <iostream>
using namespace std;
#define N 100000
int a[N+1];

void adjust_down(int i,int m)//调整 
{
  int x;
  while(i*2<=m)
  {
    i=i*2;
    if((i<m)&&(a[i+1]>a[i]))//检查右兄弟 
      i++;
    if(a[i]>a[i/2])
    {
      x=a[i/2];
      a[i/2]=a[i];
      a[i]=x;
    }
    else
      break;
  }
}

int main()
{
  int n,i;
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i];
    
  for(i=n/2;i>=1;i--)//只须调整一半即可 
    adjust_down(i,n);//建堆 
  for(i=n;i>=2;i--)
  {
    a[i]=a[i]^a[1];//交换 
    a[1]=a[1]^a[i];
    a[i]=a[i]^a[1];
    adjust_down(1,i-1);
  }
  for(i=1;i<=n;i++)
    cout<<a[i]<<' ';
  return 0;
} 
