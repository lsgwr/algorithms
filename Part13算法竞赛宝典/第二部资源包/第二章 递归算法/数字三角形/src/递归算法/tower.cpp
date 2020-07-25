//数字三角形递归算法 
#include <iostream>
#include <cstdlib>
using namespace std;
int a[1001][1001],n;

int fun(int x,int y)//x为行，y为列 
{ 
  int r,l; 
  if(x==n)//结束条件，如果到了最底层，返回本身值 
    return a[x][y];
  l=fun(x+1,y);//从左往下走，要知道（x,y）的最值，必先求下一层左右的最值 
  r=fun(x+1,y+1);//从右往下走 
  if(l>r)
   return l+a[x][y];
  else
   return r+a[x][y];         
}

int main()
{
  freopen("tower.in","r",stdin);
  freopen("tower.out","w",stdout);
  int i,j;
  cin>>n;
  for(i=1;i<=n;i++)
    for(j=1;j<=i;j++)
      cin>>a[i][j]; 
  cout<<fun(1,1)<<'\n';//求最顶点（1，1）的值 
  return 0;
}
