//³­½üÂ· 
#include <iostream>
#include <cstdio>
#include <math.h>
using namespace std;

int x,y,n,dx[1000],dy[1000],i,j,f[1000],Max;

void init (void)
{
  cin>>x>>y>>n;
  for (i=0;i<n;i++)
  {
    cin>>dx[i]>>dy[i];
    for (j=i;j>0;j--)
      if (dx[j]<dx[j-1])
      {
	    swap(dx[j],dx[j-1]);
		swap(dy[j],dy[j-1]);
      }
  }
}

int main ()
{
  freopen("shortline.in","r",stdin);
  freopen("shortline.out","w",stdout);
  init();
  for (i=0;i<n;i++)
  {
    f[i]=1;
    for (j=0;j<i;j++)
      if (dx[j]<dx[i] && dy[j]<dy[i] && f[i]<f[j]+1)
		f[i]=f[j]+1;
      if (f[i]>Max)
	    Max=f[i];
  }
  cout<<int(100*(double(x+y-2*Max)+Max*sqrt(2))+0.5)<<'\n';
  return 0;
}
