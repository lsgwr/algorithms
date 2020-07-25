//24点 全排列枚举 
#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;

bool work(int d[4],int b[3],int c[3],bool output=0) //运算兼输出
{
  int i;
  int e[3]={0,0,0}; //存储每次操作后新增的数字 
  int *a[4]={d,d+1,d+2,d+3};//原指向数组d中4个数字的指针，操作后可能指向新的位置 
  for(i=0;i<3;i++)//i用来枚举每次操作，每次操作针对第c[i]个数和第c[i]+1个数 同时用来指向新数存储的位置 
  {
    if(output) 
      cout<<*a[c[i]];
    switch(b[i])
    {
      case 1:
        if(output) 
          cout<<'+';
        e[i]=*a[c[i]]+*a[c[i]+1];
        break;
      case 2:
        if(output) 
          cout<<'-';
        e[i]=*a[c[i]]-*a[c[i]+1];
        break;
      case 3:
        if(output) 
          cout<<'*';
        e[i]=*a[c[i]]**a[c[i]+1];
        break;
      case 4:
        if(output) 
          cout<<'/';
        if(*a[c[i]+1]==0 || *a[c[i]]%*a[c[i]+1]!=0) 
          return 0;//除法特判 
        e[i]=*a[c[i]] / *a[c[i]+1];
        break;
    }
    if(output) 
      cout<<*a[c[i]+1]<<'='<<e[i]<<endl;    
    if(i==1&&c[0]==1)//消除bug 
    {
      if(c[i]==0)
        a[c[i]+2]=&e[i];
      else
        a[c[i]-1]=&e[i];
    }
    a[c[i]]=&e[i];
    a[c[i]+1]=a[c[i]];//更新指针指向
  }
  if(output) 
    return 1;//若这次任务是打印，则可直接返回1 
  if(e[2]!=24) 
    return 0;
  return work(d,b,c,1);//若有解则打印 
}

int main()
{
  freopen("24.in","r",stdin);
  freopen("24.out","w",stdout);
  int i,j,a[4],b[3];//a[4]储存4个值,b[3]用来枚举每次操作的操作符 
  for(i=0;i<4;i++) 
    cin>>a[i];
  int c[3]={0,1,2};//0表示对a[0]、a[1]操作，1表示对a[1]、a[2]操作，2表示对a[2]、a[3]操作 
  do
  {
    //枚举操作符 
    for(b[0]=1;b[0]<=4;b[0]++) 
      for(b[1]=1;b[1]<=4;b[1]++)
        for(b[2]=1;b[2]<=4;b[2]++)
          if(work(a,b,c))//将四个数字、操作符、操作顺序作为参数交给work函数处理 
            return 0;//终止运算
  }while(next_permutation(c,c+3));//生成操作顺序的全排列 
  cout<<"NO"<<endl;
  return 0;
} 
