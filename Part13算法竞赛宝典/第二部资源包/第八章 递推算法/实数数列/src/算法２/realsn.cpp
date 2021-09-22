//实数数列算法２ 
#include <iostream>
using namespace std;

typedef double Type;//根据具体数据范围，可能需要用到高精度，此处只展示算法，暂用int 

struct A
{
  Type p,q;//a[i]=p+q*a[2]
}a[70];

int n,m;
Type d,a1,an;

int main()
{
  freopen("realsn.in","r",stdin);
  freopen("realsn.out","w",stdout);
  cin>>n>>d>>m>>a1>>an;
  //初始化a[1],a[2] 
  a[1].p=a1;
  a[2].q=1;
  for(int i=3;i<=n;i++)//根据递推式计算每一个a[i] 
  {
    a[i].p = a[i-2].p - 2*(a[i-1].p - d);
    a[i].q = a[i-2].q - 2*(a[i-1].q - 0);
  }
  if(n==1)
    cout<<a1<<endl;
  else  //a[m]=p+q*a2 , 其中a2=(an-a[n].p)/a[n].q
    cout<<int(a[m].p + a[m].q*(an-a[n].p)/a[n].q)<<endl;
  return 0;
}

