//Ä§·¨Ê¯¿ó 
#include <cstdio>
#include <cstdlib>
#include <iostream>
using namespace std;

int a[1001][3]={0},b[1001]={0};

int g[1001][1001];

inline bool isNumber(char c)
{
    return (c>='0' && c<='9');
}

void init()
{
  int i,n;
  char c; 
  cin>>n;
  for(i=1;i<=n;++i)
      cin>>b[i];
  for(i=1;i<=n;i++)
  {
    int x;
    cin>>x;
    do
    {
      int num=0;
      while(isNumber(c=getchar()))
        num=num*10+c-'0';
      g[x][num]=1;
      g[num][x]=1;
    }while(c!='\n');
  }
  for(i=1;i<=n;i++)
  {
     a[i][0]=b[i];
     a[i][1]=i;
   }  
}

void Max()
{
  int i,j,max,l;
  for(i=10;i>=1;i--)
  {
      max=0;
      l=i;
      for(j=i+1;j<=10;j++)
        if(g[i][j]&&a[j][0]>max)
        {
            max=a[j][0];
            l=j;                      
         }
      a[i][0]+=max;
      a[i][1]=l;                
  }
  max=0;
  l=1;
  for(i=1;i<=10;i++)
   if(a[i][0]>max)
     {
       max=a[i][0];
       l=i;           
     } 
  cout<<max<<endl;
}

int main()
{
  freopen("Mine.in","r",stdin);
  freopen("Mine.out","w",stdout);
  init();
  Max();
  return 0;  
}
