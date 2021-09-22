//康托展开逆运算 
#include<iostream>
using namespace std;
long long fac[10]={1,1,2,6,24,120,720,5040,40320,362880};//阶乘 
int hash[10]={0};

int cantor(int m,int n)
{
  long int num=0,e;
  int temp;
  int i,j;int k;
  int xp;
  m=m-1;
  for(i=n-1;i>0;i--)
  {
    temp=0;e=1;
    xp=m/fac[i];//xp表示在这个数之前有几个比这个数小的未用到过的数 
    m=m%fac[i];//把m更新一次 
    for(j=1;j<=(n>temp+xp+1?temp+xp+1:n);j++)//查看之前有几个数已经用过 
    if(hash[j]!=0)
      temp++;
    for(j=1;j<=i;j++)//将得出的数加到num中 
      e*=10;
    num+=(temp+xp+1)*e;
    hash[temp+xp+1]=1;
    //for(k=1;k<=n;k++) //打印数字的使用情况 
    //  printf("%d ",hash[k]);
    //printf("\n");
  }
  temp=0;
  for(i=1;i<=n;i++)//取出最后的未使用的数
    if(hash[i]==0)
    { hash[i]=1;num+=i;break; }
  return(num);
}
int main()
{
  freopen("cantor2.in","r",stdin);
  freopen("cantor2.out","w",stdout);  
  int n,num;
  cin>>n>>num;  
  printf("%ld\n",cantor(n,num));
  return 0;
}
