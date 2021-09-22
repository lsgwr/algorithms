/*
程序名称：N皇后问题的普通递推算法 
程序说明：
程序作者：ZXH (2010-9-11)
程序备注：
*/ 
#include <iostream>
#include <math.h>
using namespace std;
int i,j,k,n,number;
int x[20+1];//存放皇后位置,数组从1开始 

int Try(int k)//判断该位置是否可安置皇后 
{
  int i;
  for(i=1;i<=k-1;i++)
    if((x[i]==x[k])||(abs(x[i]-x[k])==abs(i-k)))
      return 0;
  return 1;
}

int main()
{
  freopen("queen.in","r",stdin);
  freopen("queen.out","w",stdout);
  k=1;
  x[k]=0;
  cin>>n;
  while(k>0)
  {
    x[k]=x[k]+1;
    while((x[k]<=n)&&(!Try(k)))//直到试到一个合适的位置 
      x[k]=x[k]+1;
    if(x[k]>n)//如果试过了n的范围 
      k=k-1;//回溯到前一行 
    else
      if(k==n)//如果安置好最后一行皇后 
        number++;//print();//打印结果 
      else
      {
        k=k+1;//继续安置下一行皇后 
        x[k]=0;
      }
  }
  cout<<number<<endl;
}

