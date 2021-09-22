//星星 
#include <bits/stdc++.h>
using namespace std;

int c[32000+10];
int a[15000+10];

int lowbit(int x)
{
  return x&(-x);
}

void updata(int x,int value)
{
  for(int i=x; i<=32001; i+=lowbit(i))
    c[i]+=value;
}

int getsum(int x)
{
  int sum=0;
  for(int i=x; i>0; i-=lowbit(i))
    sum +=c[i];
  return sum;
}

int main()
{
  freopen("stars.in","r",stdin);
  freopen("stars.out","w",stdout);	
  int n,x,y;
  scanf("%d",&n);
  for(int i=0; i<n; i++)
  {
    scanf("%d%d",&x,&y);//下标可能从0开始,所以要x+1,因为lowbit(0)=0会死循环
    a[getsum(x+1)]++;   //求出横坐标小于x的所有stars个数，并记录到a中
    updata(x+1,1);      //更新区间
  }
  for(int i=0; i<n; i++)
    printf("%d\n",a[i]);
  return 0;
}

