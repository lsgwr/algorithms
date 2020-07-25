//魔板问题－参考程序 
#include <iostream>
#include <fstream>
using namespace std;
 
void swap(int &x,int &y)//交换两个元素 
{
  int tmp=x;
  x=y;
  y=tmp;
}
 
struct data
{
  int last,deep,opr,x;
}q[100000];					//队列 
 
bool use[100000];			//判重 
 
int od(int x)			//康托展开 
{
  int num[9];
  for (int i=8;i>=1;i--)
  {
    num[i]=x%10;
    x/=10;
  } 
  int jie[8],tt=0;
  jie[0]=1;
  for (int i=1;i<=7;i++)
    jie[i]=jie[i-1]*i;
  for (int i=1;i<=7;i++)
  {
    int count=0;
    for (int j=i+1;j<=8;j++)
      if (num[j]<num[i]) 
        count++;
    tt+=jie[8-i]*count;
  }
  return tt;
}
 
int main()
{
  ifstream fin("Magic.in");
  ofstream fout("Magic.out");
 
  int now[8],l=1,r=1,goal=0,k[3][8]={{4,5,6,7,0,1,2,3},{3,0,1,2,7,4,5,6},{0,5,1,3,4,6,2,7}};
  for (int i=0;i<=3;i++)
    fin>>now[i];
  for (int i=7;i>=4;i--)
    fin>>now[i];
  for (int i=0;i<=7;i++)
    goal=goal*10+now[i];
  q[1].deep=0;
  q[1].opr=-1;
  q[1].x=12348765;
  use[od(12348765)]=true;
  q[1].last=0;
  if (q[1].x==goal)
  {
    fout<<0<<endl;
    fout<<endl;
    return 0;
  }
  for (;;)
  {
    int tmp[8],x=q[l].x;
    for (int i=7;i>=0;i--)
    {
      tmp[i]=x%10;
      x/=10;
    }
    for (int i=0;i<3;i++)
    {
      int nn[8];
      for (int j=0;j<8;j++) 
        nn[j]=tmp[k[i][j]];
      x=0;
      for (int j=0;j<8;j++) x=x*10+nn[j];
        if (!use[od(x)])
        {
          r++;
          q[r].deep=q[l].deep+1;
          q[r].x=x;
          q[r].last=l;
          q[r].opr=i;
          use[od(x)]=true;
          if (x==goal) 
            break;
        }
    }
    l++;
    if (x==goal) 
      break;
    if (l>r) 
      break;
  }
  fout<<q[r].deep<<endl;
  int len=0,res[1000];
  while (q[r].opr>=0)
  {
    len++;
    res[len]=q[r].opr;
    r=q[r].last;
  }
  for (int i=len;i>=1;i--)
    fout<<(char)('A'+res[i]);
  fout<<endl;
  return 0;
}

