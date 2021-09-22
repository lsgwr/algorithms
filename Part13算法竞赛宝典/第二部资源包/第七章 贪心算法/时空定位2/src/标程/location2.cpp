//时空定位２
#include"stdio.h"
#include"stdlib.h"
#include"math.h"

#define N 10010

struct Node
{
    double begin;
    double end;
}node[N];

int cmp(const void *a,const void *b)//Double类型的比较要特别注意
{
  if((*(struct Node *)a).begin!=(*(struct Node *)b).begin)
    return (*(struct Node *)a).begin>(*(struct Node *)b).begin ?1:-1;
  else
    return (*(struct Node *)a).end >(*(struct Node *)b).end ?1:-1;
}

int main()
{
  freopen("location2.in", "r", stdin);
  freopen("location2.out", "w", stdout);    
  int i,n,k;
  int ncase;
  int w,h;
  int x;
  int ans=0;
  double R,t,r,max,p;
  int flag=1;
  scanf("%d",&ncase);
  while(ncase--)
  {
    scanf("%d%d%d",&n,&w,&h);
    R= h/(2*1.0) ;
    t= h*h/(4*1.0);
    k=0;
    flag=1;
    
    for(i=0;i<n;i++)//预处理，将问题转换为区间问题
    {
      scanf("%d%lf",&x,&r);
      if(r>R)
      {
        r=sqrt(r*r-t);
        node[k].begin=x-r;
        if(node[k].begin<0) 
          node[k].begin=0;
        node[k].end=x+r;
        if(node[k].end>w) 
          node[k].end=w;
        k++;
      }
      else
        continue;
    }
    qsort(node,k,sizeof(node[0]),cmp);
    ans=0;
    p=0;
    i=0;

    while(p<w)//当还没有铺满
    {
       max=0;
       //寻找符合条件，并且最右端的值最大的那个
       for(i;node[i].begin<=p&&i<k;i++)
       {
         if(node[i].end-p>=max)
           max=node[i].end-p;
       }
       if(!max)
       {
         flag=0;
         break;
       }
       p+=max;
       ans++;
    }
    if(!flag)
      printf("0\n");
    else
      printf("%d\n",ans);
  }
  return 0;
}
