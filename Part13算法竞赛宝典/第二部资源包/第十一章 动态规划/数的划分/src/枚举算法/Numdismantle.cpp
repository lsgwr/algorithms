//数的划分-枚举算法 
#include<iostream>
using namespace std;

int main()
{
  freopen("Numdismantle.in","r",stdin);
  freopen("Numdismantle.out","w",stdout);
  int n,k,ans=0;
  cin>>n>>k;
  if (k==2)
    for (int a=1;a<=n;++a)
	  for (int b=a;b<=n;++b)
		 if (a+b==n) 
           ans++;

  if (k==3)
    for (int a=1;a<=n;++a)
      for (int b=a;b<=n;++b)
		 for (int c=b;c<=n;++c)
			if (a+b+c==n) 
              ans++;

  if (k==4)
    for (int a=1;a<=n;++a)
      for (int b=a;b<=n;++b)
		for (int c=b;c<=n;++c)
		  for (int d=c;d<=n;++d)
		    if (a+b+c+d==n) 
              ans++;
  
  if (k==5)
    for (int a=1;a<=n;++a)
      for (int b=a;b<=n;++b)
		for (int c=b;c<=n;++c)
		  for (int d=c;d<=n;++d)
		    for (int e=d;e<=n;++e)
		      if (a+b+c+d+e==n)
                ans++;
  
  if (k==6)
    for (int a=1;a<=n;++a)
      for (int b=a;b<=n;++b)
		for (int c=b;c<=n;++c)
		  for (int d=c;d<=n;++d)
		    for (int e=d;e<=n;++e)
		      for (int f=e;f<=n;++f)
				if (a+b+c+d+e+f==n) 
                  ans++;
  cout<<ans<<'\n';
  return 0;
}
