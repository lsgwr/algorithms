//纵横火柴棒算法2       
#include<iostream>
using namespace std;
int map[4][5],i,j,k,x,y,z;
int num,mx,my,mk,m,Min=10000,Max=0,N,u,v=1;
char temp[4][5];//临时存输入的值 
int win[4][5],lose[4][5];//赢队列，输队列，用数组仿真 
int zui[4][5],ci[4][5];//存最优解，存次优解 

int place(int x, int y,int t) //判断某一位置是否能否安置火柴棒，x,y坐标，t为横竖状态 
{
 int m,b=1;
 if (map[x][y]!=0) 
   return 0;
 else 
   if (t==2) //如果竖直状态 
   {
     for (m=x;m<=3;m++)
     {
        if (map[m][y]==1) 
          break; 
        if (map[m][y]==2) 
          b=0;
     }
     for (m=x;m>=1;m--)
     {
       if (map[m][y]==1) 
         break;
       if (map[m][y]==2) 
         b=0;
     }
   }   
   if (t==1) //如果水平状态 
   {
     for (m=y;m<=4;m++)
     {
       if (map[x][m]==2) 
         break;
       if (map[x][m]==1) 
         b=0;
     }
     for (m=y;m>=1;m--)
     {
       if (map[x][m]==2) 
         break;
       if (map[x][m]==1) 
         b=0;
     }
   }
   return b;
}


void Try(int x,int y,int k,int d)//求出解的深度 ，x,y,k为初始状态，d为深度 
{
  int a,b,c,e=0,temp; //temp恢复map数组 
  if (d>Max) 
    Max=d;
  for (b=1;b<=4;b++)
    for (a=1;a<=3;a++)
      for (c=1;c<=2;c++)
      {
        if (place(a,b,c)==1) //如果可以放置 
        {
          e++;
          temp=map[a][b];
          map[a][b]=c;
          Try(x,y,k,d+1); 
          map[a][b]=temp;
        } 
        if (e==0) //如果不能放置任何火柴 
        {
          if ((d%2)==1) 
            win[x][y]=k; //如果奇数入赢队列 
          if ((d%2)==0) 
            lose[x][y]=k; //如果偶数入输队列 
        }
      }
}

int main()
{
  int t;
  freopen("matches2.in","r",stdin);
  freopen("matches2.out","w",stdout);
  cin>>N;
  while (N>0)
  {
    for (i=1;i<=3;i++)
      for (j=1;j<=4;j++)
        cin>>temp[i][j];
    for (j=1;j<=4;j++)
      for (i=1;i<=3;i++)
      {
        if (temp[i][j]=='1') //1代表横，2代表竖 
          map[i][j]=2;
        if (temp[i][j]=='-')
          map[i][j]=1;
      }
    for (i=1;i<=3;i++) //穷举 
      for (j=1;j<=4;j++)
        for (k=1;k<=2;k++)
        {
          if (place(i,j,k)==1)  
          {
             t=map[i][j];
             map[i][j]=k;
             Try(i,j,k,1); 
             map[i][j]=t; //恢复 
          }
        }
  for (i=1;i<=3;i++)
     for (j=1;j<=4;j++)
     {
       if ((win[i][j]!=0) && (lose[i][j]==0)) //如果赢队列里面答案元素不在输队列中为最优解 
         zui[i][j]=win[i][j];
       if ((win[i][j]!=0) && (lose[i][j]==win[i][j])) //如果在，就是次优解 
         ci[i][j]=win[i][j];
     }
   for (i=1;i<=3;i++) 
     for (j=1;j<=4;j++)
       if (zui[i][j]!=0) //如果有最优解，则输出 
       {
         v=0;
         cout<<i-1<<j-1;
         if (zui[i][j]==2) 
           cout<<1;  
         if (zui[i][j]==1) 
           cout<<"-";
         return 0;
      }
   if (v==1); //如果没有最优解，输出次优解 
   {
     for (i=1;i<=3;i++)
       for (j=1;j<=4;j++)
         if (ci[i][j]!=0)
         {
           cout<<i-1<<j-1;
           if (ci[i][j]==2) 
             cout<<1;
           if (ci[i][j]==1) 
             cout<<"-";
           return 0;
         }
   }
  /*for (i=1;i<=3;i++)
   for (j=1;j<=4;j++)
    cout<<i<<' '<<j<<' '<<win[i][j]<<' '<<lose[i][j]<<' '<<endl;*/
  N--;
 }
 return 0;
}
