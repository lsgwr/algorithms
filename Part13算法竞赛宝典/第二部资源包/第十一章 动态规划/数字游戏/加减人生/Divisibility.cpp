//加减人生 
#include <stdio.h>
#include <string.h>
#define clr(a) memset(a,0,sizeof(a))
#define N 10001
#define M 101

int a[N];
int b[N][M],p,q;
int opt[N][M];
int n,m;

int abs(int x)
{
  return x>0?x:-x;
}

int main()
{
  freopen("Divisibility.in","r",stdin);
  freopen("Divisibility.out","w",stdout);  
  int i,j,k,T,flag;
  scanf("%d",&T);
  while(T--)
  {
    scanf("%d%d",&n,&m);
    for(i=1;i<=n;i++) 
      scanf("%d",&a[i]);
    clr(b); clr(opt);//初始化 
    p=1;
    b[0][0]=0;
    opt[0][0]=1;
    
    for(i=1;i<=n;i++)//work
    {
      q=0;
      for(j=0;j<p;j++)
      {
        k=abs(b[i-1][j]+a[i])%m;
        if(!opt[i][k])
        {
          opt[i][k]=1;
          b[i][q++]=k;
        }
        k=abs(b[i-1][j]-a[i])%m;
        if(!opt[i][k])
        {
          opt[i][k]=1;
          b[i][q++]=k;
        }
      }
      p=q;
    }
    if(opt[n][0])//output 
      puts("Divisible");
    else 
      puts("Not divisible");
    if(T) 
      puts("");
  }
  return 0;
}
