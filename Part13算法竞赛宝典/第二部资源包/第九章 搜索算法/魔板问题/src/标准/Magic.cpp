//魔板问题　－宽搜 
#include<iostream>
#include<fstream>
using namespace std;
bool have[45000]={0};   //存储某状态是否存在 
char did[45000]={0};    //存储某一步的操作
int bfs[45000]={0},last[45000]={0},cost[45000]={0};
//分别存储当前状态，上一个状态的序号，总共进行了多少次操作 
int s[8]={1,2,3,4,8,7,6,5},ans=0,enter=0,now=0,len=0,f=0,temp=0;
/* s数组表示魔板的状态，ans表示目标状态，enter是为了保证输出每60个一行，now是目前搜索到的序号，
len是目前总共状态，f表示答案的序号 */ 

int cantor()//用康托展开式压缩状态
{
  int a=0,i=0,j=0,b=0;
  for(i=7;i>=1;--i)
  {
    b=s[i]-1;
    for(j=7;j>i;--j)
      if(s[j]<s[i])
        --b;
    a+=b;
    a*=i;
  }
  return a;
}

int change()//把状态压缩成8位的数字 
{
  int a=0,i=0;
  for(i=0;i<=7;++i)
    a=a*10+s[i];
  return a;
}

void turn(int num)//把数字解压 
{
  int i=0;
  for(i=7;i>=0;--i)
  {
    s[i]=num%10;
    num/=10;
  }
  return;
}

void go(char tmp)
{
  ++len;
  bfs[len]=change();
  temp=cantor();
  if(have[temp])
    --len;
  else
  {
    have[temp]=1;
    cost[len]=cost[now]+1;
    last[len]=now;
    did[len]=tmp;
    if(bfs[len]==ans)
    f=len;
  }
} 

void go1()//操作A 
{
  int i=0;
  for(i=0;i<4;++i)//进行操作 
  {
    temp=s[i],s[i]=s[i+4],s[i+4]=temp;
  }
  go('A'); //尝试添加 
  for(i=0;i<4;++i)         //还原 
  {
    temp=s[i],s[i]=s[i+4],s[i+4]=temp;
  }
}

void go2()   //操作B 
{
  temp=s[3],s[3]=s[2],s[2]=s[1],s[1]=s[0],s[0]=temp;//进行操作 
  temp=s[7],s[7]=s[6],s[6]=s[5],s[5]=s[4],s[4]=temp;//尝试添加 
  go('B');
  temp=s[0],s[0]=s[1],s[1]=s[2],s[2]=s[3],s[3]=temp;//还原 
  temp=s[4],s[4]=s[5],s[5]=s[6],s[6]=s[7],s[7]=temp;
}

void go3()  //操作c 
{
  temp=s[1],s[1]=s[5],s[5]=s[6],s[6]=s[2],s[2]=temp;//进行操作
  go('C');                                          //尝试添加 
  temp=s[2],s[2]=s[6],s[6]=s[5],s[5]=s[1],s[1]=temp;//还原 
}

void out(int num)   //输出 
{
  if(num!=1)
  {
    out(last[num]);
    cout<<did[num];
    ++enter;
    if(enter%60==0)
      cout<<"\n";    //保证每行最多60个字符 
  }
}

int main()
{
  freopen("Magic.in","r",stdin);
  freopen("Magic.out","w",stdout);  
  int i=0,j=0;
  have[cantor()]=1;  //确定初始状态 
  bfs[1]=12348765;
  now=1;len=1;
  cin>>s[0]>>s[1]>>s[2]>>s[3]>>s[7]>>s[6]>>s[5]>>s[4];//保持输入顺序的正确 
  ans=change();
  if(ans==bfs[1])
    f=1;
  while(f==0 && now<=len)//宽度优先搜索 
  {
    turn(bfs[now]);
    go1();
    if(f==0)
      go2();
    if(f==0)go3();
      ++now;
  }
  if(f==0)
    cout<<-1<<"\n";
  else
  {
    cout<<cost[f]<<"\n";
    out(f);
    if(enter%60!=0)
      cout<<"\n";  //保证最后一行有回车 
  }
  return 0;
}
