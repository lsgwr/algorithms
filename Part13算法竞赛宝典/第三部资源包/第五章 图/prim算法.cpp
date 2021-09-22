/*
程序名称：prim算法的演示程序 
程序说明：这里使用无向图
*/
#include<iostream>
#define MAXN 2001
#define INF 999999999
using namespace std;
int n,e;
int w[MAXN][MAXN];/*两点之间边的权值*/
int mincount[MAXN];/*从初始顶点到该顶点的最小权值*/

void prim(int s)
{
  int i,j,count=0,min;/*count为生成树所有边的权值和*/           
  int k;
  for(i=1;i<=n;i++)
    mincount[i]=w[s][i];/*初始化，设w(1,i)为初始点k到i的最小权值，如果没有则为+∞*/
  mincount[s]=0;

  for(i=1;i<n;i++)         
  {
    min=INF;
    for(j=1;j<=n;j++) 
	if(mincount[j]!=0 and mincount[j]<min)//如该节点没有被加入到SMT中并且该点权值为当前最小  
    {
      min=mincount[j];
      k=j;  //记录该点
    }

    mincount[k]=0;//把这个点加入到最小生成树中
    count+=min; //将这条边加入到最小生成树中

    for(j=1;j<=n;j++) /*修正初始点到每个点的最小权值*/
     if(w[k][j]<mincount[j])
       mincount[j]=w[k][j];       
  }
  cout<<count<<"\n";         
}

void init()
{
  int i,j,tx,ty;        
  for(i=0;i<=MAXN;i++)
    for(j=0;j<=MAXN;j++)
       w[i][j]=INF;
          
  cin>>n>>e;
  for(i=1;i<=e;i++)              
  {
    cin>>tx>>ty>>w[tx][ty];
    w[ty][tx]=w[tx][ty];   
  }
}

int main()//演示过程,从标号为1的顶点开始构造生成树
{
  init();
  prim(1);              
  return 0;         
}
