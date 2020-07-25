// Joseph's Problem
#include<iostream>
using namespace std;
int main()
{
   freopen("Joseph's4.in","r",stdin);
   freopen("Joseph's4.out","w",stdout);
   int n,k;
   long long s=0;
   cin>>n>>k;
   for(int i=1;i<=n;i++)
     s=s+k%i;
   cout<<s<<endl;
   return 0;
}
