//封闭面积问题
#include<iostream>
#include<fstream>
#define MAXN 90000
using namespace std;

ifstream fin("area.in");
ofstream fout("area.out");

struct img
{
  short int x;
  short int y;
}a[MAXN];


int main()
{   
    char temp;
    short int dx[4],dy[4];
   
    dx[0]=0;  dy[0]=1;  //建立方向 
    dx[1]=1;  dy[1]=0;
    dx[2]=0;  dy[2]=-1;
    dx[3]=-1;  dy[3]=0;
   
    int m,n,tt,kk,xing=0,ttxx,ttyy;
    fin>>m>>n;
    bool pos[n+2][m+2];
    memset(pos,0,sizeof(pos));
   
    int i,j;
    for(i=1;i<=n;++i)   //输入图像 
      for(j=1;j<=m;++j)
      {  fin>>temp;
         if(temp=='*')  {pos[i][j]=1;  xing++;}
         else if(temp=='0')  pos[i][j]=0;   
      }
    for(i=1;i<=n+1;++i)
        pos[i][m+1]=1;
    for(i=1;i<=m+1;++i)
        pos[n+1][i]=1;
    for(i=0;i<=m+1;++i)
        pos[0][i]=1;
    for(i=1;i<=n+1;++i)
        pos[i][0]=1;      
     
   int sum=1,front,rear;
   tt=1;
   kk=1;
   a[1].x=1;     a[1].y=1;  
   pos[1][1]=1;
   
   while(tt<=kk) 
   {
       for(i=0;i<=3;++i)  //各个方向各试一遍 
       {
          front=tt%MAXN;
          ttxx=a[front].x+dx[i];;
          ttyy=a[front].y+dy[i];
          if( pos[ttyy ][ ttxx ]==0  )   
          {
                kk++;
                rear=kk%MAXN;
                a[rear].x=ttxx;
                a[rear].y=ttyy;
                sum++;
                pos[ ttyy ][ ttxx ]=1;
          } 
       }  
      tt++;
      
    }
  
    fout<<m*n-sum-xing<<endl;
    return 0;
    fin.close();
    fout.close();
}
