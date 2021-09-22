//凸多边形三角划分－高精度 
#include <iostream>
#include <algorithm>
#include <stdlib.h>
#include <string.h>
using namespace std;
int a[51][101],m,f[51][51][101];//a[][]代表顶点值 
struct node
{
  int x,y,z;
}n[51];

bool judge[51][51];
int Count=0,mid[51][51];//mid[i][j]代表由顶点i与顶点j与mid[i][j]组成的三角形。 

bool cmp(node x,node y)//排序 
{
  if(x.x==y.x)
  {
	if(x.y==y.y)
      return x.z<y.z;
    return x.y<y.y;
  }
  return x.x<y.x;
}

void init()
{
  int i,j;
  string str;
  scanf("%d",&m);
  for(i=1;i<=m;i++)
  {
    cin>>str;
    a[i][0]=str.length();
    for(j=0;j<a[i][0];j++)
      a[i][a[i][0]-j]=str[j]-'0';
  }//初始化，倒序输入数字 
  for(i=2;i<=m;i++)
    for(j=1;j<=m-i;j++)
   	  f[j][i+j][0]=100;//相当于设置最大值，[0]代表位数，
                       //所以相当于设f[j][i+j]相当于100位 
} 
				
void mult(int x[],int y[],int z[])//高精度乘法 
{
  int i ,j;
  for(i=1;i<=x[0];i++)
    for(j=1;j<=y[0];j++)
    {
   	  z[i+j-1]+=x[i]*y[j];
   	  z[i+j]+=z[i+j-1]/10;
   	  z[i+j-1]%=10;
    }
  z[0]=i+j+1;
  while(!z[z[0]])
    z[0]--;
}
			
void add(int x[],int y[],int z[])//高精度加法 
{
  int i,len=max(x[0],y[0]);
  for(i=1;i<=len;i++)
  {
 	z[i]+=x[i]+y[i];
 	if(z[i]>=10)
    {
 	  z[i+1]++;
 	  z[i]-=10;
    }
    if(z[len+1])
      z[0]=len+1;
    else
      z[0]=len;
  }
}

bool Min(int x[],int y[],int z[])//判断两个高精度数的大小，返回小的 
{
  int len=x[0],i;
  if(x[0]>y[0])
  {
   	for(i=0;i<=y[0];i++)
   	  z[i]=y[i];
    return false;
  }
  else if(x[0]<y[0])
  {
    for(i=0;i<=x[0];i++)
   	  z[i]=x[i];
    return true;
  }
  else
 	for(int i=len;i>=1;i--)
    {
   	  if(x[i]>y[i])
   	  {
   	    for(i=0;i<=y[0];i++)
          z[i]=y[i];
   	    return false;
      }
	  if(x[i]<y[i])
	  {
		for(i=0;i<=x[0];i++)
          z[i]=x[i];
        return true;
	  }
	}
}

void find(int START,int END)//递归查找三角形的顶点 
{
  if(START+1>=END)
    return;
  n[++Count].x=START;
  n[Count].y=mid[START][END];
  n[Count].z=END;
  find(START,mid[START][END]);
  find(mid[START][END],END);
}
    
void fun()
{
  int tmp_1[101],tmp_2[101],tmp_3[101],tmp_4[101],i,j,k;
  for(i=1;i<m;i++)//递归方程 f[I,J]=Min{f[I,K]+f[K,J]+a[I]*a[J]*a[K]}
    for(j=1;j<=m-i;j++)
    {
      for(k=1;k<i;k++)
      {
     	memset(tmp_1,0,sizeof(tmp_1));
        memset(tmp_2,0,sizeof(tmp_2));
        memset(tmp_3,0,sizeof(tmp_3));
        memset(tmp_4,0,sizeof(tmp_4));
     	add(f[j][j+k],f[j+k][j+i],tmp_1);
     	mult(a[j],a[k+j],tmp_2);
     	mult(tmp_2,a[j+i],tmp_3);
     	add(tmp_1,tmp_3,tmp_4);
     	if(Min(tmp_4,f[j][j+i],f[j][j+i]))
   	      mid[j][j+i]=j+k;//记录三角形顶点 
      }
    }
  find(1,m);
  sort(n+1,n+Count+1,cmp);//按字典序排序 
  for(i=f[1][m][0];i>=1;i--)
    printf("%d",f[1][m][i]);
  printf("\n");
  for(i=1;i<=Count-1;i++)
 	printf("%d %d %d,",n[i].x,n[i].y,n[i].z);
  printf("%d %d %d\n",n[Count].x,n[Count].y,n[Count].z);
}
			
int main()
{
  freopen("Triangle.in","r",stdin);
  freopen("Triangle.out","w",stdout);
  init();
  fun();
  return 0;
}
