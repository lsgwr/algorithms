//Ｋ个最大连续子序列和 
#include<iostream>
using namespace std;
int a[1001];
int f[11][1001];//注意此处的行列定义是相反的 

int main()
{
  freopen("ksum.in","r",stdin);
  freopen("ksum.out","w",stdout);  
  int i,j,m,k,n,temp,Max;
  cin>>n>>k;
  for(i=1;i<=n;++i) 
    cin>>a[i];  
      
  for(i=1;i<=k;++i)
    for(j=1;j<=n;++j)
    {
      f[i][j]=f[i][j-1]+a[j];//接在j-1后面
      for(m=i-1;m<=j-1;++m)//另起一段
      {
        temp=f[i-1][m]+a[j]; 
        if(temp> f[i][j])
          f[i][j]=temp;
      }
    }
  Max=f[k][k];
  for(i=k;i<=n;++i)//找出最大值
    if(f[k][i]>Max)
      Max=f[k][i];
  cout<<Max<<endl;
  return 0;
}
