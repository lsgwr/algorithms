#include<iostream>
#include<fstream>
#include<iomanip>
using namespace std;
ifstream fin("encounter.in");
ofstream fout("encounter.out");
int n,k,p[101][101];
double ans;
bool judge;

void init()
{
    fin>>n;
    k=(n+1)/2;
    if(k%2==1) {judge=1;return;}
    for(int i=0;i<=n+1;++i)
        for(int j=0;j<=n+1;++j)
        {
            if((i==1&&j==1) || (i==1&&j==n) || (i==n&&j==1) || (i==n&&j==n))    
                p[i][j]=2;
            else if(i==1 || i==n || j==1 ||j==n)
                p[i][j]=3;
            else p[i][j]=4;
        }   
    return;    
}

void work(int x,int y,int t,double lv)
{
    if(x<1 || x>n || y<1 || y>n || t>n) return;
    if(x-(abs(y-k))<t) return; 
    if(t==x && y==k)    
    {
        ans+=lv;    
        return;
    }
    work(x+1,y,t+1,lv/p[x][y]);
    work(x-1,y,t+1,lv/p[x][y]);
    work(x,y+1,t+1,lv/p[x][y]);
    work(x,y-1,t+1,lv/p[x][y]);
    return;
}

int main()
{
    init();
    if(!judge) work(k,k,0,1);
    fout<<setprecision(4)<<ans<<endl;
    return 0;    
}
