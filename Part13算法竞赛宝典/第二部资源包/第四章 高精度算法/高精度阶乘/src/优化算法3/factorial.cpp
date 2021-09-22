//优化算法3：万进制阶乘 
 #include <iostream>
#include <stdio.h>
#include <stdlib.h>
#define MAXN 40000
#define BASE 10000
using namespace std;
int a[MAXN+1],len=1;

void mul(int i)
{
  int j,p=0;
  for(j=MAXN;j>=MAXN-len+1;j--)
  {
    a[j]=a[j]*i+p;
    if(a[j]>=BASE)
    {
      p=a[j]/BASE;
      a[j]%=BASE;
    }
    else
      p=0;
  }
  while(p>0)
  {
    ++len;
    a[MAXN-len+1]=p;
    p=a[MAXN-len]/BASE;
    a[MAXN-len+1]%=BASE;
  } 
}

int main()
{
  FILE *in=fopen("factorial.in","r");
  FILE *out=fopen("factorial.out","w");
  int n,i;
  fscanf(in,"%d",&n);
  a[MAXN]=1;
  fprintf(out,"%d!=",n);
  for(i=1;i<=n;i++)
    mul(i);
  if(a[MAXN-len+1])
    fprintf(out,"%d",a[MAXN-len+1]);
  for(i=MAXN-len+2;i<=MAXN;i++)
    fprintf(out,"%04d",a[i]);
  fprintf(out,"\n");
  fclose(in);
  fclose(out);
  return 0;
}
