//挖地雷 
#include<iostream>
using namespace std;
int i,j,n,t=1,x;
long long a[10000],b[10000];
int f(int k) //递推，成功返回1，失败返回0 
{
 
  for(i=2;i<=n;i++)
  {
    b[i+1]=a[i]-b[i-1]-b[i];
    if(b[i+1]<0 || b[i+1]>1 ) 
      return 0;
  }   
  if (b[n+1]==1) 
    return 0; //如果不应该出现地雷的位置出现地雷 
  else 
    return 1;       
}

int main()
{
  freopen("bomp.in","r",stdin);
  freopen("bomp.out","w",stdout);
 
  cin>>n;
  for (i=1;i<=n;i++)
    cin>>a[i];
  for (i=1;i<=n;i++)
    if ((a[i]<0)||a[i]>3) //如果输入数据不合法 
    {
      cout<<"No answer";
      return 0;
    }
  if (a[1]==0)
  {
    b[1]=0;
    b[2]=0;
  }
  if (a[1]==1)
  {
    b[1]=1;
    b[2]=0;
  }
  if (a[1]==2)
  {
    b[1]=1;
    b[2]=1;
  }
  x=f(n); //递推 
  if ((a[1]==1) && (x==0)) //如果第一个数据是1并且递推失败，交换b[1],b[2] 
  {
    b[1]=0;
    b[2]=1;
  }
  x=f(n);  //再次递推 
  if ((x==0)) 
    cout<<"No answer";
  else 
  {
    for (i=1;i<=n;i++)
      cout<<b[i]<<' ';
  }   
  cout<<endl;
  return 0;
}
