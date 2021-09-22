//×îÓÅ³Ë³µ 
#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int a[500+1][500+1];
char v[500+1];
long s[500+1];
int t[500+1];
int p,x,y,i,j,Max,k,m,n,l;

int main()
{
  freopen("input.txt","r",stdin);
  freopen("output.txt","w",stdout);
  scanf("%d%d",&m,&n);
  memset(a,0,sizeof(a));
  for(l=1;l<=m;l++)
  {
    j=0;
    memset(t,0,sizeof(t));
    while(!feof(stdin))
    {
      j++;
      scanf("%d",&t[j]);
    }
    for(i=1;i<=j-1;i++)
      for(k=i+1;k<=j;k++)
        a[t[i]][t[k]]=1;
  }
  memset(v,0,sizeof(v));
  v[1]=1;
  for(j=2;j<=n;j++)
  {
    Max=99999999;
    for(i=1;i<=n;i++)
      if(!v[i]&&(a[1][i]!=0)&&(a[1][i]<Max))
      {
        Max=a[1][i];
        p=i;
      }
    v[p]=1;
    for(i=1;i<=n;i++)
      if((a[1][p]!=0)&&(a[p][i]!=0))
        if((a[1][i]>a[1][p]+a[p][i])||(a[1][i]==0))
          a[1][i]=a[1][p]+a[p][i];
  }
  if(a[1][n]-1==-1)
    printf("NO\n");
  else
    printf("%d\n",a[1][n]-1);
  return 0;
}
