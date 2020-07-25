#include <iostream>
#include <cstdlib>
#include <cstdio>
using namespace std;

double a[61];
int p[61],q[61],r[61];


int main()
{
    freopen("realsn7.in","r",stdin);
	freopen("realsn7.out","w",stdout);
	double d,a1,an;
	int n,i,m;
	cin>>n>>d>>m>>a1>>an;
	p[1]=q[1]=q[2]=r[2]=0;
	p[2]=r[1]=1;
	for (i=3;i<=n;i++)
	{
		p[i]=p[i-2]-2*p[i-1];
		q[i]=q[i-2]-2*q[i-1]+2;
		r[i]=r[i-2]-2*r[i-1];
	}
	for (i=1;i<=n;i++)
	 //printf("%d %d %d\n",p[i],q[i],r[i]);
	a[1]=a1;a[n]=an;
	for (i=2;i<n;i++)
	{
		a[i]=(a[n]-q[n-i+2]*d-r[n-i+2]*a[i-1])/p[n-i+2];
		//printf("%lf\n",a[i]);
	}
    cout<<int(a[m]);
	//system("pause");
	return 0;
}
