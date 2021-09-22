//kruskal算法
#include<iostream>
#define MAXN 2000
#define INF 99999999
using namespace std;
int n,e;/*点数和边数*/
int x[MAXN],y[MAXN],w[MAXN];/*x为边的起点，y为终点，w为边的权值*/
int dad[MAXN];/*每个节点属于的父亲集合*/


void sort(int i,int j)//快排，给边权排序
{
  if(i>=j)     
    return ;
  int m,n,t,k;
  m=i,n=j;
  k=w[ (i+j) >> 1];//取中值   
  while(m<=n)
  {
    while(w[m]<k)
	  m++;
    while(w[n]>k)
	  n--;
    if(m<=n)          
	{
	  t=x[m];
	  x[m]=x[n];
	  x[n]=t;
	  t=y[m];
	  y[m]=y[n];
	  y[n]=t;
	  t=w[m];
	  w[m]=w[n];
	  w[n]=t;            
	  m++;
	  n--;             
	}        
  }
  sort(i,n);
  sort(m,j);      
}

int getfather(int x)/*查找点x属于的集合*/
{
  if(x==dad[x]) 
    return x;                
  dad[x]=getfather(dad[x]);//查找过程中更新x的父亲集合，相当于并查集归并过程
  return (dad[x]);
}
 
void kruskal()
{
   int i,p,ans;/*p:已经加入的边数,ans:加入边的边权和*/
   for(i=1;i<=n;i++)
     dad[i]=i;/*初始化点的集合*/
   p=1,ans=0;//p为顶点数，为加快计算速度p初始化为1所以当p=n时结束计算
       
   for(i=1;i<=e;i++)   
   {
     if(getfather(x[i])!=getfather(y[i]))//如果边的两点不在同一个集合内，则归并两个集合                
	 {
	   ans+=w[i];/*加入这条边并统计其权值*/                
	   dad[getfather(x[i])]=y[i];//合并为同一父亲集合,此处是取y[i]的值 
	   p++;                
	   if(p == n)//如果最小生成树中的顶点数等于全部顶点数 - 1
       {
         cout<<ans<<"\n";
         return ;
       } 
     }               
   }
   return ;
} 

int main()
{
  int i,j;        
  cin>>n>>e;//输入节点数和边数 
  for(i=1;i<=e;i++)
    cin>>x[i]>>y[i]>>w[i];
  sort(1,e);/*将边按权值大小排序*/
  kruskal();
  system("pause");
  return 0;         
}
