//×îÓÅËã·¨
#include<iostream>
using namespace std;
int main()
{
   int M,K,ans=0,x;
   cin>>M>>K;
   for(int i=1;i<=M;i++)
   {
    if(ans+K<i)
     {
       x=(i+1-ans)/(K-1)-1;

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
     ans=(ans+K-1)%i+1;
   }
    cout<<ans<<endl;
return 0;
}
