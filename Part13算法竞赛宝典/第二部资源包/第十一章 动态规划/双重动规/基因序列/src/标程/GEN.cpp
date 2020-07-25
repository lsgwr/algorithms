//ЛљвђађСа 
#include<algorithm>
#include<iostream>
#include<cmath>
#define LL long long
#define INF 99999999
#define Min(num1,num2) if(num1>num2) num1=num2
#define Max(num1,num2) if(num1<num2) num1=num2
using namespace std;

int n,f[101][101],g[101],num[101],map[101][101];
string s;

void work()
{
  memset(f,0,sizeof f);
  cin>>s;
  int l=s.size();
  for(int k=1,i=0;i<l;k++,i++) 
    f[k][k]=num[s[i]-'A'];
  for(int p=1;p<=l;p++)
    for(int i=1;i<=l;i++)
    {
      int j=i+p;
      if(j>l) 
        break;
      for(int k=i;k<j;k++)
        for(int ci=0;ci<26;ci++)
          for(int cj=0;cj<26;cj++)                
            if((f[i][k]&num[ci])&&(f[k+1][j]&num[cj]))
              f[i][j]|=map[ci][cj];          
    }
  int key='S'-'A';
  for(int i=1;i<=l;i++) 
    g[i]=INF;
  g[0]=0;
  for(int i=1;i<=l;i++)
    for(int j=1;j<=i;j++)
      if((f[j][i]&num[key])&&g[j-1]!=INF)
        Min(g[i],g[j-1]+1);
  g[l]==INF ? printf("NIE\n") : printf("%d\n",g[l]);
} 

int main()
{
  freopen("GEN.in","r",stdin);
  freopen("GEN.out","w",stdout);
  scanf("%d\n",&n);
  num[0]=1;
  for(int i=1;i<=26;i++) 
    num[i]=num[i-1]<<1;
  for(int a,b,c,i=1;i<=n;i++)
  {
    a=getchar()-'A';
    b=getchar()-'A';
    c=getchar()-'A';
    map[b][c]|=num[a];
    getchar();
  }
  int T;
  scanf("%d\n",&T);
  while(T--) 
    work();  
}
