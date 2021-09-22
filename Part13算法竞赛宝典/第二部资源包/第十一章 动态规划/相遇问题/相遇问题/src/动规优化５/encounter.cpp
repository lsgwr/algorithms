//奇妙的相遇－动规优化５ 
#include<iostream>
#include<iomanip>
#define MAX 1555
using namespace std;
int n,m;
//MAX>>1相当于MAX/2，也是位运算，由于使用镜像，只需开辟一半的空间 
double f[MAX][MAX>>1];
int inline d(int x,int y)//用来计算(x,y)位置能扩展出来的路径数
{ 
  return 2+(x>1 && x<n)+(y<m);//判断是否越界，能扩展出的路径数至少为2 
}
int inline minn(int ax,int bx)//返回ax,bx的较小值 
{
  return ax<=bx?ax:bx;
}
int inline maxn(int ax,int bx)//返回ax,bx的较大值
{ 
  return ax>=bx?ax:bx;
}

int inline absn(int ax)//返回ax的绝对值
{ 
  return ax>=0?ax:-ax;
}

double Dp()//计算过程 
{
  if(m&1^1)//初始奇偶性判断，使用了位运算 
    return 0;
  int i,j,k,end_k,end_j;
  for(i=n;i>=0;f[i--][0]=1)//此处开始逆推，逆推的初始位置的概率为1 
    for(j=maxn(i,m+1-i),end_j=minn(n,m+1+i);j<=end_j;++j)
    {//此处通过maxn(i,m+1-i)来构造镜像，同时对公主的范围进行剪枝 
      if(j-i&1^1)//过程中的奇偶性判断,此处使用位运算，表示当i与j同奇偶时返回1 
        f[j][0]=(f[j][1]+f[j][1]+f[j+1][0]+f[j-1][0])/d(j,0);
      for(k=2-(j-i&1),end_k=minn(minn(j-i,m),i-absn(m+1-j));k<=end_k;k+=2)
        //①j-i&1同样是过程中的奇偶性判断，当i+j为偶数时返回0，否则返回1
        //②minn(j-i,m)为镜像选择，同样也是公主追赶王子的剪枝 ，absn(m+1-j)为公主范围剪枝 
        f[j][k]=(f[j+1][k]+f[j-1][k]+f[j][k+1]+f[j][k-1])/d(j,k);//此处为滚动数组的合并 
    }
  return f[m+1][0];//返回公主的初始位置概率值 
}

int main()
{
  freopen("encounter.in","r",stdin);
  freopen("encounter.out","w",stdout);    
  cin>>n;
  m=n>>1;
  cout<<fixed<<setprecision(4)<<Dp()<<'\n';
  return 0;
}
