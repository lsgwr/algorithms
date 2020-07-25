/*
程序名称：高精度阶乘优化算法1 
程序备注：可在1250左右范围内三三相乘，但优化效果不大
*/
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
int a[40000],len=1;

void mul(int i) 
{
  int j,p=0;
  for(j=1;j<=len;j++)
  {
    a[j]=a[j]*i+p;
    if(a[j]>=10)
    {
      p=a[j]/10;//保存进位值 
      a[j]%=10;
    }
    else
      p=0;
  }
  while(p>0)//对进位值进行处理 
  {
    a[++len]=p;
    p=a[len]/10;
    a[len]%=10;
  }
}

int main()
{
  FILE *in=fopen("factorial.in","r");
  FILE *out=fopen("factorial.out","w");
  int n,i;
  fscanf(in,"%d",&n);
  a[1]=1;
  fprintf(out,"%d!=",n);
  if(n&1==1)//如果为奇数 
  {
    for(i=1;i<=n-1;i+=2)
      mul(i*(i+1));
    mul(n);
  }
  else      //如果为偶数 
    for(i=1;i<=n;i+=2)
      mul(i*(i+1));
        
  for(i=len;i>=1;--i)
    fprintf(out,"%d",a[i]);
  fprintf(out,"\n");
  fclose(in);
  fclose(out);
  return 0;
}
