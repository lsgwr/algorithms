#include<iostream>
#include<fstream>
using namespace std;
ifstream fin("knight.in");
ofstream fout("knight.out");
int x,y,a[1000],key;
int b[5]={0,1,2,2,1};
int c[5]={0,-2,-1,1,2};

int main()
{
    fin>>x>>y;
    int m=1,n=1,k=0,i=1,t=0,j;
    while(1)
    {
        if(m==x&&n==y) {key=1;break;}
        j=i;
        while(a[j]==4)
            --j;
        if(j==0) break;
        ++k;
        if(k>4)
        {
            --i;--t;
            k=a[i];
            m-=b[k];n-=c[k];
            a[i]=0;
        }
        else
            if(m+b[k]<=x && n+c[k]<=y && m+b[k]>=1 && n+c[k]>=1 && (y-n)<=2*(x-m))
            {
                m+=b[k];n+=c[k];
                a[i]=k;
                ++i;++t;k=0;
            }  
    }
    if(key)
    {
        for(i=1;i<t;++i)
            fout<<a[i]<<' ';
        fout<<a[t];
    }
    else fout<<-1;
    fout<<endl;
    fin.close();
    fout.close();
    return 0;    
}
