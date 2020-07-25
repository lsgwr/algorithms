//求组合数算法3 
#include <iostream>
#include <string.h>
#include <iomanip>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

void output(int x[])//输出 
{
  cout<<x[x[0]];
  for(int i=x[0]-1;i>0;i--)
    cout<<setw(4)<<setfill('0')<<x[i];
  cout<<endl;
}

void StrToInt(string n,int x[])
{
  x[0]=n.size();
  for(int i=x[0]-1;i>=0;i--)
    x[i/4+1]=x[i/4+1]*10+(n[x[0]-i-1]-'0');
  x[0]=x[0]/4+(x[0]%4!=0);
}

void Empty(int a[])//初始为0 
{
  for(int i=a[0];i>=0;i--) 
    a[i]=0;
}

void add(int x[],int k) //高精度数+低精度数 
{
  x[1]+=k;
  int i;
  for(i=1;x[i]>9999;i++)
  {
    x[i+1]+=x[i]/10000;
    x[i]%=10000;
  }
  if(i>x[0]) 
    x[0]=i;
}

void sub(int x[],int k) //高精度数-低精度数 
{
  x[1]-=k;
  int i;
  for(i=1;i<x[0] && x[i]<0;i++)
  {
    int s=x[i]%10000;
    x[i+1]-=x[i]/10000+(s!=0);
    x[i]=s+10000*(s!=0);
  }
  if(i>=x[0])
  {
    for(;i>1 && x[i]==0;i--);
      x[0]=i;
  }
  if(x[0]==0) 
    x[0]++;
}

void mul(int a[],int b[],int c[])//高精度数*高精度数 
{
  Empty(c);
  int i,j;
  for(i=1;i<=a[0];i++)
    for(j=1;j<=b[0];j++)
    {
      c[i+j-1]+=a[i]*b[j];
      c[i+j]+=c[i+j-1]/10000;
      c[i+j-1]%=10000;
    }
  c[0]=a[0]+b[0]-(c[a[0]+b[0]]==0);
}

void div(int a[],int b,int c[])//高精度数/低精度数 
{
  Empty(c);
  c[0]=a[0];
  int t=0,i;
  for(i=a[0];i>0;i--)
  {
    t=t*10000+a[i];
    c[i]=t/b;
    t=t%b;
    if(i==c[0] && c[i]==0) 
      c[0]--;
  }
}

int t[10000000];

void combin(int m[],int n,int c[])
{
  Empty(c);c[0]=1,c[1]=1;//c=1;
  for(int i=1;i<=n;++i)
  {
    mul(c,m,t);//t=c*m
    div(t,i,c);//c=t/i
    sub(m,1);//m-- 
  }
}

int m[10000010],n,ans[10000010];
int main()
{
  freopen("combin.in","r",stdin);
  freopen("combin.out","w",stdout);  
  string instr;
  cin>>instr>>n;
  StrToInt(instr,m);
  add(m,n);
  sub(m,2);
  n--;
  combin(m,n,ans);
  output(ans);
  return 0;
}
