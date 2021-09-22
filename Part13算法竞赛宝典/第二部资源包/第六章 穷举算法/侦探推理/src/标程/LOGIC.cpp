//侦探推理（2003年提高组复赛第三题） 
#include <iostream>
#include <string>
using namespace std;
int m,n,p,lie[20];//lie标记某人是否说谎，如lie[1]=1，则第1人诚实，-1为说谎 
struct
{
  string name;//人名 
  int P[20+1];//说XX是罪犯的标志，如p[1]=1,表示他说第一人是罪犯
  int NotP[20+1];//说XX不是罪犯的标志，要防止一个人的证词先后矛盾
  int day[7+1];//表示他说今天是星期几，如星期一，则day[1]=1,否则为-1 
  int Me;//表示他说自己是不是罪犯，如是，为1，否则为0 
  int NotMe;//要防止一个人的证词先后矛盾，一会说我是罪犯，一会说我不是罪犯 
}student[20+1];//最多20个人 

int IsHe(int k,int g)//检查第K个人是否说别人是罪犯 
{
  int i;
  for(i=1;i<=m;i++)
    if(i!=g && student[k].P[i]==1)//如果说无罪的人有罪，则说谎 
      return 0;
  if(student[k].P[g]==1)      
    return 1;
  return -1;//不作处理      
}

int NotHe(int k,int g)//检查第K个人是否说别人不是罪犯 
{
  int i;
  if(student[k].NotP[g]==1)//明明是罪犯却说不是的，说谎 
    return 0;  
  for(i=1;i<=m;i++)
    if(i!=g && student[k].NotP[i]==1)//如果说别人不是罪犯的，诚实 
      return 1;  
  return -1;    
}

int Today(int k,int day)//检查第K个人今天是星期几是否说谎 
{
  for(int i=1;i<=7;i++)
    if(i!=day && student[k].day[i]==1)//说今天是别的星期的，说谎 
      return 0;
  if(student[k].day[day]==1)//说对星期的，诚实 
    return 1;
  return -1;//不作处理     
}

int err(int k)//无此判断也可过全部数据，但逻辑应更严密 
{
  int Num=0,i;  
  for(i=1;i<=m;i++)  //指好几人为罪犯的，说谎
    if(student[k].P[i]==1)
     Num++;
  if(Num>1)
    return 0; 
  Num=0;    
  for(i=1;i<=7;i++)  //指好几天为星期数的，说谎
    if(student[k].day[i]==1)
     Num++;
  if(Num>1)
    return 0;
  return 1;    
}

int judge(int g,int day)//参数为罪犯，星期数 
{
  int j,k,lieNum;
  memset(lie,0,sizeof(lie));//注意数组要重置为0 
  for(k=1;k<=m;k++)//设G是罪犯
  { 
    if(err(k)==0)//指好几人为罪犯的，说谎，指好几天为星期数的，说谎 
    { 
      if(lie[k]==1)
        return 0;  
      else
        lie[k]=-1;
    }
    if(student[k].Me==1 && k==g)//罪犯k亲自承认
    {  
      if (lie[k]==-1)
        return 0;//又说真话又说假话，矛盾退出
      else
        lie[k]=1;//标记本人诚实
    }
    if(student[k].NotMe==1 && k==g)//罪犯k自己不承认是罪犯
    {
      if(lie[k]==1)
        return 0;//又说真话又说假话，矛盾退出 
      else     
        lie[k]=-1;//说谎 
    } 
    if(student[k].Me==1 && k!=g)//不是罪犯K的别人承认自己是罪犯的 
    {
      if(lie[k]==1)
        return 0;
      else
        lie[k]=-1;     
    }
    if(student[k].NotMe==1 && k!=g)//不是罪犯K的别人不承认自己是罪犯的 
    {
      if(lie[k]==-1)
        return 0;
      else
        lie[k]=1;       
    }
    if(student[k].P[g]==1)//罪犯K或别人说他是罪犯
    {
      if(lie[k]==-1) 
        return 0;
      else
        lie[k]=1;//别人是诚实的
    }    
    if(student[k].NotP[g]==1)//罪犯K或别人说他不是罪犯
    {
      if(lie[k]==1)
        return 0;
      else  
        lie[k]=-1;//说谎
    }     
    if(IsHe(k,g)==0)//罪犯K或别人说别人是罪犯的,则说谎 
    {
      if(lie[k]==1)
        return 0;
      else 
        lie[k]=-1;      
    } 
    if(NotHe(k,g)==1)//罪犯K或别人说别人不是罪犯的,诚实 
    {
      if(lie[k]==-1)
        return 0;
      else 
        lie[k]=1;      
    }
    if(Today(k,day)==0)//说星期数谎的
    {
      if(lie[k]==1)
        return 0;
      else 
        lie[k]=-1;           
    }   
    if(Today(k,day)==1)//说对星期数的
    {
      if(lie[k]==-1)
        return 0;
      else 
        lie[k]=1;           
    }     
  } 
  lieNum=0;  
  for(j=1;j<=m;j++)//统计说谎人数 
    if(lie[j]==-1)
     lieNum++;
  if(lieNum>n)//说谎人数超过限定，矛盾，退出 
    return 0;  
  
  lieNum=0;   
  for(j=1;j<=m;j++)//统计说真话人数 
    if(lie[j]==1)
     lieNum++;
  if(lieNum>m-n)//说真话人数超过限定，矛盾，退出 
    return 0;
    
  return 1;//如果都符合条件了，说明这可能是一个正确解，返回1 
}

int main()
{
  freopen("LOGIC.in","r",stdin);
  freopen("LOGIC.out","w",stdout);
  int i,j,index,BadManNum=0;
  string temp,BadMan;
  char word[250];
  cin>>m>>n>>p;
  for(i=1;i<=m;i++)//读取姓名      
    cin>>student[i].name;
  gets(word);//只有加这一句，才能正确读证词 
  for(i=1;i<=p;i++)//读入并处理证词 
  {
    string temp=gets(word);
    int pos=temp.find(":",0);
    string t1(temp,0,pos);

    for(j=1;j<=m;j++)//确定是哪个人说的话 
      if(student[j].name==t1)
        index=j;
    temp.erase(0,pos+2);//删除":"前的名字
    if(temp=="I am guilty.")
      student[index].Me=1;
    else if(temp=="I am not guilty.")
      student[index].NotMe=1;
    else if(temp=="Today is Monday.")
      student[index].day[1]=1;  
    else if(temp=="Today is Tuesday.")
      student[index].day[2]=1; 
    else if(temp=="Today is Wednesday.")
      student[index].day[3]=1; 
    else if(temp=="Today is Thursday.")
      student[index].day[4]=1; 
    else if(temp=="Today is Friday.")
      student[index].day[5]=1; 
    else if(temp=="Today is Saturday.")
      student[index].day[6]=1;   
    else if(temp=="Today is Sunday.")
      student[index].day[7]=1;
    else
    {
      for(j=1;j<=m;j++)
      {
        t1=student[j].name+" is guilty.";
        if(temp==t1)
          student[index].P[j]=1;
      }   
      for(j=1;j<=m;j++)
      {
        t1=student[j].name+" is not guilty.";
        if(temp==t1)
          student[index].NotP[j]=1;
      }        
    }                         
  }//预处理结束,此处应验证是否输入正确，可自编一个输出函数检验 

  for(i=1;i<=m;i++)//穷举可能的罪犯
    for(j=1;j<=7;j++)//穷举可能的星期数
    {
      if(judge(i,j)==0)//如果证词有矛盾 
        continue;
      else if(judge(i,j)==1)//如果证词都成立 
      {
        if(BadMan!=student[i].name)//陷阱！！！因为会重复证明某人是罪犯的 
          BadManNum++;//罪犯人数加1 
        BadMan=student[i].name;   
      }                                     
    }
  if(BadManNum==1)
    cout<<BadMan<<endl;
  else if(BadManNum>1)
    cout<<"Cannot Determine"<<endl;
  else
    cout<<"Impossible"<<endl; 
  return 0;
}
