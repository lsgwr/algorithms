//∆Â≈Ã∑÷∏Ó 
#include <iostream>   
#include <cstdlib>     
#include <cmath>   
#define N 20   
#define INF 0x7ffffff   
#define M 8   
using namespace std;  
int sum[N][N][N][N],dp[N][N][N][N][N];  
int a[N][N];  
int main()  
{  
  freopen("chessboard.in","r",stdin);   
  freopen("chessboard.out","w",stdout);  
  int n;  
  while(scanf("%d",&n)!=EOF)  
  {  
    for(int i=0;i<=M-1;i++)    
      for(int j=0;j<=M-1;j++)  
        scanf("%d",&a[i][j]);    
    
    for(int x1=0;x1<=M-1;x1++)  
      for(int y1=0;y1<=M-1;y1++)  
        for(int x2=x1;x2<=M-1;x2++)  
          for(int y2=y1;y2<=M-1;y2++)  
          {  
             if(y2==y1&&x2==x1)  
               sum[x1][y1][x2][y2] = a[x1][y1];  
             else if(x2==x1)  
               sum[x1][y1][x2][y2] = sum[x1][y1][x2][y2-1]+a[x2][y2];  
             else if(y2==y1)    
               sum[x1][y1][x2][y2] = sum[x1][y1][x2-1][y2] + a[x2][y2];  
             else   
               sum[x1][y1][x2][y2]=sum[x1][y1][x2][y2-1]+sum[x1][y1][x2-1][y2]-sum[x1][y1][x2-1][y2-1]+a[x2][y2];  
             dp[1][x1][y1][x2][y2] = sum[x1][y1][x2][y2]*sum[x1][y1][x2][y2];  
          }   
  for(int k=2;k<=n;k++)  
    for(int x1=0;x1<=M-1;x1++)  
      for(int y1=0;y1<=M-1;y1++)  
        for(int x2=x1;x2<=M-1;x2++)   
          for(int y2=y1;y2<=M-1;y2++)  
          {  
            dp[k][x1][y1][x2][y2]=INF;  
            for(int t = x1;t<=x2-1;t++) //hor   
            {  
              dp[k][x1][y1][x2][y2]=min(dp[k][x1][y1][x2][y2],dp[1][x1][y1][t][y2]+dp[k-1][t+1][y1][x2][y2]);  
              dp[k][x1][y1][x2][y2]=min(dp[k][x1][y1][x2][y2],dp[k-1][x1][y1][t][y2]+dp[1][t+1][y1][x2][y2]);  
            }  
            for(int t = y1;t<=y2-1;t++) //ver   
            {  
              dp[k][x1][y1][x2][y2]=min(dp[k][x1][y1][x2][y2],dp[1][x1][y1][x2][t]+dp[k-1][x1][t+1][x2][y2]);  
              dp[k][x1][y1][x2][y2]=min(dp[k][x1][y1][x2][y2],dp[k-1][x1][y1][x2][t]+dp[1][x1][t+1][x2][y2]);  
            }  
          }   
    double temp = (double)(sum[0][0][M-1][M-1])/(double)(n);  
    double res1 = temp*temp;  
    double res = (double)(dp[n][0][0][M-1][M-1])/(double)n - res1;  
    res = sqrt(res);  
    res = res*1000;  
    res+=0.5;  
    int R = (int)(res);  
    res = (double)(R)/1000;  
    printf("%.3lf\n",res);  
  }  
  return 0;  
}  
