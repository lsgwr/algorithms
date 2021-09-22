#include<iostream>
#include<fstream>
using namespace std;
int main()
{
    ifstream fin("knight.in");
    ofstream fout("knight.out");
    int x,y;
    fin>>x>>y;
    x--;
    y--;
    int m=y/2,i;
    if(m>x)
        fout<<-1<<endl;
    else
        {
            if(x==m);
            else
            {
                for(i=1;i<(x-m)/4;i++)
                    fout<<"3 2 ";
                if(y%2==0)
                {
                if((x-m)%4==0)
                    fout<<"3 2";
                else if((x-m)%4==2)
                    fout<<"3 2 4 1";
                else if((x-m)%4==3)
                    {fout<<"3 2 3 3";m--;}
                else if((x-m)%4==1)
                    {fout<<"3 3 1";}
                }
                else
                {
                    if((x-m)%4==0)
                        fout<<"3 4 1";
                    else if((x-m)%4==3)
                        fout<<"3 2 4 2";
                    else if((x-m)%4==2)
                        fout<<"3 2 3";
                    else if((x-m)%4==1)
                        {fout<<"3 3 3";m--;}
                }
            }
            for(i=1;i<=m;i++)
                fout<<" 4";
            fout<<endl;
        }
}
