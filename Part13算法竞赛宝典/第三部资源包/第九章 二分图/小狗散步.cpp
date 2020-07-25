//Ð¡¹·É¢²½ 
#include<iostream>   
#include<cmath>   
using namespace std;    
#define maxn 110   
  
struct node    
{   
  int x,y;   
}X[maxn],Y[maxn];   
  
int map[maxn][maxn];   
int b[maxn],match[maxn];   
bool vis[maxn];   
int n,m;   
  
double dis(node x,node y)   
{   
  return sqrt(abs(x.x-y.x)*abs(x.x-y.x)+abs(x.y-y.y)*abs(x.y-y.y));   
}   
  
bool dfs(int i)   
{   
  int j,cnt;   
  for(j=1;j<=n-1;j++)   
  {   
    //j=map[i][cnt];   
    //cout<<i<<":"<<j<<endl;   
    if(!vis[j] && map[i][j])
    {   
      vis[j]=1;   
      if(match[j]==0 || dfs(match[j]) )    
      {   
        match[j]=i;   
        return true;   
      }   
    }   
  }   
  return false;   
}   
int main()   
{      
  int i,j,k;   
  while(scanf("%d%d",&n,&m)!=EOF)   
  {   
    for(i=1;i<=n;i++)   
      scanf("%d%d",&X[i].x,&X[i].y);   
    for(j=1;j<=m;j++)   
      scanf("%d%d",&Y[j].x,&Y[j].y);   
    memset(b,0,sizeof(b));   
    memset(map,0,sizeof(map));   
    for(i=1;i<=n-1;i++)   
    {   
      for(j=1;j<=m;j++)   
      {   
        //cout<<(dis(X[i],Y[j]) + dis(Y[j],X[i+1]))/2<<endl;   
        if( dis(X[i],X[i+1]) >(dis(X[i],Y[j]) + dis(Y[j],X[i+1]))/2 )   
        {   
          map[j][i]=1;   
          //cout<<i<<":"<<j<<endl;   
        }   
      }   
    }   
    int ans=0;   
    memset(match,0,sizeof(match));   
    for(i=1;i<=m;i++)   
    {   
      memset(vis,0,sizeof(vis));   
      if(dfs(i))    
      {   
        ans++;   
      }   
    }   
    printf("%d\n",ans+n);   
    for(i=1;i<=n;i++)   
    {   
      printf("%d %d",X[i].x,X[i].y);   
      if(i==n)
        printf("\n");   
      else  
      {   
        printf(" ");   
        if(match[i]!=0)   
          printf("%d %d ",Y[match[i]].x,Y[match[i]].y);   
      }   
    }   
  }  
  getchar();
  getchar(); 
  return 0;   
}  
