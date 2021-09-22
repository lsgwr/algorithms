//高精度幂的优化算法 
#include<iostream>
#include<fstream>
#include<string>
#include <cstdlib>
using namespace std;

int a[10001],x[10001],c[10001],len_a;
int len_x=1,b,*pa,*pc,*px;

int Power()
{
  int i,j,r=0;
  for(i=1;i<=len_a*2 && i<=1000;i++)
    pc[i]=0;
  for(i=1;i<=len_a;i++)
    for(j=1;j<=len_a && i+j-1<=1000;j++)
      pc[i+j-1]+=(pa[i])*(pa[j]);
  for(i=1;i<=len_a*2-1 && i<=1000;i++)
  {
    if(pc[i]+r>9)
    {
      pc[i]=pc[i]+r;r=pc[i]/10;pc[i]%=10;
    }
    else
    {
      pc[i]+=r;r=0;
    }
  }
  if(i>1000)
    len_a=1000;
  else if(r)
  {
    pc[len_a*2]=r;
    len_a=len_a*2;
  }
  else
    len_a=len_a*2-1;
  int *change;
  change=pa;
  pa=pc;
  pc=change;
}

int mul()
{
  int i,j,r=0;
  for(i=1;i<=len_a+len_x && i<=1000;i++) 
    pc[i]=0;//pc数组初始为0
  for(i=1;i<=len_a;i++)//计算 
    for(j=1;j<=len_x && i+j-1<=1000;j++)
      pc[i+j-1]+=(pa[i])*(px[j]);
  for(i=1;i<=len_a+len_x-1 && i<=1000;i++)
  {
    if(pc[i]+r>9)
    {
      pc[i]=pc[i]+r;r=pc[i]/10;pc[i]%=10;
    }
    else
    {
      pc[i]+=r;r=0;
    }
  }
  if(i>1000)
    len_x=1000;
  else if(r)
  {
    pc[len_a+len_x]=r;
    len_x=len_x+len_a;
  }
  else
    len_x=len_x+len_a-1;
  int *change;//交换地址 
  change=px;
  px=pc;
  pc=change;
}

int main()
{
  ifstream fin("evolution.in");
  ofstream fout("evolution.out");
  string s;
  int i,j;
  fin>>s>>b;
  int len=s.size();
  for(i=1;i<=len;i++)//将字符数组转为整形数组 
    a[i]=s[len-i]-'0';
  len_a=len;
  pa=a;//pa指针指向a数组 
  pc=c;//pc指针指向c数组 
  px=x;//px指针指向x数组 
  px[1]=1;
  while(b>1)//降幂算法 
  {
    if(b%2==1)
      mul();
    Power();
    b=b>>1;//b=b/2
  }
  mul();
  for(i=len_x;i>=1;i--)//输出答案 
    fout<<px[i];
  fout<<endl;
  fin.close();
  fout.close();
  return 0;
}
