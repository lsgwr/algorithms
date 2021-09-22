//邮票面值问题 
#include <stdio.h>
#include <stdlib.h>
int k,n;
int ans[11],val[11];//val[i]表示第i种邮票的面额
int f[1000],Max;

/*
枚举第dep种邮票的面值，对每个确定的面值递归调用以进一步确定下一种邮票的面值
即只用前dep-1种邮票能凑出1~max之间任意一个数额，但不能凑出max+1
*/
void search(int dep,int max)//max表示前dep-1种邮票最多能连续凑到max的数额
{
  int i,j,t[1000];
  if(dep>k)//如果所有种类的邮票都已经确定，则记录答案
  {
    if(max>=Max)
      for(Max=max,i=1;i<=k;i++)
		ans[i]=val[i];
    return;
  }
    
  for(i=0; i<1000; i++)  //备份动规数组
    t[i]=f[i];
    
  int v;//第dep种邮票面值(必定大于第dep-1种邮票的面值)
  for(v=val[dep-1]+1; v<=max+1; v++)//若v为max+2，则max+1无法凑出
  {
    val[dep]=v;
    for(int value=0; value<1000-v; value++)
      if(f[value]+1 < f[value+v])
		f[value+v]=f[value]+1;
    for(j=max; f[j]<=n; j++);//找到最小的j使得f[j]>n
    //若f[j]>n则表示无法用n张已确定的dep种邮票凑出j
    //因此用已确定的dep种邮票最多只能凑出连续j-1种数额
      search(dep+1, j-1);//确定下一种邮票面额
    
    for(j=0;j<1000;j++)//还原动规数组
            f[j]=t[j];
  }
}

int main()
{
  freopen("Stamp.in","r",stdin);
  freopen("Stamp.out","w",stdout);  
  int i,j;
  scanf("%d%d",&n,&k);
  val[1]=1; //一开始只确定了第一种邮票,第一种邮票必然是1
  for(i=0; i<=n; i++)//要凑出不超过n的面值i需要i张第一种邮票
    f[i]=i;
  for( ; i<1000; i++)//超过n的面值都不能凑出，故设为无穷
    f[i]=1e9;
    
  //因为第一种邮票面值已经确定，直接从第二种邮票开始枚举
  search(2,n);//仅用第一种邮票最多能连续凑到面值n，即用n张第一种邮票
  for(i=1; i<=k; i++)//输出
    printf("%d ",ans[i]);
  printf("%d\n",Max);
  return 0;
}
