//猛兽军团1－优化 
#include <iostream>
#include <cstdlib>
using namespace std;
#define MAXN 2000
#define MAXK 1100

long long f[2][MAXN][MAXK],Ans;
int N,K;
int s[MAXN],num[MAXN];
int next[MAXN*10],h[MAXN*10],st[MAXN],flag;

inline int getnum(int x)//得到猛兽个数
{
  int t=0;
  while(x>0)
  {
    x=x&(x-1);
    t++;
  }
  return t;
}

void DFS(int len,int now)//速度较穷举略快，但不明显 
{
  if(len==N)//搜到了一个状态 
  {
    ++s[0];
    s[ s[0] ]=now;
    num[ s[0] ]=getnum(now);
    return ;
  }
  DFS(len+1,now<<1);//不放置猛兽的情况 
  if((now&1)!=1)//放置猛兽的情况,前提now是偶数（最右位不是1）
    DFS(len+1,(now<<1)|1);
}

void add(int one,int two)//加入链接表存储
{
  next[++flag]=st[one];
  h[flag]=two;
  st[one]=flag;
}

void init()
{
  cin>>N>>K;
  DFS(0,0);//深搜得到s[]
  int s1,s2;
  add(1,1);//s[1]=00000 (n个0)
  for(int i=1;i<=s[0];i++)//穷举所有方案，预处理边边关系数组
    for(int j=i+1;j<=s[0];j++) 
    {
      s1=s[i],s2=s[j];
      if(((s2<<1) & s1) || ((s2>>1) & s1) || (s1&s2))//若有冲突 
        continue;//忽略 
      add(i,j);add(j,i);
    }
}

void Dp()
{
  f[0][1][0]=1;
  int s1,s2,m=1,dm;
  for(int i=1;i<=N;i++)//行循环
  {
    dm=m^1;//通过异或实现滚动数组下标m在0,1之间滚动 
    for(int a=1;a<=s[0];a++)//滚动数组每次都需要初始化
      for(int b=0;b<=K;b++) 
        f[m][a][b]=0;
    for(int a=1;a<=s[0];a++)//枚举此行状态
      for(int k=0;k<=K-num[a];k++)//枚举猛兽个数
        for(int b=st[a];b!=0;b=next[b])//枚举上一行状态
        {
          s2=h[b];
          f[m][a][k+num[a]]+=f[dm][s2][k];
        }
    m=dm;
  }
  m=m^1;
  for(int i=1;i<=s[0];i++)//统计答案
    Ans+=f[m][i][K];
  cout<<Ans<<endl;
}

int main()
{
  freopen("embattle.in","r",stdin);
  freopen("embattle.out","w",stdout);  
  init(); 
  Dp();
  return 0;
}
