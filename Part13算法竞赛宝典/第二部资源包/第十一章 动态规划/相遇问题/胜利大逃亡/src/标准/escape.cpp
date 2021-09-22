//Ê¤Àû´óÌÓÍö 
#include <cstdio>
#include <string>
#include <cstdlib>

const long dx[] = {-1,0,1,0};
const long dy[] = {0,-1,0,1};

bool bat[120][120][300];
char map[120][120];
long f[120][120];

void swtch(long &d,long t)
{
  if (t == 3)
    d = (d+3)%4;
  else if (t == 2)
    d = (d+2)%4;
  else if (t == 1)
    d = (d+1)%4;
}

long getint()
{
  long rs=0;bool sgn=1;char tmp;
  do tmp = getchar();
  while (!isdigit(tmp)&&tmp-'-');
  if (tmp == '-')
  {
    tmp=getchar();
    sgn=0;
  }
  do rs=(rs<<3)+(rs<<1)+tmp-'0';
  while (isdigit(tmp=getchar()));
  return sgn?rs:-rs;	
}

int main()
{
  freopen("escape.in","r",stdin);
  freopen("escape.out","w",stdout);
  long m = getint();
  long n = getint();
  long T = m+n-1;
  for (long i=0;i<n+2;i++)
    map[i][0] = map[i][m+1] = 2;
  for (long i=0;i<m+2;i++)
    map[0][i] = map[n+1][i] = 2;

  long p = getint();
  for (long i=1;i<p+1;i++)
  {
    long y = getint();
    long x = getint();
    map[x][y] = 1;
  }

  long b = getint();
  for (long j=1;j<b+1;j++)
  {
    long y0 = getint();
    long x0 = getint();
    long d = getint()-1;
    long t = getint();
    long x = x0;
	long y = y0;
	if (x<1||y<1||x>n||y>m||map[x][y]>0)
	  continue;
	bat[x][y][1] = true;
	for (long i=1;i<T;i++)
	{
	  long tt;
	  for (tt=0;tt<4&&map[x+dx[d]][y+dy[d]]>0;++tt)
	    swtch(d,t);
	  if (tt < 4)
		x += dx[d];y += dy[d];
	  bat[x][y][i+1] = true;
	}
  }
  f[1][1] = 1;
  for (long i=1;i<n+1;i++)
	for (long j=1;j<m+1;j++)
  	  if (!bat[i][j][i+j-1]&&map[i][j]==0&&f[i][j]==0)
		f[i][j] = f[i-1][j]+f[i][j-1];
  printf("%ld",f[n][m]);
  return 0;
}
