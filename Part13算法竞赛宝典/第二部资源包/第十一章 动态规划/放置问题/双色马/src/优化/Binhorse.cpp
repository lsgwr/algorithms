#include<iostream>
#include<fstream>
using namespace std;
    ifstream fin("Binhorse.in");
    ofstream fout("Binhorse.out");
const int MAX=500;
int horse,home;
int v[MAX+1][MAX+1],f[MAX+1][MAX+1];
short a[MAX+1];
int in_and_prepare()
{
    int color[2],kind=0;;
    a[0]=-1;
    fin>>horse>>home;
    for(int i=1;i<=horse;i++)
    {
        fin>>a[i];
        if(a[i]!=a[i-1])
            kind++;
    }
    if(kind<=home)
        return 0;
    for(int i=1;i<horse;i++)
    {
        color[a[i]]=1;
        color[!a[i]]=0;
        for(int j=i+1;j<=horse;j++)
        {
            color[a[j]]++;
            v[i][j]=color[0]*color[1];
        }
        f[i][1]=v[1][i];
    }
    f[horse][1]=v[1][horse];
    return 1;
}
void DP()
{
    int x;
    for(int i=2;i<=home;i++)
        for(int j=i;j<=horse;j++)
        {

            x=INT_MAX;
            for(int k=j-1;k>=i-1;k--)
                if(x>f[k][i-1]+v[k+1][j])   
                    x=f[k][i-1]+v[k+1][j];
            f[j][i]=x;
        }
}
int main()
{
    if(in_and_prepare())
    {
        DP();
        fout<<f[horse][home]<<endl;
        
    }
    else
        fout<<0<<endl;
    //fout<<clock()<<endl;
    fin.close();
    fout.close();
    return 0;
}
