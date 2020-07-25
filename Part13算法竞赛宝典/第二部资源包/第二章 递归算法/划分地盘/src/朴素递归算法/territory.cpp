//地盘划分－朴素递归算法 
#include <cstdlib>
#include <iostream>
using namespace std;

int s;
int z(int a,int b)
{
  if(a==b)
    return 1;   
  else if(a<b)
  {
    b=b-a;
    s=z(a,b)+1;
    return s;       
  }
  else
  {
    a=a-b;
    s=z(a,b)+1;
    return s;    
  }
}

int main()
{
  freopen("territory.in","r",stdin);
  freopen("territory.out","w",stdout);
  int x,y;
  scanf("%d %d",&x,&y);
  printf("%d\n",z(x,y)); 
  return 0;
}
