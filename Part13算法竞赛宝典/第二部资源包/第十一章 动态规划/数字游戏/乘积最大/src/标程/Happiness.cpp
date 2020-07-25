//×î´ó³Ë»ý
# include <string.h>
# include <stdio.h>
#include <stdlib.h>
char str[41];
long g(int start,int end)
{
  int i;
  long int s=0,t=1;
  for(i=end;i>=start;i--)
    {
       s+=(str[i]-'0')*t;
       t*=10;
    }
  return(s);
}

int main()
{
  int i,j,h,k=0,n;
  long int max,f[41][41]={0};
  freopen("Happiness.in","r",stdin);
  freopen("Happiness.out","w",stdout);
  scanf("%d %d",&n,&k);
  scanf("%s",str);
  for(i=0;i<n;i++)
    f[0][i]=g(0,i);

  for(i=1;i<=k;i++)
    for(j=i;j<n;j++)
     {
	   max=f[i-1][j-1]*g(j,j);
	   for(h=j-1;h>=i;h--)
	   {
	     if(f[i-1][h-1]*g(h,j)>max)
	       max=f[i-1][h-1]*g(h,j);
	   }
	   f[i][j]=max;
     }
  printf("%ld\n",f[k][n-1]);
  return 0;
}
