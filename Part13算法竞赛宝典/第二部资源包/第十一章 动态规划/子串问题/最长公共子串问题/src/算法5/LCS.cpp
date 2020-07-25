//最长公共子串问题 -算法5
#include <iostream>
#include <cstdlib>
#define M 500
using namespace std;

int n1,n2;
short A[M+1];
char s1[M+1],s2[M+1];

void dp()
{
  int i,j,k=0,t=0;
  for(i=1;i<=n1;++i)
    for(j=1,k=0;j<=n2;++j)
    {
      t=A[j];//在赋值之前先记录下来
      if(s1[i]==s2[j]) 
        A[j]=k+1;
      else A[j]>?=A[j-1];
        k=t;//把记录的传给k，放在下一次用
    }
  cout<<A[n2]<<endl;
}

void init()
{
  int i;
  cin>>n1;
  for(i=1;i<=n1;++i)
    cin>>s1[i];
  cin>>n2;
  for(i=1;i<=n2;++i)
    cin>>s2[i];
}

int main()
{
  freopen("LCS.in","r",stdin);
  freopen("LCS.out","w",stdout);     
  init();
  dp();
  return 0;
}
