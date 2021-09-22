//求素数的循环小数
#include<iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int a[100]={0};
int b[10000]={0};

int judge(int n)
{    
    int i;
    if(n<=1)    return 0;
    for(i=0; a[i]>0; i++)
        if(n%a[i]==0) 
          return 0;
    
    a[i]=n;
    return 1;
}

int s(int num)
{
    int k,i=0,c;
    b[i++]=10/num;
    k=c=10%num;
    do
    {
        b[i]=(c*10)/num;
        c=(c*10)%num;
        i++;
    }while(k!=c);

    return (i-1);
    
}

int main()
{
    a[0]=2;
    for(int i=1; i<=500; i++)
        if(judge(i))
        {
            int n=s(i);
            cout<<i<<"=0.";
            for(int k=0; k<n; k++)
                cout<<b[k];
            cout<<endl;
        }
    system("pause");
    return 0;
}
