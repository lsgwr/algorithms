//计算分数的精确值
#include<iostream>
using namespace std;
int remainder[101],quotient[101];
//remainder:存放除法的余数;quotient:依次存放商的每一位

int main()
{
  int m,n,i,j;
  cin>>m>>n;      //输入被除数和除数
  cout<<m<<"/"<<n<<"=0.";
  for(i=1;i<=100;i++)  //i: 商的位数
  {
    remainder[m]=i; //m:除的余数 remainder[m]:该余数对应的商的位数
    m*=10;          //余数扩大10位
    quotient[i]=m/n; //商
    m=m%n;           //求余数
    if(m==0) //余数为0,即除完了
    {
      for(j=1;j<=i;j++) //则表示是有限小数
        cout<<quotient[j];  //输出商
      break;             //退出循环
    }
    if(remainder[m]!=0) //若该余数对应的位在前面已经出现过
    {
      for(j=1;j<=i;j++) 
        cout<<quotient[j];  //则输出循环小数
      cout<<"\n是无限循环小数,并从小数点后"<<remainder[m]<<"位";
      cout<<"到"<<i<<"位\n";//输出循环节的位置
      break;                //退出
    }
  }
  system("pause");
  return 0;
}
