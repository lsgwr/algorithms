//骑士遍历优化版 
#include<iostream>
#include<iomanip>
using namespace std;
const int base=10000,LONGMAX=400,YMAX=1000;
int x,y,a[4][YMAX+1][LONGMAX+1],len,n,m;
int ans[LONGMAX+1];

void add(int p,int y1,int px,int y2)
{
  int r=0;
  y2=(y2>0?y2:-y2);
  for(int i=1;i<=len;i++)
  {
    a[p][y1][i]+=a[px][y2][i]+r;
    if(a[p][y1][i]>=base)
    {
      r=1;
      a[p][y1][i]-=base;
    }
    else
      r=0;
  }
  if(r==1)
  {
    len++;
    a[p][y1][len]=1;
  }
} 

void DP(int i,int j)//求(i，j)的路径数 
{
  int p1=i%3,p2,p3;
  p2=p1+1;
  if(p2>=3)
    p2=0;
  p3=3-p1-p2;
  //滚动调整 
  for(int k=1;k<=m;k++)
    a[p1][j][k]=0;
  add(p1,j,p3,j+2);
  add(p1,j,p3,j-2);
  add(p1,j,p2,j+1);
  add(p1,j,p2,j-1);
}

void mul_and_add(int p1,int p2,int p3,int p4,int j)
{
  j=(j>0?j:-j);
  for(int k=1;k<=len+2;k++)
    for(int l=1;l<=len+2;l++)
      ans[k+l-1]+=a[p1][j][k]*a[p2][j][l];
      
  int r=0;
  for(int k=1;k<=LONGMAX;k++)
  {
    ans[k]+=r;
    r=ans[k]/base;
    ans[k]%=base;
  }
  for(int k=1;k<=len+2;k++)
    for(int l=1;l<=len+2;l++)
      ans[k+l-1]+=a[p3][j][k]*a[p4][j][l];
  r=0;
  for(int k=1;k<=LONGMAX;k++)
  {
    ans[k]+=r;
    r=ans[k]/base;
    ans[k]%=base;
  }
}

void work(int x1,int x2,int x3,int x4)
{
  int p1=x1%3,p2=x2%3,p3=x3%3,p4=x4%3;
  for(int j=0;j<=m;j++)
  {
    add(3,j,p3,j+1);
    add(3,j,p3,j-1);
  }
  for(int j=-m;j<=m;j++)
    mul_and_add(p1,p2,3,p4,j);
}

int main()
{
  freopen("horse.in","r",stdin);
  freopen("horse.out","w",stdout);  
  cin>>x>>y;
  if(x==1)
  {
    cout<<0<<endl;
    return 0;
  }
  n=((x+1)>>1);
  m=(y>>1);//镜像+折中 
  len=a[0][0][1]=1;
  for(int i=1;i<=n;i++)
    for(int j=0;j<=m;j++)
      DP(i,j);
      
  if(x&1)
    work((x>>1),(x>>1)+1,(x>>1)-1,(x>>1));
  else
    work((x>>1),(x>>1),(x>>1)-1,(x>>1)-1);
  int c=LONGMAX;
  while(ans[c]==0 && c>=1)
    c--;
  cout<<ans[c];
  for(int i=c-1;i>=1;i--)
    cout<<setw(4)<<setfill('0')<<ans[i];
  cout<<endl;
  return 0;  
}
