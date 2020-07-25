//¾ù·ÖÖ½ÅÆ 
#include <stdio.h>
#include <stdlib.h>
int a[101]={0};
int main()
{
  freopen("card.in","r",stdin);
  freopen("card.out","w",stdout);  
  long n,i,sum=0,d;
  scanf("%ld",&n);
  for(i=1;i<=n;i++)
  {
    scanf("%ld",&a[i]);
    sum+=a[i];
  }
  d=sum/n;
    
  long count=0;
  for(i=1;i<=n-1;i++)
    if(a[i]!=d)
    {
      a[i+1]-=d-a[i];
      count++;
    }
  printf("%ld\n",count);
  return 0;
}    
