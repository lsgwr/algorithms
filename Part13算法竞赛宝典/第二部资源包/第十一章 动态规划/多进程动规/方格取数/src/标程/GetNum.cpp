//方格取数 
#include <iostream>
#include <cstdlib>
using namespace std;
int a[10+1][10+1];//存放（x,y）的数字 
int f1[100+1][100+1],f2[100+1][100+1];//分别存放i-1,i个阶段的状态转移方程 
int k,j,p1,b1,p2,b2,x1,y1,x2,y2,d1,d2,x,y,z,n;

void init()
{
  cin>>n;
  n++;//给二维表多一行一列，保证起点无数据 
  x=1;
  while((x>0))
  {
    cin>>x>>y>>z;
    a[x+1][y+1]=z;//a数组存放结点数据，对应行列坐标都加1 
  }     
}

void dp()
{
  for(k=1;k<=2*n-1;k++)//准备走第k步 
  {
    for(p1=1;p1<=k;p1++)//枚举两条路径在第i-1步时的状态p1和b1 
      for(b1=p1;b1<=k;b1++)//b1从p1开始，避免（x1,x2),(x2,x1)重复计算 
      {
        p2=k+1-p1;//y1=k+1-x1
        b2=k+1-b1;//y2=k+1-x2
        if(p1<=n && p1>=1 && b1>=1 && p2>=1 && p2<=n && b2>=1 && b2<=n)//未越界 
        {
          for(d1=0;d1<=1;d1++)//第一条路沿d1方向延伸到（x1,y1) 
            for(d2=0;d2<=1;d2++)//第二条路沿d2方向延伸（x2,y2) 
            {
              x1=0;y1=0;x2=0;y2=0;
              if(d1==0)//向右延伸 
              {
                x1=p1;
                y1=p2+1;
              }
              else//向下延伸 
              {
                x1=p1+1;
                y1=p2;
              }
              if(d2==0)//向右延伸 
              {
                x2=b1;
                y2=b2+1;
              }
              else//向下延伸 
              {
                x2=b1+1;
                y2=b2;
              }
              //////////////计算第i步的状态转移方程
              if(x1<=n&&x1>=1&&y1<=n&&y1>=1&&x2<=n&&x2>=1&&y2>=1&&y2<=n)              {
                if(x1==x2) 
                {
                  if(f2[x1][x2]<(f1[p1][b1]+a[x1][y1]))//如果走到一起
                    f2[x1][x2]=(f1[p1][b1]+a[x1][y1]);
                }
                else//如果分开两条 
                {
                  if(f2[x1][x2]<(f1[p1][b1]+a[x1][y1]+a[x2][y2]))
                    f2[x1][x2]=(f1[p1][b1]+a[x1][y1]+a[x2][y2]);
                }
              }
            }
        }
      }
    for(x1=1;x1<=k;x1++)//记下第i步的状态转移方程 
      for(x2=1;x2<=k;x2++)
        f1[x1][x2]=f2[x1][x2];
  }     
}

int main()
{
  freopen("GetNum.in","r",stdin);
  freopen("GetNum.out","w",stdout);  
  init();
  dp();
  cout<<f1[n][n]<<endl;
  return 0;
}
