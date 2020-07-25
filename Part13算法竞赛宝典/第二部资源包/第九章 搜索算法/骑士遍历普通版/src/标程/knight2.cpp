//∆Ô ø±È¿˙2  
#include<iostream>
using namespace std;
int n,x,y,Count;
int w[2000],pos[41][41];

int main()
{
  freopen("knight2.in","r",stdin);
  freopen("knight2.out","w",stdout);
  int i,j,key=0;
  int dx[9],dy[9];
  dx[4]=dx[1]=1;  dx[3]=dx[2]=2;
  dx[5]=dx[8]=-1; dx[6]=dx[7]=-2;
  dy[3]=dy[6]=1;  dy[4]=dy[5]=2;
  dy[2]=dy[7]=-1; dy[1]=dy[8]=-2;
  cin>>n>>x>>y;
  x++;
  y++;
  Count=n*n;
  i=2;
  pos[x][y]=1;
  while(i>1 && i<=Count)
  {
    key=0;
    do
    {
      w[i]++;
      if(w[i]>8)
      {
        pos[x][y]=0;
        w[i]=0;
        i--;
        x-=dx[w[i]];
        y-=dy[w[i]];
        key=1;
        break;
      }
    }while(x+dx[w[i]]<1||x+dx[w[i]]>n||y+dy[w[i]]<1||y+dy[w[i]]>n||pos[x+dx[w[i]]][y+dy[w[i]]]);
    if(!key)
    {
      x+=dx[w[i]];
      y+=dy[w[i]];
      pos[x][y]=i;
      i++;
    }
  }
  if(i==1)
    cout<<-1<<endl;
  else
  {
    for(i=n;i>=1;i--)
    {
      for(j=1;j<n;j++)
        cout<<pos[j][i]<<' ';
      cout<<pos[n][i]<<endl;
    }
  }
  return 0;
}
