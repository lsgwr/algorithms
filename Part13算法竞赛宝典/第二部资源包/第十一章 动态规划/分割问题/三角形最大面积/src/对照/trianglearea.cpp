//三角形最大面积 
#include<iostream>
#include<fstream>
#define trin 120
using namespace std;

ifstream fin("trianglearea.in");
ofstream fout("trianglearea.out");

int ans=1,N;
int tri[trin+1][2*trin-1],ups[trin+1][2*trin-1];//朝下的三角形与朝上的三角形，两者其实可以合并，但为了保险起见，还是分开来做。 

int min(int a,int b)
{
  if(a<b)
    return a;
  else
    return b;
}

void init()
{
  char temp;
  int i,j;
  fin>>N;
     
  for(i=1;i<=N;i++)
    for(j=i;j<=2*N-i;j++)
    {
      fin>>temp;
      if(temp=='#')
        tri[i][j]=0;
      else
        tri[i][j]=1;
    }
  memcpy(ups,tri,sizeof(ups));
}

void dpdown()
{
  int i,j;
  for(i=2;i<=N;i++)
    for(j=i;j<=2*N-i;j+=2)
      if(tri[i][j] && tri[i-1][j-1] && tri[i-1][j] && tri[i-1][j+1])
      {
        tri[i][j]+=min(tri[i-1][j-1],tri[i-1][j+1]);
        if(ans<tri[i][j])
          ans=tri[i][j];
      }
}

void dpup()
{
  int i,j;
  for(i=N-1;i>0;i--)
    for(j=i+1;j<=2*N-i;j+=2)
      if(ups[i][j] && ups[i+1][j-1] && ups[i+1][j] && ups[i+1][j+1])
     {
        ups[i][j]+=min(ups[i+1][j-1],ups[i+1][j+1]);               
        if(ans<ups[i][j])
           ans=ups[i][j];
     }
}

void out()
{
  fout<<ans*ans<<endl;
}

int main()
{
  init();
  //display();
  //display2();
  dpdown();
    
  if(ans < N/2)//朝上的三角形，高度最大也不会超过 N/2 
    dpup();
  out();
  fin.close();
  fout.close();
  return 0;
}
