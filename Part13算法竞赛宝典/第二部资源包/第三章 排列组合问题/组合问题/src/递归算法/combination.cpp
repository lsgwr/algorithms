//组合问题递归算法 
#include <iostream>
#include <stdlib.h>
#include <stdio.h> 
using namespace std;
int buff[21];     //放组合的数组,从１开始
int M,N;

void print()
{
  for( int i=1;i<=N;i++)
    cout<<buff[i];
  cout<<"\n";      
}

void combin(int M,int N)
{ 
  if(N==0)  //递归结束条件 
    print();
  else
    for(int i=M;i>=N;i--)//逆序赋值,且限制了随后的数越来越小 
    {
      buff[N]=i;
      combin(i-1,N-1);
    }
}

main()
{    
  freopen("combination.in","r",stdin);
  freopen("combination.out","w",stdout);   
  cin>>M>>N;//即从M个元素中选择N个元素
  combin(M,N);
  return 0;
}
