//ÇóÁùÎ»Êı
#include<iostream>
using namespace std;

int main()
{
    int a[6]={7},b[6]={1};
    for(int i=100007; i<=199997; i+=10)
    {
        int n=3*i,j,k=i;
        k/=10;  n/=10;
        for(j=1; j<6; j++)
        {
            a[j]=k%10;
            k/=10;
            
            b[j]=n%10;
            n/=10;
            
            if(b[j]!=a[j-1])    break;
        }
        
        if(j>5)
        {
            cout<<i<<endl<<clock()<<"ms";
            break;
        }
    }
    return 0;
}
