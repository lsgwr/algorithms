//八数码问题－双向广搜+康托 
#include<iostream>
#include<cstdlib>
using namespace std;

int have[2][370000]={0};//存储某种状态是否存在 
int line[2][370000]={0};//存储双向广搜的状态 
int last[2][370000]={0};//存储上一种状态的编号 
int len[2]={0},mid[2]={0};//存储双向广搜的已有状态数和找到的答案 
int now[2]={0},s[9]={0},p=0;//存储当前搜索到的，八数码状态和0所在的位置 
int NUM;
void out()        //输出当前的八数码状态 
{
  NUM++;   
  cout<<s[0]<<" "<<s[1]<<" "<<s[2]<<"\n";
  cout<<s[3]<<" "<<s[4]<<" "<<s[5]<<"\n";
  cout<<s[6]<<" "<<s[7]<<" "<<s[8]<<"\n";
}

int cantor()//用康托展开式压缩 
{
  int a=0,i=0,j=0,b=0;
  for(i=8;i>=1;--i)
  {
    b=s[i];
    for(j=8;j>i;--j)
      if(s[j]<s[i])
        --b;
    a+=b;
    a*=i;
  }
  return a;
}

void get(int num) //把压缩的数转换成八数码 
{
  int i=0;
  for(i=8;i>=0;--i)
  {
    s[i]=num%10;
    if(s[i]==0)p=i;
      num/=10;
  }
}

int turn()//把八数码转换成数 
{
  int i=0,num=0;
  for(i=0;i<=8;++i)
  {
    num*=10;
    num+=s[i];
  }
  return num;
}

void go(int c,bool w) //把0位进行移动 
{
  int temp=0,num=0;
  temp=s[p],s[p]=s[p+c],s[p+c]=temp;
  ++len[w];
  line[w][len[w]]=turn();
  num=cantor();
  if(have[w][num]!=0)
    --len[w];
  else
  {
    last[w][len[w]]=now[w];
    if(have[!w][num]!=0)
    {
      mid[w]=len[w];
      mid[!w]=have[!w][num];
    }
    else
      have[w][num]=len[w];
  }
  temp=s[p],s[p]=s[p+c],s[p+c]=temp;
}

void out1(int num)//输出宽搜的各个步骤 
{
  if(num!=1)
  {
    out1(last[0][num]);
    cout<<"\n";
  }
  get(line[0][num]);
  out();
}

void out2(int num)//输出逆向宽搜的各个步骤 
{
  get(line[1][num]);
  if(num!=mid[1])
    out();
  if(num!=1)
  {
    cout<<"\n";
    out2(last[1][num]);
  }
}

int main()
{
  int a=0,b=0,temp=0;
  int i=0,j=0;
  for(i=1;i<=9;++i)//输入初始状态 
    cin>>s[i-1];
  a=turn();
  line[0][1]=a;
  len[0]=1;now[0]=1;have[0][cantor()]=1;
  //for(i=1;i<=9;++i)//输入目标状态 
  //  cin>>s[i-1];
  s[0]=1;s[1]=2;s[2]=3;s[3]=8;//固定的目标状态 
  s[4]=0;s[5]=4;s[6]=7;s[7]=6;s[8]=5;  
  b=turn();
  line[1][1]=b;
  len[1]=1;now[1]=1;
  if(have[0][cantor()]==0)//查看状态是否重合 
    have[1][cantor()]=1;
  else
    mid[0]=1,mid[1]=1;
  while(mid[0]==0 && (now[0]<=len[0] || now[1]<=len[1]))//回溯 
  {
    while(mid[0]==0 && len[1]>=len[0] && now[0]<=len[0])//宽搜 
    {
      get(line[0][now[0]]);
      if(p>=3)
        go(-3,0); //向上 
      if(mid[0]==0 && p<=5)
        go(3,0); //向下 
      if(mid[0]==0 && p>=1 && (p-1)%3!=2)
        go(-1,0);      //向左 
      if(mid[0]==0 && p<=8 && (p+1)%3!=0)
        go(1,0);       //向右 
      ++now[0];
    }
    while(mid[0]==0 && len[0]>=len[1] && now[1]<=len[1])//逆向宽搜 
    {
      get(line[1][now[1]]);
      if(p<=5)
        go(3,1);  //向下 
      if(mid[0]==0 && p>=3)
        go(-3,1); //向上 
      if(mid[0]==0 && p<=8 && (p+1)%3!=0)
        go(1,1);       //向右 
      if(mid[0]==0 && p>=1 && (p-1)%3!=2)
        go(-1,1);      //向左 
      ++now[1];
    }
  }
  if(mid[0]==0)
    cout<<"-1\n";//若不可以达到目标状态，输出-1
  else
  {
    out1(mid[0]);
    out2(mid[1]);  
    cout<<NUM-1<<endl;
  }
  return 0;
}
