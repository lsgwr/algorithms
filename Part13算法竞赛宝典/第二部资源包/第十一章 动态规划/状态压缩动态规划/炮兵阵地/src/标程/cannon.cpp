//炮兵阵地 
#include <cstdlib>
#include <iomanip>
#include <iostream>
using namespace std;

int f[110][150][150];
int s[110][150];
int num[110][150];
int N,M;
bool map[1100][1100];//预处理所有状态的冲突情况，用map[][]数组标记

inline int getnum(int x)//计算x转二进制数后有多少0 
{
  int t=0;
  while(x>0)
  {
    x=x&(x-1);
    t++;
  }
  return t;
}

void init()
{
  cin>>N>>M;
  string tmp;int q;
  for(int i=1;i<=N;i++)
  {
    cin>>tmp;
    q=0;
    for(int j=0;j<tmp.size();j++)//状态压缩地形 
      if(tmp[j]=='P')//如果是平原设为0 
        q=(q<<1); 
      else//如果是山地,设为1 
        q=(q<<1)|1;
   
    for(int j=0;j<(1<<M);j++)//枚举所有方案0-111..111 
    {
      if((j<<1)&j || (j<<2)&j || (j>>1)&j || (j>>2)&j ) 
        continue;//若一行内有冲突，即左右两格内有炮阵，则舍弃 
      if( q&j ) //若该地形与枚举的二进制数j方案有冲突，则舍弃 
        continue;
      ++s[i][0];//计数 
      s[i][s[i][0]]=j;//记录该方案 
      num[i][s[i][0]]=getnum(j);//记录该方案的炮兵数 
    }
  }
  for(int i=0;i<=(1<<M);i++)//预处理方案i与j是否冲突 
    for(int j=0;j<=(1<<M);j++)
      if( !(i&j) )
        map[i][j]=true;
}

void Dp()
{
  s[0][0]=1;
  int s1,s2,m=1;
  for(int i=1;i<=N;i++,m=m^1)//动规，第i行 
    for(int a=1;a<=s[i-1][0];a++)//a方案 
    {
      s1=s[i-1][a];//i-1行的a方案 
      for(int b=1;b<=s[i][0];b++)//b方案 
        if( map[s1][s[i][b]] )//若a,b方案不冲突 
        {
          s2=s[i][b];//i行的b方案     
          if(i==1)//若i是第一行 
            f[m][a][b]=max(f[m][a][b],num[i][b]);
          else
          {    
            for(int c=1;c<=s[i-2][0];c++)//i-2行的c方案 
              if( map[s[i-2][c]][s1] && map[s[i-2][c]][s2])//若3方案各不冲突 
                f[m][a][b]=max(f[m][a][b],f[m^1][c][a]+num[i][b]);
          }  
        }
    }
  int Ans=0;
  m=m^1;
  for(int a=1;a<=s[N-1][0];a++)
    for(int b=1;b<=s[N][0];b++)
      Ans=max(Ans,f[m][a][b]);
  cout<<Ans<<endl;
}

int main()
{
  freopen("cannon.in","r",stdin);
  freopen("cannon.out","w",stdout);
  init();
  Dp();
  return 0;
}
