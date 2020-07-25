#include<iostream>
#include<fstream>
using namespace std;
char went[41][41];
int n,qq;
int go(int x1,int y1,int i)
{
    if(i==qq+1)
        return 1;
    else if(went[x1][y1] || x1<1||x1>n || y1>n || y1 <1)
        return 0;
    else
        {
            went[x1][y1]=i;
            if(go(x1+1,y1-2,i+1))
                return 1;
            if(go(x1+2,y1-1,i+1))
                return 1;
            if(go(x1+2,y1+1,i+1))
                return 1;
            if(go(x1+1,y1+2,i+1))
                return 1;
            if(go(x1-1,y1+2,i+1))
                return 1;
            if(go(x1-2,y1+1,i+1))
                return 1;
            if(go(x1-2,y1-1,i+1))
                return 1;
            if(go(x1-1,y1-2,i+1))
                return 1;
            went[x1][y1]=0;
            return 0;
        }
}
int main()
{
    ifstream fin("knight2.in");
    ofstream fout("knight2.out");
    int x,y;
    fin>>n>>x>>y;
    x++;
    y++;
    qq=n*n;
    int i,j;
    if(go(x,y,1))
            for(i=1;i<=n;i++)
                {
                    for(j=1;j<n;j++)
                        fout<<(int)went[j][n-i+1]<<' ';
                    fout<<(int)went[n][n-i+1]<<endl;
                }
    else
        fout<<-1<<endl;
    fin.close();
    fout.close();
    return 0;
}
