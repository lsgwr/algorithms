/*
程序名称：高精度分数
程序说明：输入一个分数(m/n)(<0<m<n<=100):")
程序作者：ZXH (2010-3-30)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 
#include<iostream>
using namespace std;
int Remainder[101];// remainder:存放除法的余数；
int quotient[101]; // quotient:依次存放商的每一位
int main()
{
  freopen("fraction.in","r",stdin);
  freopen("fraction.out","w",stdout);
  int m,n,i,j;
  scanf("%d/%d",&m,&n);      /*输入被除数和除数*/
  cout<<m<<'/'<<n<<"=0.";
  for(i=1;i<=100;i++)             /*i: 商的位数*/
  {
     Remainder[m]=i;       /*m:除的余数remainder[m]:该余数对应的商的位数*/
     m*=10;                /*余数扩大10位*/
     quotient[i]=m/n;      /*商*/
     m=m%n;                /*求余数*/
     if(m==0)              /*余数为0 则表示是有限小数*/
     {
        for(j=1;j<=1;j++) 
          cout<<quotient[j];  /*输出商*/
        break;             /*退出循环*/
     }
     if(Remainder[m]!=0)     /*若该余数对应的位在前面已经出现过*/
     {
        for(j=1;j<=i;j++)
          cout<<quotient[j];  /*则输出循环小数*/
        cout<<endl;
        cout<<"无限循环小数"<<"从小数点后"<<Remainder[m]<<"位到"<<i<<"位";
        cout<<endl;
        break;  
     }
   }
}

