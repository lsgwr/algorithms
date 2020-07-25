//朴素算法求高精度阶乘
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

int main()
{
  FILE *in=fopen("factorial.in","r");
  FILE *out=fopen("factorial.out","w");
  int data[50001]={0};
  int digit=1;//位数
  int i,j,n;
  data[1]=1;
  fscanf(in,"%d",&n);
  for(i=1;i<n+1;i++)
  {
    for(j=1;j<digit+1;j++)//阶乘运算 
      data[j]*=i;
    for(j=1;j<digit+1;j++)
       if(data[j]>=10)  //当某位数>=10时，需整理数据
         for(int r=1;r<digit+1;r++)
         {
           if(data[digit]>=10)//位数增加1 
             digit++;
           data[r+1]+=data[r]/10;
           data[r]=data[r]%10;
         }
  }
  fprintf(out,"%d!=",i-1); //打印结果
  for(int k=digit;k>0;k--)
    fprintf(out,"%d",data[k]);
  fprintf(out,"\n");
  fclose(in);
  fclose(out);
  return 0;  
}
