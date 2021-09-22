//魔方阵
#include<iostream>
#include<fstream>
using namespace std;
int a[10]={0,0,0,0,0,5,0,0,0,0},j,num=0;

int fun(int m)
{
    int i;
    if(m==5) 
    {
        for(j=1;j<=3;++j)
            if(a[j]+a[j+3]+a[j+6]!=15 || a[3*j]+a[3*j-1]+a[3*j-2]!=15)
                 return 0;
        cout<<a[1]<<a[2]<<a[3]<<endl;//输出结果 
        cout<<a[4]<<a[5]<<a[6]<<endl;
        cout<<a[7]<<a[8]<<a[9]<<endl<<endl;        
		++num;
    }
    else for(i=1;i<=9;++i)
    {
        if(a[i]==0)
        {
            a[i]=m;
            a[10-i]=10-m;
            fun(m+1);
            a[i]=0;
            a[10-i]=0;
        }  
    }
}

int main()
{
    int m=1;
    fun(m);
    cout<<num<<endl;
    system("pause");
    return 0;
}
