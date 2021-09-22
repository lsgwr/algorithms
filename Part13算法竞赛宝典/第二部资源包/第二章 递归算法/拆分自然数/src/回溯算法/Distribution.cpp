//自然数拆分-回溯算法 
#include<iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
int Num[100],total=0,N ;

void print(int k)
{
  int i;
  cout<<N<<"="<<Num[1];
  for(i=2;i<=k;i++)
    cout<<"+"<<Num[i];                 
  cout<<"\n";
  total++;   
}

void split(int n,int digit)//n是需要拆分的数，m表示拆分的位数 
{
  int remainder;    
  for(int i=1;i<=n;i++)//从1开始尝试拆分   
    if(i>=Num[digit-1])//拆分的数大于或等于前一个
    {
      Num[digit]=i ;// 将这个数计入结果中          
      remainder=n-i ;// 剩下的数是n-i
      if(remainder==0 && digit>1)//若剩余数为0且总拆分个数大于1，则打印结果 
        print(digit);
      else
        split(remainder,digit+1);// 否则将剩下的数继续进行拆分
      Num[digit]=0;//取消本次结果，恢复初始值，进行下一次拆分即回溯
    }
}
 
int main()
{
  freopen("Distribution.in","r",stdin);
  freopen("Distribution.out","w",stdout);

  cin>>N;
  split(N,1);//从第1位数开始拆分 
  cout<<total<<endl;
  return 0;
}
