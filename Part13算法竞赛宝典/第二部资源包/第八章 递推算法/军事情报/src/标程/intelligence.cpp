//军事情报 
#include<iostream>
#include<cstdlib>
using namespace std;

int n,i;
long long s[1001];

int main()
{ 
  freopen("intelligence.in","r",stdin);
  freopen("intelligence.out","w",stdout);  
  s[0]=0; s[1]=0; s[2]=1;
  for (i=3;i<=1000;i++)
    s[i]=(i-1)*(s[i-1]+s[i-2]);     
  cin>>n; 
  cout<<s[n]<<endl;  
  return 0;
}
