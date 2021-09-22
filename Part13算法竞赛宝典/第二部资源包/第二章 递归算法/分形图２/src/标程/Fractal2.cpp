//分形图２ 
#include<stdio.h>
#include<stdlib.h>
#include<iostream>
using namespace std;

char mo[5][5];
char map[3005][3005];
int n;

void dfs(int k,int x,int y)
{
   int i,j;
   if(k==1)
   {
     for(i=x;i<x+n;i++)
       for(j=y;j<y+n;j++)
         map[i][j]=mo[i-x][j-y];
     return ;
   }
   int sum=1,kk=k-2;
   while(kk--)
     sum=sum*n;
   for(i=0;i<n;i++)
     for(j=0;j<n;j++)
     {
       if(mo[i][j]!=' ')
         dfs(k-1,x+sum*n*i,y+sum*n*j);
     }
}

int main()
{
   freopen("Fractal2.in","r",stdin);
   freopen("Fractal2.out","w",stdout); 
   int i,m,sum,j;
   while(scanf("%d",&n)!=EOF)
   {
     if(n==0)
       break;
     getchar();
     memset(map,' ',sizeof(map));
     for(i=0;i<n;i++)
       gets(mo[i]);
     scanf("%d",&m);
     dfs(m,0,0);//从（0,0）位置处
     sum=1;
     while(m--)
       sum=sum*n;
     for(i=0;i<sum;i++)
     {
       map[i][sum]='\0';//设置每行字符串的结束符
       printf("%s\n",map[i]);//每次输出一行
     }
   }
   return 0;
}
