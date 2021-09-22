//添加号问题 
#include <stdlib.h>
#include <string.h>
#include <iostream>
#define max(a,b) ((a<b)?b:a)
#define min(a,b) ((a<b)?a:b)
using namespace std;

const int Ns=205;
const int Ms=25;

int n,num[Ns];
char tmp[Ns];

struct bign
{
  int s[Ns],len;
  bign()
  {
    memset(s,0,sizeof(s));
    len=0;
  }
}f[Ns][Ms];

bign add(bign a,int st,int ed)//高精度加法
{
  int i,jw=0;
  for (i=1;st<=ed;++i)
  {
    a.s[i]=a.s[i]+num[ed]+jw;
    jw=a.s[i]/10;
    a.s[i]%=10;
    --ed;
  }
  --i;
  while(jw!=0)
  {
    a.s[++i]+=jw;
    jw=a.s[i]/10;
    a.s[i]%=10;
  }
  a.len=max(a.len,i);
  return a;
}

bool cmp(bign a,bign b)//高精度比较,a<b返回true 
{
  if (a.len<b.len)
    return true;
  else if (a.len>b.len)
    return false;
  for (int i=a.len;i>0;--i)
  {
    if (a.s[i]<b.s[i]) 
      return true;
    else if (a.s[i]>b.s[i])
      return false;
    else
      continue;
  }
}

int main()
{
  freopen("parentheses.in","r",stdin);
  freopen("parentheses.out","w",stdout);  
  scanf("%s",&tmp);
  scanf("%d",&n);
  int i,j,k,len=strlen(tmp);
  for (i=0;i<len;++i)
    num[i+1]=int(tmp[i]-48);
    
  for (i=1;i<=len;++i)//边界f[i][0]=num[1][i];
    f[i][0]=add(f[i][0],1,i);
    
  /*
  *Dp,f[j][i]为前j个数添加i个括号所取得的最小值. 
  *转移方程 f[j][i]=min{f[k-1][i-1]+num[k][j]};i<k<=len;
  *每次添加括号的位置在k位置前 
  */
  for (i=1;i<=n;++i)
    for (j=i+1;j<=len;++j)
    {
      f[j][i].len=Ns;//相当于f[j][i]=inf; 
      for (k=i+1;k<=j;++k)
      {
        bign c=add(f[k-1][i-1],k,j);
        if (!cmp(f[j][i],c))
          f[j][i]=c;
      }
    }
  for (i=f[len][n].len;i>0;--i)
    printf("%d",f[len][n].s[i]);
  printf("\n");
  return 0;
}
