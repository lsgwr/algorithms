//钓鱼 
#include <stdio.h>
 
int n;//湖的个数
int h;//可用时间
int fi[30];//最初钓鱼量
int di[30];//单位时间鱼的减少量
int cfi[30];//对 fi 数组的保存
int ti[30];//ti[i] 表示从第 i 个湖到第 i+1 湖的时间
 
struct LAKENODE
{
  int num[30];
  int max;
}lake[30];//用结构体数组保存可能解
 
int GetMax(int p[], int i, int j)//返回数组 p 中最大数的编号
{
  int cmax = p[i], loc = i;// loc :最大数的位置
  for (int m = i + 1; m <= j; m++)
    if (cmax < p[m])
    {
      cmax = p[m];
      loc = m;
    }
  return loc;
}
 
void GetFish()
{
  int i, j;
  int T = h * 60, t, CT;
  for(i = 1; i <= n; i++)//结构体数组初始化，全部置零
  {
     lake[i].max = 0;
     for(int j = 1; j <= n; j++)
       lake[i].num[j] = 0;
  }
  for (i = 1;i <= n; i++)//枚举结束湖的位置,从第一个湖到第n个湖
  {
    CT = T;
    t = 0;
    for (j = 1; j <= i; j++)
    {
      cfi[j] = fi[j];//将fi数组的值拷贝到cfi数组中
      CT = (j < i) ? CT - ti[j] * 5 : CT;//计算除去走路时间后的剩余时间
    }
    while (t < CT)
    {
      int k = GetMax(cfi, 1, i);//找到钓鱼量最多的湖的编号
      lake[i].max += cfi[k];//钓鱼总量增加在k湖一个单位时间钓到的鱼
      lake[i].num[k] += 5;//停在k湖的时间增加一个单位时间
      cfi[k] >= di[k] ? cfi[k] -= di[k] : cfi[k] = 0;//修改第k个湖在下一个时间单位中所能钓到的鱼
      t += 5;//时间增加一个单位时间
    }
  }
  for (i = 1; i <= n; i++)//将最大值拷贝到cfi数组中，用于查询真正最大值
    cfi[i] = lake[i].max;
  int la = GetMax(cfi, 1, n);//la：最优解下标
  for (i = 1; i <= n; i++)
  {
    (i!=n)?printf("%d, ",lake[la].num[i]):printf("%d", lake[la].num[i]);
  }
  printf("\nNumber of fish expected: %d\n\n", lake[la].max);
}
 
int main()
{
  freopen("fish.in","r",stdin);
  freopen("fish.out","w",stdout);  
  int i;
  while (scanf("%d", &n) && n)
  {
    scanf("%d", &h);
    for (i = 1; i <= n; i++)
      scanf("%d", &fi[i]);
    for (i = 1; i <= n; i++)
      scanf("%d", &di[i]);
    for (i = 1; i < n; i++)
      scanf("%d", &ti[i]);
    GetFish();
  }
  return 0;
}
