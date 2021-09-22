//拆分自然数递归算法 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
int Num[100],Count=0;

void print(int k)
{
  int i;
  cout<<Num[0]<<"="<<Num[1];
  for(i=2;i<=k;i++)
    cout<<"+"<<Num[i];                 
  cout<<"\n";
  Count++;   
}

void split(int k,int n)
{
  int i;
  if(n<=0) //如无法再拆分，则打印 
    print(k);
  else
    for(i=Num[k];i<=n;++i)
      if(i>=Num[k])//如果拆分的数大于前一个数
      {
        Num[k+1]=i;//则成功 
        split(k+1,n-Num[k+1]);//并进入下一个数的递归           
      }          
}

int main()
{
  freopen("Distribution.in","r",stdin);
  freopen("Distribution.out","w",stdout);
  cin>>Num[0];
  for(int i=1;i<=Num[0]/2;++i)//先拆分成两位加数 
  {
     Num[1]=i;
     split(1,Num[0]-Num[1]);  
  }
  cout<<Count<<"\n";
  return 0;
}
