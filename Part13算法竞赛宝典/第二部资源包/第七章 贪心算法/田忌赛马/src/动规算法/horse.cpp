//田忌赛马－动规法 
#include<stdio.h>
#include<algorithm>
using namespace std;

int a[1001],b[1001];
int f[1001][1001];

int S(int i, int j)//得分 
{
  if(a[i]>b[j])
    return 1;
  else if(a[i]<b[j])
    return -1;
  else 
    return 0;
}

void init(int n)
{
  for(int p=0; p<=n; p++)//将f全部设置为0
    for(int q=0; q<=n; q++)
      f[p][q]=0;
  for(int i=1; i<=n; i++)//设置f[i][0] 
  {
    if(a[i]>b[i])//因未用最慢的马，那必是依次用最快的马 
      f[i][0]=f[i-1][0]+1;
    else if(a[i]<b[i])
      f[i][0]=f[i-1][0]-1;
    else
      f[i][0]=f[i-1][0];//若相等就使用前一次的最优值
  }
  for(int j=n,g=1; j>=1; j--,g++)//设置f[i][i]
  {
    if(a[j]>b[g])//这说明田忌每一次都用最慢的马 
      f[g][g]=f[g-1][g-1]+1;
    else if(a[j]<b[g])
      f[g][g]=f[g-1][g-1]-1;
    else
      f[g][g]=f[g-1][g-1];
  }
}

int MAX(int i,int j)
{
  if(i>j)
    return i;
  else 
    return j;
}

bool compare(const int& a, const int& b)
{
  return a>b;
}

int main()
{
  freopen("horse.in","r",stdin);
  freopen("horse.out","w",stdout);
  int n,max;
  while(1)
  {
    scanf("%d",&n);
    if(n==0)
      break;
    for(int i=1; i<=n; i++)
      scanf("%d",&a[i]);
    for(int x=1; x<=n; x++)
      scanf("%d",&b[x]);
    sort(a+1,a+n+1,compare);
    sort(b+1,b+n+1,compare);
    init(n);	
    for(int j=2; j<=n; j++)//动态规划递推关系
      for(int k=1; k<j; k++)
		f[j][k]=MAX((f[j-1][k-1]+S(n-k+1,j)),(f[j-1][k]+S(j-k,j)));
    max = f[n][0];
    for(int g=1; g<=n; g++)//找到最大收益
      if(f[n][g]>max)
        max=f[n][g];
    printf("%d\n",max*200);
  }
}

