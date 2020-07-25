//sqrtŒ Ã‚À„∑®2
#include<iostream>
#include<math.h>
using namespace std;

int i,j;
long long n,x,y,all,num,tmp;
int prime[70005];

void get_prime()
{
    prime[1]=2,prime[2]=3;all=2;
    for(i=5;i<=1<<16;++i)
    {
        for(j=1;j<=all;++j)
            if(i%prime[j]==0)
                break;
        if(j==all+1)
            ++all,prime[all]=i;
    }
}

int main()
{
    cin>>n;
    get_prime();
    for(x=1,y=1,i=1,tmp=n;i<=all && tmp!=1;++i)
    {
        num=0;
        while(tmp%prime[i]==0)
            ++num,tmp=tmp/prime[i];
        if(num&1)y=y*prime[i];
        num=(num+1)/2;
        for(j=1;j<=num;++j)
            x=x*prime[i];
    }
    if(tmp!=1)x*=tmp;
    x=x*2+y+n;
    cout<<x<<"\n";
    getchar();
    getchar();
    return 0;
}
