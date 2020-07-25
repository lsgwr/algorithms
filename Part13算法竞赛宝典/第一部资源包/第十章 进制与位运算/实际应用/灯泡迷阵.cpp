//灯泡迷阵
#include<iostream>
using namespace std;

int n,m,t,two;
bool a[1000000][2],in;  //滚动数组 

int main()
{
    cin>>n>>m;
    for(int i=0;i<n;++i)
        cin>>a[i][0];   //输入 
    while(t<m)
    {
        two=-1;
        while(t+(1<<two+1)<=m)
            two++;      //找到最大的t+2^k<=m 
        for(int i=0;i<n;++i)
            a[i][in^1]=a[i][in]^a[(i-(1<<two)%n+n)%n][in];   //状态转移 
        in^=1;
        t+=(1<<two);
    }
    for(int i=0;i<n;++i)
        cout<<a[i][in];
    cout<<"\n";    
    return 0;

}
