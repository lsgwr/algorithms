#include <iostream>
#include <fstream>
using namespace std;
int top=0;
int a[1001];
bool b[101][101];
int n,m;
bool first=0;
ifstream    fin("knight.in");
ofstream    fout("knight.out");
int add(int num)
{
    top++;
    a[top]=num;
}
int del()
{
    a[top]=0;
    top--;
}
int s(int x,int y)
{
    int i;
    if(first==1|| b[x][y]==1 || (m-y)>((n-x)+(n-x)))
        return 0;
    if(x==n && y==m)
    {
        for(i=1;i<top;i++)
            fout<<a[i]<<' ';
        fout<<a[i]<<endl;
        first=1;
    }
    else
    {
        if(x+1<=n && y-2>=1)
        {
            add(1);
            s(x+1,y-2);
            del();
        }
        if(x+2<=n && y-1>=1)
        {
            add(2);
            s(x+2,y-1);
            del();
        }
        if(x+2<=n && y+1<=m)
        {
            add(3);
            s(x+2,y+1);
            del();
        }
        if(x+1<=n && y+2<=m)
        {
            add(4);
            s(x+1,y+2);
            del();
        }
        b[x][y]=1;
    }
}
int main()
{
    fin>>n>>m;
    s(1,1);
    if(first==0)
        fout<<-1<<endl;
    fin.close();
    fout.close();
    return 0;
}
