//太空梯 
#include <iostream>
#include <algorithm>
using namespace std;
#define MAXV 410
#define MAXM 40010

typedef struct 
{
  int h,a,c;
}Blocks;

Blocks v[MAXV];

int cmp(Blocks x,Blocks y)
{
  return x.a<y.a;
}
int f[MAXM],user[MAXM];

int main()
{
  freopen("Elevator.in","r",stdin);
  freopen("Elevator.out","w",stdout);    
  int i,t,j,Max;
  scanf("%d",&t);
  for(i=1;i<=t;i++)
    scanf("%d%d%d",&v[i].h,&v[i].a,&v[i].c);
  sort(v+1,v+t+1,cmp);
  memset(f,0,sizeof(f));
  f[0]=1;
  Max=0;	//赋值为0，不能为-1
  for(i=1;i<=t;i++)
  {
    memset(user,0,sizeof(user));
    for (j=v[i].h;j<=v[i].a;j++)
      if(!f[j]&&f[j-v[i].h]&&user[j-v[i].h]+1<=v[i].c)
      {
		f[j]=1;
		user[j]=user[j-v[i].h]+1;
		if(j>Max) 
          Max=j;
      }
  }
  printf("%d\n",Max);
  return 0;
}
