//杨辉三角形优化算法
#include <iostream>
#include <iomanip>
using namespace std;
int a[100][2];
int j;
void  fun(int i)//用n-1行求n行 
{
    if(i==1 ||i==j)
    { a[i][1]=1;
        if(i==j)
        return;
        else fun(i+1);
    }
    else
        {a[i][1]=a[i-1][0]+a[i][0];fun(i+1);}
}
void fun2(int i)//把n行变为基础行 
{
    if(a[i][1]==0)
        return ;
    else
        {a[i][0]=a[i][1];fun2(i+1);}
}
int main()
{
    int n,k;
    cin>>n;
    for(j=1;j<=n;j++)
        {for(k=1;k<=n-j;k++)
            cout<<"   ";
        fun(1);
        fun2(1);
        for(k=1;a[k][1]!=0;k++)
            cout<<left<<setw(5)<<a[k][1]<<' ';
        cout<<endl;}
    system("pause");
}
