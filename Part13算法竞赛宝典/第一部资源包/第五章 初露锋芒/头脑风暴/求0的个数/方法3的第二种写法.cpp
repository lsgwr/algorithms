#include <iostream>
#include <cstdlib>
using namespace std;
int main()
{
    
    freopen("factorial.in","r",stdin);
    freopen("factorial.out","w",stdout);
    long long n,sum=0;
    cin>>n;
    for(;n>=5;)
    {
       n/=5;
       sum+=n;
    }
    cout<<sum<<endl;
    return 0;
}
