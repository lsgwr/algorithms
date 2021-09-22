//合并魔法石2－方法2 
#include<stdio.h>
#include<iostream>
using namespace std;
 
#define MAX_LONG 0x7fffffff
 
struct Node{ //当前序列的合并方案
     long c; //得分和
     int d; //子序列的堆数
};
 
long sumtype[101][101]; //sumtype[i][j] - 子序列[i,j]的石子总数
Node list[101][101]; //st[i][j] - 子序列[i,j]的合并方案
int date[101],dt[101]; //date[i] - 第i堆石子数,dt - 暂存date
int i,j,N; //N - 石子堆数, i,j - 循环变量
 
void Print(int i,int j) //递归打印子序列[i,j]的合并过程
{
     int k,x; //k - 循环变量,x - 子序列中首堆石子的序号
     if(j != 1) //继续倒推合并过程
     {
         Print(i,list[i][j].d); //倒推子序列的合并过程
         x=(i+list[i][j].d-1)%N+1; //求子序列中首堆石子的序号
         Print(x,j-list[i][j].d); //倒推子序列的合并过程
         for(k=1;k<=N;k++) //输出当前合并第i堆,第x堆石子的方案
              if(date[k]>0)
                   if(i==k || k==x)
                       printf("-%d ",date[k]);
                   else
                       printf("%d ",date[k]);
         printf("\n");
         date[i]=date[i]+date[x]; //原第i堆和第x堆合并成第i堆
         date[x]=-date[x]; //将原第x堆从圈内去除
     }
}
 
void solve(int s)
{
     int i,j,k;
     long t,x;
     for(i=1;i<=N;i++) //仅含一堆石子的序列不存在合并
     {
         list[i][1].c=0;
         list[i][1].d=0;
     }
     for(j=2;j<=N;j++) //顺推含堆,含堆……含N堆石子的各子序列的合并方案
         for(i=1;i<=N;i++) //当前考虑从第i堆数起,顺时针数j堆的子序列
         {
              if(s==1) //合并[i,j]子序列的得分和初始化
                   list[i][j].c=MAX_LONG;
              else
                   list[i][j].c=0;
              t=sumtype[i][j]; //最后一次合并的得分为[i,j]子序列的石子总数
              for(k=1;k<=j-1;k++) //子序列的石子堆数依次考虑堆……j-1堆
              {
                   x=(i+k-1)%N+1; //求子序列首堆序号
                   if((s==1 && list[i][k].c+list[x][j-k].c+t<list[i][j].c) ||
                       (s==2 && list[i][k].c+list[x][j-k].c+t>list[i][j].c))
                       //若该合并方案为目前最佳,则记下
                   {
                       list[i][j].c=list[i][k].c+list[x][j-k].c+t;
                       list[i][j].d=k;
                   }
              }
         }
     //在子序列[1,N],[2,N],……,[N, N]中选择得分总和最小(或最大)的一个子序列
     k=1;
     x=list[1][N].c;
     for(i=2;i<=N;i++)
         if((s==1 && list[i][N].c<x) || (s==2 && list[i][N].c>x))
         {
              k=i;
              x=list[i][N].c;
         }
     //Print(k,N); //由此出发,倒推合并过程
     //printf("%d\n%d\n",sumtype[1][N], x); //输出最后一次将石子合并成一堆的石子总数
     //printf("\n");
     printf("%d",list[k][N].c);
}
 
int main()
{
     freopen("merge2.in", "r", stdin);
	freopen("merge2.out", "w", stdout);
     scanf("%d",&N); //读入石子堆数
     for(i=1;i<=N;i++)
         scanf("%d",&date[i]); //读入每堆石子数
     for(i=1;i<=N;i++) //求每一个子序列的石子数sumtype
         sumtype[i][1]=date[i];
     for(j=2;j<=N;j++)
         for(i=1;i<=N;i++)    //注意读入的技巧 
              sumtype[i][j]=date[i]+sumtype[i%N+1][j-1];
     
     for(i=1;i<=N;i++) //暂存合并前的各堆石子
         dt[i]=date[i];
     solve(2); //求得分和最小的合并方案
     printf(" ");
     for(i=1;i<=N;i++) //恢复合并前的各堆石子
         date[i]=dt[i];
     solve(1); //求得分和最大的合并方案
     printf("\n");
     return 0;
}
