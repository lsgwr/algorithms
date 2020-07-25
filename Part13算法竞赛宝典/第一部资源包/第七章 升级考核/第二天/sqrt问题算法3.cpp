//sqrtÎÊÌâËã·¨3
#include<iostream>
#include<math.h>
using namespace std;

long long n,t,x;
int p;

int main()
{
    cin>>n;
    t=1;
    p=2;
    while(true)
    {
        if(n%(p*p)==0)
        {
            n=n/(p*p);
            t=t*p;
        }
        else ++p;
        if((p*p)>n)
            break;
    }
    x=(t+1)*(t+1)*n;
    cout<<x<<"\n";
    getchar();
    getchar();
    return 0;
}
