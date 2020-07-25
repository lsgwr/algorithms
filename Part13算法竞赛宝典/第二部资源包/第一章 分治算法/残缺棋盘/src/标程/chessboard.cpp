//残缺棋盘 
#include <iostream>
#include <cstdlib>
using namespace std;
int k=1,c[1024][1024];//左上角坐标设为（1，1） 

void lt(int x1,int y1,int x2,int y2 )//左上 
{
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2]=4;
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2+1]=4;
  c[x1+(x2-x1)/2][y1+(y2-y1)/2+1]=4; //填充图形4     
}

void lb(int x1,int y1,int x2,int y2 )//右上 
{
  c[x1+(x2-x1)/2][y1+(y2-y1)/2]=2;
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2]=2;
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2+1]=2;//填充图形2   
}

void rt(int x1,int y1,int x2,int y2 )//左下
{
  c[x1+(x2-x1)/2][y1+(y2-y1)/2]=3;
  c[x1+(x2-x1)/2][y1+(y2-y1)/2+1]=3;
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2+1]=3;//填充图形3      
}
 
void rb(int x1,int y1,int x2,int y2 )//右下
{
  c[x1+(x2-x1)/2+1][y1+(y2-y1)/2]=1;
  c[x1+(x2-x1)/2][y1+(y2-y1)/2]=1;
  c[x1+(x2-x1)/2][y1+(y2-y1)/2+1]=1;//填充图形1      
} 

void work(int x1,int y1,int x2,int y2)//递归函数 
{
  int i,j,p,q;   
  if(x2-x1==1)//当方格为2*2时,填充 并结束 
  {
    for(i=x1;i<=x2;i++)//查找坏点或已覆盖点在何处 
      for(j=y1;j<=y2;j++)
        if(c[i][j]!=0)
          p=i,q=j;
    if(p==x1 && q==y1)//在左上角
      lt(x1,y1,x2,y2);
    if(p==x1 && q==y2)  //在左下角
      lb(x1,y1,x2,y2);
    if(p==x2 && q==y1)//在右上角 
      rt(x1,y1,x2,y2);
    if(p==x2 && q==y2)  //在右下角   
      rb(x1,y1,x2,y2);
  }   
  else
  {
    for(i=x1;i<=x2;i++)  //查找该方块的坏点或已覆盖的点在何处 
      for(j=y1;j<=y2;j++)
        if(c[i][j]!=0)
          p=i,q=j;
    if(p<=(x1+(x2-x1)/2))
      if( q<=(y1+(y2-y1)/2))//如该点位于左上角
        lt(x1,y1,x2,y2);
      else  //如该点在左下角 
        lb(x1,y1,x2,y2);
    if(p>(x1+(x2-x1)/2))
      if (q<=(y1+(y2-y1)/2))  //如该点位于右上角
        rt(x1,y1,x2,y2);
      else  //如该点在右下角 
        rb(x1,y1,x2,y2);
    work(x1,y1,(x1+(x2-x1)/2),(y1+(y2-y1)/2));//平分为四块后递归 
    work((x1+(x2-x1)/2+1),y1,x2,(y1+(y2-y1)/2));
    work(x1,(y1+(y2-y1)/2+1),(x1+(x2-x1)/2),y2);
    work((x1+(x2-x1)/2+1),(y1+(y2-y1)/2+1),x2,y2);    
  }
}

void out()//输出数组 
{
  int i,j;   
  for(i=1;i<=k;i++)
  {
    for(j=1;j<=k;j++)
     cout<<c[j][i]<<' ';
    cout<<endl;
  }       
}

int main()
{ 
  freopen("chessboard.in","r",stdin);
  freopen("chessboard.out","w",stdout);  
  int i,n,x,y;
  cin>>n>>x>>y;
  for(i=1;i<=n;i++)
    k=k*2;  
  c[x][y]=7;//定义坏的坐标为7 
  work(1,1,k,k);//左上顶点坐标为（1,1)，右下顶点坐标为（k,k） 
  out();
  return 0;
}
