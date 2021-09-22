//最具亲和力魔法师大赛 
# include <iostream>
using namespace std;

struct person
{
  char name[20];//姓名
  int count;//得票数
}leader[3]={"Mike",0,"John",0,"Smith",0};//初始化候选人

int main()
{
  int i,j;
  char name[20];  //此处的变量name与结构体变量name并不冲突
  for(i=1;i<=10;i++)
  {
    cin>>name;
    for(j=0;j<3;j++)
       if(strcmp(name,leader[j].name)==0)//输入姓名与候选人姓名比较
          leader[j].count++;        //得票数加1
  }
  cout<<endl;
  
  for(i=0;i<3;i++)
      cout<<leader[i].name<<" "<<leader[i].count<<" "; //打印统计结果
}
