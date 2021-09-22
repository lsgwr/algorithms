//电脑组装 
#include <iostream>
#include <cstdio>
#include <map>
#include <string>
#include <cstring>
#include <algorithm>
using namespace std;

struct compute
{
  int type,price,quality;
}d[1005];

int init[1005],n,w,Count;
map<string,int> MAP;//将string映射为数字 

int cmp(compute a,compute b)
{
  return a.quality<b.quality;
}

int weight(int middle)
{
  int i,j,s;
  memset(init,-1,sizeof(init));
  for(i=middle;i<n;i++)
  {
    if(init[d[i].type]==-1)//如果该类型还没有统计 
      init[d[i].type]=d[i].price;//则添加该类型的一个组件的价值 
    else if(init[d[i].type]>d[i].price) 
      init[d[i].type]=d[i].price;//选取部件选给定quality的最便宜的部件
  }
  for(s=i=0;i<Count;i++)//看是否所有类型都选上 
	if(init[i]==-1)//若有没选的，则失败 
      return 0;
	else 
      s+=init[i];//否则累加 
  if(s>w) //若超过预算，失败 
    return 0;
  else //否则成功 
    return 1;	
}

int binary()//二分 
{
  int left=0,right=n-1,middle;
  while(left<right)
  {
    middle=(left+right+1)/2;//要加1，否则遇到8,9就成死循环
    if(weight(middle))  
      left=middle;
    else 
      right=middle-1;
  }
  return d[left].quality;
}

int main()
{
  freopen("Assemble.in","r",stdin);
  freopen("Assemble.out","w",stdout);  
  int t,i,j;
  string s; char a[25];
  cin>>t;
  while(t--)
  {   
    Count=0;
    scanf("%d%d",&n,&w);//组件数，预算 
    for(i=0;i<n;i++)
    {	
      scanf("%s",a);//类型 
      s=a;
      scanf("%s%d%d",a,&d[i].price,&d[i].quality); 
      if(MAP.find(s)==MAP.end())//如果该类型还没有 
        MAP[s]=Count++;//该类别映射为某个数字 
      d[i].type=MAP[s];//标记归类 
    }
	MAP.clear();//映射任务完成 
	sort(d,d+n,cmp);//按质量排序 
	printf("%d\n",binary());	
  } 
  return 0;
}
