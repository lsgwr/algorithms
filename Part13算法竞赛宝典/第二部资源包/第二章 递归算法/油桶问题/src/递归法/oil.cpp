//ÓÍÍ°ÎÊÌâ£­µİ¹éËã·¨ 
#include <iostream>
#include <cstdlib>
using namespace std;

int a[100+1];
int m;
int n,i;

int p(int n,int m)
{
  if(n==0)
    return 0;
  else
    if(a[n]==m)
      return 1;
    else
    {
      if(p(n-1,m-a[n])==1)
        return 1;
      if(p(n-1,m)==1)
        return 1;  
    }
  return 0;  
}

int main()
{
  freopen("oil.in","r",stdin);
  freopen("oil.out","w",stdout);
  scanf("%d%d",&n,&m);
  for(i=1;i<=n;i++)
    scanf("%d",&a[i]);
  if(p(n,m))
    printf("yes\n");
  else
    printf("no\n");
  return 0;
}
