//sqrtŒ Ã‚À„∑®1
#include<iostream>
#include<math.h>
using namespace std;

long long n,x,y;

int main()
{
    cin>>n;
    while(++y)
    {
        if(n*y==(int)(sqrt(n*y))*(int)(sqrt(n*y)))
        {
            x=n+y+2*(int)sqrt(n*y);
            break;
        }
    }
    cout<<x<<"\n";
    getchar();
    getchar();
    return 0;
}
