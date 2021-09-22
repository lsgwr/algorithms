#include<iostream>
#include<fstream>
#include<iomanip>
using namespace std;
short x,y;
unsigned int way[255][255][8];
bool q=1;
short dx[4]={1,2,2,1},dy[4]={2,1,-1,-2};
const int ttt=1000000000;
void add(short i ,short j, short k)
{
    short xx;
    bool r=0;
    if(q)
        {
        way[i+1][j+2][8]+=way[i-dx[k]+1][j-dy[k]+2][8];
        if(way[i+1][j+2][8]>=ttt)
            q=0;
        }
    else
            for(xx=8;xx>=1;xx--)
                {
                way[i+1][j+2][xx]+=way[i-dx[k]+1][j-dy[k]+2][xx]+r;
                if(way[i+1][j+2][xx]>ttt)
                    {way[i+1][j+2][xx]-=ttt;r=1;}
                else
                    r=0;
                }
}
int main()
{
    short i,j,k;
    ifstream fin("horse.in");
    ofstream fout("horse.out");
    fin>>x>>y;
    way[0+1][(y>>1)+2][8]=1;
    i=1;
    int xm,ym;
    int len=0;
    for(i=1;i<=x;i++)
        for(j=0;j<=y;j++)
            for(k=0;k<4;k++)
                add(i,j,k);
        if(q)
            fout<<way[x+1][(y>>1)+2][8];
        else
            {
                i=1;
                while(!way[x+1][(y>>1)+2][i])
                    i++;
                fout<<way[x+1][(y>>1)+2][i];
                i++;
                while(i<=8)
                {
                    fout<<setw(9)<<setfill('0')<<way[x+1][(y>>1)+2][i];
                    i++;
                }
            }
    /*for(i=1;i<=x;i++)
    {
        for(j=0;j<=y;j++)
            fout<<way[i+1][j+2][10]<<' ';
            fout<<endl;
    }
        */
    fout<<endl;
    fin.close();
    fout.close();
    return 0;
}
