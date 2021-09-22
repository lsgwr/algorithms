#include <iostream>
#include <cmath>
using namespace std;
int main()
{
 long long N,K;
 while(cin>>N>>K)
  {
   long long S,T,i,s,e,ans=0;  
   S=sqrt(( double)K);
   T=K/S;
   //该段为朴素算法
   for(i=1,ans=0;i<=N && i<=T;i++) 
     ans+=K%i; 
   if(N>K)
    ans+=(N-K)*K;
   //该段为优化算法 
   for(i=S;i>1;i--)
    {
     s=K/i;
     e=K/(i-1);
      if(N<s)
       break;
      if(N<e)
       e=N;
      ans=ans+(e-s)*(K%e+K%(s+1))/2;
     }
     cout<<ans<<endl;
  }
  return 0;
}
