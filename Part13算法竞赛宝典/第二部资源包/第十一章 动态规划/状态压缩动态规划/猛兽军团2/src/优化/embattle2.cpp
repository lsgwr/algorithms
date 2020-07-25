/*猛兽军团2－优化 
  空间优化：put[][]数组滚动，dp数组滚动
  时间优化：s[]数组的引入
*/
#include <iostream>
#include <cstdlib>
using namespace std;
#define MOD 100000000

int M,N,line[13];
int put[2][1000];
int s[1000];
int f[2][1000],Ans=0,m=1,dm;

void init()
{
  cin>>M>>N;
  for(int x,i=1;i<=M;i++)
    for(int j=1;j<=N;j++)
    {
      cin>>x;
      line[i]=(line[i]<<1)+1-x;
    }
  for(int i=0;i<(1<<N);i++)
    if( !((i<<1)&i) && !((i>>1)&i) )
      s[++s[0]]=i;
}

void Dp()
{
  int s1,s2;
  f[0][1]=1;put[0][0]=1;
  for(int i=1;i<=M;i++) 
  {
    dm=m^1;  
    for(int j=0;j<1000;j++) 
      put[m][j]=0;//初始化 
        
    for(int j=1;j<=s[0];j++)  //预处理 
    {
      if( s[j]&line[i] ) 
        continue;
      put[m][0]++;
      put[m][put[m][0]]=s[j];
    }
    for(int j=0;j<=put[m][0];j++)   
      f[m][j]=0;//初始化 
        
    for(int a=1;a<=put[dm][0];a++)//上一行 
    {
      s1=put[dm][a];
      for(int b=1;b<=put[m][0];b++)//这一行 
      {
        s2=put[m][b];
        if(s1&s2) 
          continue;
        f[m][b]+=f[dm][a];
        f[m][b]%=MOD;
      }
    }
    m=m^1;
  } 
  m=m^1;
  for(int i=1;i<=put[m][0];i++)
  {
    Ans+=f[m][i];
    if(Ans>MOD) 
      Ans%=MOD;
  }
  cout<<Ans<<endl;
}

int main()
{
  freopen("embattle2.in","r",stdin);
  freopen("embattle2.out","w",stdout);     
  init();
  Dp();
  return 0;
}
