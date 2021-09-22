//八数码问题－双向宽度搜索演示版 
#include <iostream>
using namespace std;
int p1,p2,t1,t2,number,x[5];//p为首，t为尾，number为交换的总次数 
struct node
{
  string str;
  int pre;//前导 
}c1[5004],c2[50004];

int change(int p0)//找出可交换的可能次数，及能交换的所在位置 
{
  if(p0==0)
  { x[1]=1,x[2]=3;return 2;  }
  else if(p0==1)
  { x[1]=0,x[2]=2,x[3]=4;return 3; }
  else if(p0==2)
  { x[1]=1,x[2]=5;return 2;  }
  else if(p0==3)
  { x[1]=0,x[2]=4,x[3]=6;return 3;}
  else if(p0==4)
  { x[1]=1,x[2]=3,x[3]=5,x[4]=7;return 4; }
  else if(p0==5)
  { x[1]=2,x[2]=4,x[3]=8;return 3; }
  else if(p0==6)
  { x[1]=3,x[2]=7;return 2;  }
  else if(p0==7)
  { x[1]=4,x[2]=6,x[3]=8;return 3; }
  else if(p0==8)
  { x[1]=5,x[2]=7;return 2;  }
}

void outC1(int v)//递归逆序输出 
{
  if(v==1)
  {
    number++;
    cout<<c1[v].str<<endl;//输出每一步 
  }
  else
  { 
    outC1(c1[v].pre);
    number++;
    cout<<c1[v].str<<endl;//输出每一步  
  }  
}

void outC2(int v)
{
  while(v>=1)
  {          
    number++;         
    cout<<c2[v].str<<endl;//输出每一步   
    v=c2[v].pre;
  }

}

void out()
{
  outC1(t1);
  outC2(c2[t2].pre);
  cout<<number-1<<endl;  
  exit(0);
}

int find(int x,string tmp)//查找本队列有无重复的字符串 
{
   int i,k;
   if(x==1)
   {
     for(i=1;i<=t1;i++)
       if(tmp==c1[i].str)
         return 1;
   }
   if(x==2)
   {
     for(i=1;i<=t2;i++)
       if(tmp==c2[i].str)
         return 1;     
   }  
   return 0;    
}

int checkC1(string tmp)//检查另一队列是否有碰头 
{
  int i;
  for(i=1;i<=t1;i++)
    if(tmp==c1[i].str)
    {
      t1=i;                
      return 1;
    }
  return 0;      
}

int checkC2(string tmp)//检查另一队列是否有碰头 
{
  int i;
  for(i=1;i<=t2;i++)
    if(tmp==c2[i].str)
    {
      t2=i;                
      return 1;
    }  
  return 0;
}

void addC1()//添加队列1 
{
  int k,i,pos,v;
  string tmp,t;
  k=p1;
  pos=c1[k].str.find('0',0);//找出0在何处 
  v=change(pos);//获得可以交换的次数 
  for(i=1;i<=v;i++)//将四个方向都遍历 
  { 
    tmp=c1[k].str;  //两两替换 
    t=tmp[x[i]];
    tmp.replace(pos,1,t);
    tmp.replace(x[i],1,"0");
    if(find(1,tmp)==0)//如果本队列无该新字符串，则添加 
    {
      t1++;
      c1[t1].str=tmp;
      c1[t1].pre=p1;
      if(checkC2(tmp)==1)//如果在另一队列碰头 
        out(); 
    }   
  }
  if(p1<t1)  
    p1++;    
} 

void addC2()//添加队列2 
{
  int k,i,pos,v;
  string tmp,t;
  k=p2;
  pos=c2[k].str.find('0',0);
  v=change(pos);
  for(i=1;i<=v;i++)//将四个方向都遍历 
  { 
    tmp=c2[k].str;  //两两替换 
    t=tmp[x[i]];
    tmp.replace(pos,1,t);
    tmp.replace(x[i],1,"0");
    if(find(2,tmp)==0)//如果本队列无该字符串，则添加新串 
    {
      t2++;
      c2[t2].str=tmp;
      c2[t2].pre=p2;
      if(checkC1(tmp)==1)//如果和另一队列重合，则打印结果 
        out();
    }
  }
  if(p2<t2)  
    p2++;    
} 

int main()
{ 
  //freopen("Puzzle8.in","r",stdin);  
  freopen("Puzzle8.ans","w",stdout);
  int i;  
  string start="317428650";
  string goal= "123804765";
  p1=1,p2=1;//首指针 
  t1=1,t2=1;//尾指针 
  c1[1].str=start;//从下标1开始 
  c1[1].pre=0;
  c2[1].str=goal;
  c2[1].pre=0;

  while(t1<5000 ||t2<5000) 
  {
    if(t1<=t2 )//反复扩展队列，队列短的优先考虑
      addC1();
    else
      addC2();  
  }
  cout<<"-1\n";    
}
