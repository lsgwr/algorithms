//骑士遍历 -优化算法 
#include<iostream>
using namespace std;
int way[5][2],a[100];//增量函数 

int main()
{
  freopen("knight.in","r",stdin);
  freopen("knight.out","w",stdout);
  int x=1,y=1,Gx,Gy,kk=1;
  way[1][0]=1;  way[1][1]=-2;
  way[2][0]=2;  way[2][1]=-1;
  way[3][0]=2;  way[3][1]=1;
  way[4][0]=1;  way[4][1]=2; 
  cin>>Gx>>Gy;
  a[1]=2;
  while(kk>=1)
  {
    a[kk]++;
    while(a[kk]>4 && kk>=1)
    {
      a[kk]=0; 
      kk--;
      x-=way[a[kk]][0];  
      y-=way[a[kk]][1]; 
      a[kk]++;
    }
    if(kk==0)
    {
      cout<<-1<<endl;  
      return 0;
    }
    x+=way[a[kk]][0];   
    y+=way[a[kk]][1];
         
    if(x==Gx  && y==Gy)   
      break;   
    if(x>Gx||x<1||y>Gy||y<1||(Gy-y)>(2*(Gx-x)) )//优化   
    {
      x-=way[a[kk]][0];  
      y-=way[a[kk]][1]; 
    }
    else 
      kk++;
  }
  for(int i=1;i<kk;++i)
    cout<<a[i]<<" ";
  cout<<a[kk]<<endl;
  return 0;
}
