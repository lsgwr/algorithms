//书架 一维线性dp 
#include<iostream>
#include<cstdlib>
using namespace std;
#define MAX 9999999
int w[10000],l[10000],f[10000];//W为宽度，L为高度
int sw;

int opt(int x,int y)
{
    int i,k = 0,tot = 0;
    for(i = x;i <= y;i++)
    {
      k += w[i];
      if(l[i] > tot)
        tot = l[i];
    }
    if(k > sw)//超出宽度限定 
      return MAX;
    else return tot;
}

int main()
{
    freopen("book.in","r",stdin);
    freopen("book.out","w",stdout);
    int i,j,n,t;
    cin>>n>>sw;//Sw 作为宽度限定条件 
    for(i = 1;i <= n;i++)
      cin>>w[i]>>l[i];
    for(i = 1;i <= n;i++)
    {
      f[i] = MAX;
      for(j = 0;j < i;j++)
      {
       t = opt(j+1,i);
       if (f[j] + t < f[i])
          f[i] = f[j] + t;
      }
    }
    cout<<f[n]<<'\n';
    return 0;
}
