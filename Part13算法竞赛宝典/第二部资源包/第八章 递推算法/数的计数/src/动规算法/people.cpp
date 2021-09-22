//数的计数－动规算法 
#include <stdio.h>
int ans[1001];
int i,j,n;

int main()
{
  freopen("people.in","r",stdin);
  freopen("people.out","w",stdout);     
  scanf("%d",&n);
  for(i=1;i<=n;i++)
  {
    ans[i]=1;
    for(j=1;j<=i/2;j++)
      ans[i]+=ans[j];
  }
  printf("%d\n",ans[n]);
  return 0;
}
