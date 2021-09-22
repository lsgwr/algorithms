#include<iostream>
#include"stdlib.h"
#define Q 1001
using namespace std;

int m;
int x[Q][Q];
int a[Q]={0};

inline bool isNumber(char c)
{
  return (c>='0' && c<='9');
}

int Try(int k) 
{
  int i;
  for(i=1;i<=m;i++)
    if(x[k][i]==1)
    {
      if(a[i]==a[k] && k!=i) 
        return 0;
    } 
  return 1;
}

int main()
{
  freopen("map.in","r",stdin); 
  freopen("map.out","w",stdout);
  cin>>m;
  char c;
  getchar();
  for(int i=1;i<=m;i++)
  {
    do
    {
      int num=0;
      while(isNumber(c=getchar())) 
        num=num*10+c-'0'; 
       x[i][num]=x[num][i]=1;
     }while(c!='\n');
  }
  int i;
  /*for(i=1;i<=m;i++)
  {
      for(int j=1;j<=m;j++)
      cout<<x[i][j]<<" ";
      cout<<endl;
  }*/
  int k=1;  
  a[k]=0;
  while(1)
  {
    a[k]=a[k]+1;
    while((a[k]<=4)&&(!Try(k)))
      a[k]=a[k]+1;
    if(a[k]>4) 
    {
      a[k]=0;       
      k=k-1;  
    }
    else
      if(k==m+1) 
        break;   
      else  
      {
        k=k+1; 
        a[k]=0;
      }
  }
  for(i=1;i<=m;i++)
    printf("%d ",a[i]); 
  return 0;
}

