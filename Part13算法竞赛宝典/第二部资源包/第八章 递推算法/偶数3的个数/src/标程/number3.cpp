//偶数3的个数 
#include <stdio.h>
int main()
{
  freopen("number3.in","r",stdin);
  freopen("number3.out","w",stdout);  
  int n,i;
  scanf("%d",&n);
  //因为最后只会用到最近两个数据，所以采用滚动数组
  int a[2]={0,9},b[2]={0,1};
  for(i=2;i<=n;i++)
  {
    a[i%2]=b[(i-1)%2]+9*a[(i-1)%2];
    b[i%2]=a[(i-1)%2]+9*b[(i-1)%2];
  }
  printf("%d\n",a[n%2]-a[(n-1)%2]);
  return 0;
}
