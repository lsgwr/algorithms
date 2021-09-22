//×îÓÅËã·¨
#include<iostream>
using namespace std;
int main()
{
   int M,K,ans=0,x;
   freopen("monkey.in","r",stdin);
   freopen("monkey.out","w",stdout);
   cin>>M>>K;
   for(int i=2;i<=M;i++)
   {
    if(ans+K<i)
     {
       x=(i-ans)/K;

       if(i+x<M)
        {
          ans=ans+K*x;
          i=i+x;
         }
       else
        {
            ans=ans+K*(M-i);
            i=M;
        }
     }
     ans=(ans+K)%i;
   }
   cout<<ans+1<<endl;
   return 0;
}
