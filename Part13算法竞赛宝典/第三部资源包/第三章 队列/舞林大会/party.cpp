//ŒË¡÷¥Ûª· 
#include <bits/stdc++.h>
using namespace std;
const int Max=1005;
int a[Max],b[Max];

int main()
{
  freopen("party.in","r",stdin);
  freopen("party.out","w",stdout);
  int n,m,k;
  cin>>m>>n>>k;
  int front1=0,real1=m-1,front2=0,real2=n-1;
  for(int i=0; i<m; ++i)
    a[i]=i+1;
  for(int i=0; i<n; ++i)
    b[i]=i+1;
  for(int i=1; i<=k; ++i)
  {
    a[++real1%Max]=a[front1%Max];
    b[++real2%Max]=b[front2%Max];
    front1++;
    front2++;
  }
  cout<<a[real1%Max]<<" "<<b[real2%Max]<<endl;
  return 0;
}


