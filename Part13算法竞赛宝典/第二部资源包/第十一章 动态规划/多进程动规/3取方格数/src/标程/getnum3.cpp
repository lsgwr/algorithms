//3取方格数 
#include<stdio.h>
#include<string.h>

int main()
{
  freopen("getnum3.in","r",stdin);
  freopen("getnum3.out","w",stdout);  
  static int f[41][21][21][21],w[21][21];
  int n,m,i,j,k,s,x1,x2,x3,y1,y2,y3,d1,d2,d3,get;
  memset(f,0,sizeof(f));
  scanf("%d",&n);
  for(i=1;i<=n;++i)
    for(j=1;j<=n;++j)
      scanf("%d",&w[i][j]);
  f[1][1][1][1]=w[1][1];//初始化 
  for(s=2;s<n*2;++s)
    for(x1=1;x1<=n;++x1)
      for(x2=1;x2<=n;++x2)
        for(x3=1;x3<=n;++x3)
        {
          y1=s-x1+1;//计算y1的值 
          y2=s-x2+1;//计算y2的值 
          y3=s-x3+1;//计算y3的值 
          if(y1<1||y2<1||y3<1||y1>n||y2>n||y3>n)
            continue;//防止越界 
          get=w[x1][y1];
          if(x2!=x1)//两人的下一步不重合 
            get+=w[x2][y2];//累加 
          if(x3!=x2 && x3!=x1)//三人的下一步不重合 
            get+=w[x3][y3];//累加 
          for(d1=-1;d1<=0;++d1)//8个方向 
            for(d2=-1;d2<=0;++d2)
              for(d3=-1;d3<=0;++d3)
              {
                i=x1+d1;j=x2+d2;k=x3+d3;
                if(i<1 || j<1 ||k<1) 
                  continue;
                if(f[s-1][i][j][k]+get>f[s][x1][x2][x3])
                  f[s][x1][x2][x3]=f[s-1][i][j][k]+get;
              }      
        }      
  printf("%d\n",f[n*2-1][n][n][n]);
  return 0;        
}
