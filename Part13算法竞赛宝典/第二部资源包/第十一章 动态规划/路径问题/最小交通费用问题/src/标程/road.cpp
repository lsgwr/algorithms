//最小交通费用问题 
#include <iostream> 
#include <cstdio>
#include <cstdlib>
using namespace std;  
 
const int maxn=101; 
int map[maxn][maxn],N,M; 

void input(void) 
{ 
  scanf("%d %d",&N,&M); 
  int a,b,s,i,j; 
  for (i=1;i<=N;i++) 
    for (j=1;j<=N;j++) 
      map[i][j]=-1; 
  for (i=1;i<=M;i++)
  {
    scanf("%d %d %d",&a,&b,&s); 
    map[a][b]=s; 
  }
} 

void floyed(void) 
{
  int i,j,k; 
  for (k=1;k<=N;k++) 
    for (i=1;i<=N;i++) 
      for (j=1;j<=N;j++) 
		if (map[i][k]!=-1 && map[k][j]!=-1) 
		  if (map[i][k]+map[k][j]<map[i][j] || map[i][j]==-1) 
		    map[i][j]=map[i][k]+map[k][j];	
}

int main()
{
  freopen("road.in" ,"r",stdin ); 
  freopen("road.out","w",stdout); 
  int a,b,i,j; 
  input();
  scanf("%d %d",&a,&b);  
  floyed(); 
  printf("%d\n",map[a][b]+map[b][a]); 
  /* 调试用 
  for (i=1;i<=N;i++) 
  { 
    for (j=1;j<=N;j++) 
      printf("%d ",map[i][j]); 
    printf("\n");
  }
  */
  return 0;
}
