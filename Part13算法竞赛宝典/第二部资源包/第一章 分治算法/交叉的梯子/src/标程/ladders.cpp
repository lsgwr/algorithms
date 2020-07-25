//½»²æµÄÌÝ×Ó
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
using namespace std;

double x,y,c;

bool judge(double t)
{
  double a,b;
  a=sqrt(x*x-t*t);
  b=sqrt(y*y-t*t);
  if(a*c+b*c-a*b>0)
    return true;
  else
    return false;
}

int main()
{
  freopen("ladders.in","r",stdin);
  freopen("ladders.out","w",stdout);
  while(~scanf("%lf%lf%lf",&x,&y,&c))
  {
	int t;
	double low,high,mid;
	low=0;
	high=(x>y?y:x);
	while(low<=high)
	{
	  mid=(low+high)/2;
	  if(judge(mid))
	    high=mid-0.0001;
	  else
	    low=mid+0.0001;
	}
	printf("%.3lf\n",low);
  }
  return 0;
}
