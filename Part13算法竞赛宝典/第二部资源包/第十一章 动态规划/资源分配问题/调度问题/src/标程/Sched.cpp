//调度问题 
#include<iostream>
#include<stdlib.h>
using namespace std;

int p[2000]={0};

int main()
{
  freopen("Sched.in","r",stdin);
  freopen("Sched.out","w",stdout);  
  int n;
  int i,j,k,sa=0,sb=0,min=100000000;
  int A[201],B[201],x=0;
  cin>>n;
  for(i=1;i<=n;i++)
  {
    cin>>A[i];
    sa+=A[i];
  }
  for(i=1;i<=n;i++)
  {
    cin>>B[i];
    sb+=B[i];
  }
  for(k=1;k<=n;k++)
    for(i=sa;i>=0;i--)
      if(i>=A[k])
        p[i]=p[i-A[k]]<p[i]+B[k]?p[i-A[k]]:p[i]+B[k];
      else
        p[i]=p[i]+B[k];

  for(i=0;i<=sa;i++)
  {
    k=i>p[i]?i:p[i];
    if(k<min)
      min=k;
  }
  cout<<min<<endl;
  return 0;
}
