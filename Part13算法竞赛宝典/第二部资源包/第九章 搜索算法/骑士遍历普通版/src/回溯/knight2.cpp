#include<iostream>
#include<fstream>
using namespace std;
int n,x,y,qq;
int way[2000],pos[41][41];
int main()
{
    int i,j;
    int dx[9],dy[9];
    dx[4]=dx[1]=1;
    dx[3]=dx[2]=2;
    dx[5]=dx[8]=-1;
    dx[6]=dx[7]=-2;
    dy[3]=dy[6]=1;
    dy[4]=dy[5]=2;
    dy[2]=dy[7]=-1;
    dy[1]=dy[8]=-2;
    ifstream fin("knight2.in");
    ofstream fout("knight2.out");
    fin>>n>>x>>y;
    x++;
    y++;
    qq=n*n;
    i=2;
    int key=0;
    pos[x][y]=1;
    while(i>1 && i<=qq)
    {
        key=0;
        do
            {
                way[i]++;
                if(way[i]>8)
                {
                        pos[x][y]=0;
                    way[i]=0;
                    i--;
                    x-=dx[way[i]];
                    y-=dy[way[i]];
                    key=1;
                    break;
                }
            }while(x+dx[way[i]]<1 || x+dx[way[i]] >n || y+dy[way[i]]<1 || y+dy[way[i]]>n||pos[x+dx[way[i]]][y+dy[way[i]]]);
        if(!key)
            {
            x+=dx[way[i]];
            y+=dy[way[i]];
            pos[x][y]=i;
            i++;
            
            }
    }
    if(i==1)
        fout<<-1<<endl;
    else
        {
            for(i=n;i>=1;i--)
                {
                for(j=1;j<n;j++)
                    fout<<pos[j][i]<<' ';
                fout<<pos[n][i]<<endl;
                }
        }
    fin.close();
    fout.close();
    return 0;
}
