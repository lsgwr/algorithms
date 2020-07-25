//简单数组操作
#include <bits/stdc++.h>
using namespace std;
#define N 100010
#define ll long long

ll D[N];                                   //树状数组D,保存增量
ll Dxi[N];                                 //树状数组，D[i]*i的前缀和
ll A[N];                                   //存放的前缀和
ll n,m;

ll lowbit(ll x)
{
  return x&(-x);
}

void Modify(ll x,ll val,ll *c)
{
  for(int i=x; i<=n; i+=lowbit(i))          //n为数组长度
    c[i]+=val;
}

ll Getsum(ll x,ll *c)
{
  ll sum=0;
  for(int i=x; i>0; i-=lowbit(i))
    sum +=c[i];
  return sum;
}

int main()
{
  scanf("%lld%lld",&n,&m);
  for(int i=1; i<=n; i++)
  {
    scanf("%lld",&A[i]);
    A[i]+=A[i-1];                          //直接处理为前缀和数组
  }
  getchar();                               //消除换行符
  string option;
  for(int i=1; i<=m; i++)
  {
    cin>>option;
    ll a,b,val;
    if(option=="C")
    {
      scanf("%lld%lld%lld",&a,&b,&val);
      Modify(a,val,D);                     //D前缀和更新
      Modify(b+1,-val,D);
      Modify(a,a*val,Dxi);                 //D*i的前缀和更新
      Modify(b+1,-(b+1)*val,Dxi);
    }
    if(option=="Q")
    {
      scanf("%lld%lld",&a,&b);
      ll Ans=A[b]-A[a-1];
      Ans+=Getsum(b,D)*(b+1)-Getsum(b,Dxi);
      Ans-=Getsum(a-1,D)*(a)-Getsum(a-1,Dxi);
      printf("%lld\n",Ans);
    }
  }
  return 0;
}
