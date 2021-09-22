//牛记数扩展问题
#include <iostream>
using namespace std;
const int M=64;
long long n,s,t;
long long f[M][M];

long long cal(int x)
{
  int len,b[M];
  for(len=0;x>0;x/=2)
   b[len++]=x%2;
  long long ans=0;
  for(int i=0,c=0;i<len &&  c<n;++i)
    if(b[len-i-1]==1)
    {
      ans+=f[len-i-1][n-c];
      c++;                 
    }     
  return ans;  
}

int main()
{
  cin>>n>>s>>t;
  for(int i=0;i<M;++i)
    f[i][0]=1,f[0][i]=1;
  for(int i=1;i<M;++i)
    for(int j=1;j<M;++j)
      f[i][j]=f[i-1][j]+f[i-1][j-1];
  cout<<cal(t)-cal(s-1)<<endl;      
  system("pause");
}
