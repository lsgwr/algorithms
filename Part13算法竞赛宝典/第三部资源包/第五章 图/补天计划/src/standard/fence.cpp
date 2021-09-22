/*
DFS+剪枝 
一、确定起点 
分2类讨论
1、图中有且仅有2个奇数点（与之相连的路径个数为奇数的节点） 
先找出两个奇数点，以编号较小的点作为起点，另一个为终点。
2、图中没有奇数点
以编号最小的点作为起点和终点。 
二、贪心DFS+剪枝
0、检查边的遍历情况，若已全部遍历则输出遍历序列，然后exit(0)。 
1、floodfill一次，判断从当前点能否遍历剩余的所有边，若不能，则返回；若能，则继续。 
2、从小到大逐个枚举当前点能到达的点，将连向该点的边①删除，并对该点执行DFS。
3、若DFS过程返回，则恢复边①，继续枚举下一个点。
*/
#include <fstream>
#include <cstdlib>
#include <iomanip>
#include <string.h>
 
using namespace std;
 
ifstream fin ("fence.in");
ofstream fout ("fence.out");
 
const long MAXN=510,MAXF=1550;
 
long mapS[MAXN][MAXN];
long nodeCounter[MAXN];
long n;
long path[MAXF],upPath;
 
void init()
{
     fin >>n;
     memset(mapS,0,sizeof(mapS));
     memset(nodeCounter,0,sizeof(nodeCounter));
     long a,b;
     for (long i=0;i<n;++i)
     {
         fin >>a >>b;
         ++mapS[a][b];
         ++mapS[b][a];
         ++nodeCounter[a];
         ++nodeCounter[b];
     }
     upPath=-1;
     return;
}
 
long getStart()     //获取起点 
{
     long oddCounter=0,minOdd=MAXN;
     for (long i=1;i<MAXN;++i)
         if (nodeCounter[i]&1)
         {
            ++oddCounter;
            if (minOdd==MAXN) minOdd=i;
         }
     if (oddCounter==2)
        return minOdd;
     else if (oddCounter==0)
          return 1;
     else
     {
         fout <<"getStart Error:oddCounter=" <<oddCounter <<endl;
         return -1;
     }
}
 
bool floodfill(long node)
{
     long stack[MAXN],top=-1;
     bool vis[MAXN];
     memset(vis,0,sizeof(vis));
     stack[++top]=node;
     vis[node]=true;
     while (top>-1)
     {
           node=stack[top--];
           for (long i=1;i<MAXN;++i)
           {
               if (mapS[node][i] && !vis[i])
               {
                  stack[++top]=i;
                  vis[i]=true;
               }
           }
     }
     for (long i=1;i<MAXN;++i)
         if (!vis[i] && nodeCounter[i])
         {
            return false;
         }
     return true;
} 
 
 
void DFS(long node)
{
     if (upPath==n)                    //如果路径长度等于F+1则输出路径 并exit(0) 
     {
        for (long i=0;i<=upPath;++i)
            fout <<path[i] <<endl;
        exit(0);
     }
 
     if (!floodfill(node))     //若无法遍历剩余全图则返回 
     {
        return;
     }
 
     for (long i=1;i<MAXN;++i)    //枚举点
     {
         if (mapS[node][i]) 
         {
            //删边
            --mapS[node][i];
            --mapS[i][node];
            --nodeCounter[i];
            --nodeCounter[node];
 
            path[++upPath]=i;         
            DFS(i);
            --upPath;
            //恢复边 
            ++mapS[node][i];
            ++mapS[i][node];
            ++nodeCounter[i];
            ++nodeCounter[node];
         }
     }
     return;
}
 
int main()
{
    init();
    path[++upPath]=getStart();
    DFS(path[upPath]);
    return 0;
}
