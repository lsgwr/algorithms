//书架问题２ 
#include<iostream>
#include<cmath>
#include <cstdlib>
using namespace std;

struct book
{
  long height,wide;
} a[200];
long f[200][200];

int cmp(const void *a,const void *b)
{
  book *t1=(book *)a;
  book *t2=(book *)b;
  if (t1->height>t2->height) 
    return 1;
  if (t1->height<t2->height) 
    return -1;
  return 0;
}

int main()
{
  freopen("book2.in","r",stdin);
  freopen("book2.out","w",stdout);  
  long n,k;
  cin>>n>>k;
  for (long i=1;i<=n;++i) 
    cin>>a[i].height>>a[i].wide;
  a[0].height=-1;
  qsort(a,n+1,sizeof(a[0]),cmp);//按高度排好序 
  for(long i=1;i<=n;++i)
    for(long j=2;j<=n-k && j<=i;++j)
    {
      f[i][j]=INT_MAX;
      for(long x=j-1;x<=i-1;++x)
        if(f[i][j]>f[x][j-1]+abs(a[x].wide-a[i].wide))
          f[i][j]=f[x][j-1]+abs(a[x].wide-a[i].wide);
    }
  long ans=f[n][n-k];
  for(long i=n-1;i>=n-k;--i)
    if (f[i][n-k]<ans) 
      ans=f[i][n-k];
  cout<<ans;
  return 0;
}
