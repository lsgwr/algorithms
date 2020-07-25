/*zz
程序名称：N皇后问题（DFS+多重优化） 
程序说明：行优化：逐行递归，耗时O（1）
列优化：双链表（NOI专刊第10期P20页《用双链表实现搜索的优化》
 
程序作者：ZXH (2010-3-30)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 

#include <iostream>
#define MAXN 20
using namespace std;

int n,n1;//棋盘大小 
short djx1[2*MAXN],djx2[2*MAXN];//对角线优化
short pre[MAXN],next[MAXN];//双链表列优化
int a[MAXN];//保存当前答案
long s=0,s1=0;//保存总方案数 

void N_QUEEN(int dp)
{
  int i=0;
  do
  {
    i=next[i];
    if(i>n)
      break;
    if(djx1[dp+i] && djx2[dp-i+n])
    { //对对角线和双链表进行修改，去掉i结点 
      djx1[dp+i]=0;djx2[dp-i+n]=0;
      next[pre[i]]=next[i];pre[next[i]]=pre[i];
      a[dp]=i;
      if(dp==n)
      {
        if(a[1]==n1)
          s1++;
        else
          s++;
      }
      else
        N_QUEEN(dp+1);
      djx1[dp+i]=1;//还原i结点的对角线和双链表 
      djx2[dp-i+n]=1;
      next[pre[i]]=i;
      pre[next[i]]=i;
    }
  }while(a[1]<=n1);
  return;
}

void init()
{
  cin>>n;
  n1=n/2+1;
  for(int i=1;i<=n;i++)//链表初始化 
  {
    pre[i]=i-1;
    next[i]=i+1;
  }
  pre[n+1]=n;next[0]=1;//哨兵 
  memset(djx1,1,sizeof(djx1));//对角线初始化 
  memset(djx2,1,sizeof(djx2));
  return;
}

int main()
{
  freopen("queen.in","r",stdin);
  freopen("queen.out","w",stdout);
  init();
  N_QUEEN(1);
  
  if(n%2==0)//镜像复制 
    s*=2;
  else
    s=2*s+s1;
  cout<<s<<endl; 
  return 0;
}           


