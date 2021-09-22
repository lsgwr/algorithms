/*
程序名称：N皇后问题（非递归+多重优化） 
程序说明：
 
程序作者：ZXH (2010-3-30)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 

#include <iostream>
using namespace std;
const int MAXN=20;
int n,n1;//棋盘大小 
int a[MAXN];//保存当前答案
short pre[MAXN],next[MAXN];//双链表列优化
short cd1[2*MAXN],cd2[2*MAXN];//对角线优化
long s=0,s1=0;//保存总方案数 

void init()
{
  cin>>n;
  n1=n/2+1;
  memset(cd1,0,sizeof(cd1));//对角线初始化 
  memset(cd2,0,sizeof(cd2));
  for(int i=1;i<=n;i++)//链表初始化 
  {
    pre[i]=i-1;
    next[i]=i+1;
  }
  pre[n+1]=n;next[0]=1;//哨兵
}

void Doit()
{
  int dp=1,flag=0;
  do
  {
    if(a[1]<=n1)
    {
      if(dp>n)
      {
        if(a[1]==n1)
          s1++;
        else
          s++;
        dp--;
        cd1[dp-a[dp]+n]=0;
        cd2[dp+a[dp]]=0;
        next[pre[a[dp]]]=a[dp];
        pre[next[a[dp]]]=a[dp];
        a[dp]=0;
        dp--;
      }
      else
      {
        if(a[dp]!=0)
        {
          cd1[dp-a[dp]+n]=0;
          cd2[dp+a[dp]]=0;
          next[pre[a[dp]]]=a[dp];
          pre[next[a[dp]]]=a[dp];
        }
        int i=a[dp];
        i=next[i];
        while(i<=n && (cd1[dp-i+n] || cd2[dp+i]))
          i=next[i];
        if(i>n)
        {
          a[dp]=0;
          dp--;
          continue;
        }        
        cd1[dp-i+n]=1;
        cd2[dp+i]=1;
        next[pre[i]]=next[i];
        pre[next[i]]=pre[i];
        a[dp]=i;
        dp++;
      }
    }
  }while(a[1]<=n1);      
}


int main()
{
  freopen("queen.in","r",stdin);
  freopen("queen.out","w",stdout);
  init();
  Doit();
  
  if(n%2==0)//镜像复制 
    s*=2;
  else
    s=2*s+s1;
  cout<<s<<endl; 
  return 0;
}           


