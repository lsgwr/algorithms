//sqrtŒ Ã‚À„∑®4
#include<iostream>
#include<math.h>
using namespace std;

long long n,q,x;
int p;

int main()
{
    cin>>n;
    for(p=(int)sqrt(n);p>=1;--p)
    {
        if(n%(p*p)==0)
        {
            q=n/(p*p);
            break;
        }
    }
    x=(p+1)*(p+1)*q;
    cout<<x<<"\n";
    getchar();
    getchar();
    return 0;
}
