//合并魔法石２ 
#include <cstring>
#include <iostream>
#include <cstdlib>
using namespace std;
const int INF = 10000000;
int f1[101][101],f2[101][101];
int v[201], s[201];
int n;

inline int d(int i,int j) 
{ 
  return s[j]-s[i-1]; 
}

int main()
{
  freopen("merge2.in","r",stdin);
  freopen("merge2.out","w",stdout);  	
  memset(f1,0,sizeof(f1));	
  memset(f2,0,sizeof(f2));
  memset(s, 0,sizeof(s));
  cin>>n;
  for (int i=1;i<=n;i++)
  {
    cin>>v[i];
    v[n+i]=v[i];			// 把环拉成链 
  }
  for(int i=1;i<=n+n;i++) 
    s[i]=s[i-1]+v[i];		// 前序和 

  for (int p=1;p<n;p++)
    for (int i=1,j=i+p;(i<n+n)&&j<=(n+n);i++,j=i+p)
    {
	  f1[i][j]=0; 
      f2[i][j]=INF;
      for (int k=i;k<j;k++)
      {
		f1[i][j]=max(f1[i][j],f1[i][k]+f1[k+1][j]+d(i,j));
		f2[i][j]=min(f2[i][j],f2[i][k]+f2[k+1][j]+d(i,j));
      }
     // cout<<"f2["<<i<<"]["<<j<<"]="<<f2[i][j]<<endl;
    }
	
  int r1=0, r2=INF;
  for(int i=1;i<=n;i++)
  {
	if(f1[i][i+n-1]>r1) 
      r1=f1[i][i+n-1];
	if (f2[i][i+n-1]<r2) 
      r2=f2[i][i+n-1];
  }
  cout<<r1<<" "<<r2<<endl;
  return 0;
}
