#include<iostream>
#include<fstream>
using namespace std;

ifstream fin("labyrinth.in");
ofstream fout("labyrinth.out");
int len=0;
int ans[300][2];
bool pos[105][105];

struct _line
{
  int x,y,from;
}line[10001];

void add(int x1,int y1,int last)
{
  len++;
  line[len].x=x1;
  line[len].y=y1;
  line[len].from=last;
  pos[x1][y1]=1;
}

void print(int i)
{
  int j=0,k;
  while(i!=0)
  {
    j++;
    ans[j][0]=line[i].x;
    ans[j][1]=line[i].y;
    i=line[i].from;
  }
  while(j>=1)
  {
    fout<<ans[j][0]<<' '<<ans[j][1]<<endl;
    j--;
  }
}

int main()
{
  int zx[9];
  int zy[9];
  zx[1]=0;zy[1]=1;
  zx[2]=1;zy[2]=1;
  zx[3]=1;zy[3]=0;
  zx[4]=1;zy[4]=-1;
  zx[5]=0;zy[5]=-1;
  zx[6]=-1;zy[6]=-1;
  zx[7]=-1;zy[7]=0;
  zx[8]=-1;zy[8]=1;
  int n,m,i,j;
  fin>>m>>n;
  for(i=1;i<=n;i++)
    for(j=1;j<=m;j++)
      fin>>pos[j][i];
  for(i=0;i<=m+1;i++)
    pos[i][0]=pos[i][n+1]=1;
  for(i=0;i<=n+1;i++)
    pos[0][i]=pos[m+1][i]=1;
  int x1=1,y1=1,last=0;
  add(1,1,0);
  i=1;
  while((x1!=m || y1!=n )&& i<=len)
  {
    for(j=1;j<=8;j++)
      if(!pos[x1+zx[j]][y1+zy[j]])
        add(x1+zx[j],y1+zy[j],i);
    i++;
    x1=line[i].x;
    y1=line[i].y;
  }
  if(x1==m && y1==n)
    print(i);
  else
    fout<<-1<<endl;
  fin.close();
  fout.close();
  return 0;
}
