//乒乓球队
#include <bits/stdc++.h>
using namespace std;

//定义链表
struct Link
{
  int data; //存放数据
  Link* next; //指向下一个结点
  Link(int=0);
};

Link::Link(int n)//d定义Link类的一种继承关系
{
  data=n;
  next=NULL;
}

int n1,n2,m,ans=0;
int result[101]; //记录n1中的点匹配的点的编号
bool state [101]; //记录n1中的每个点是否被搜索过
Link *head [101]; //记录n2中的点的邻接结点
Link *last [101]; //邻接表的终止位置记录

//判断能否找到从结点n开始的增广路
bool find(const int boy)
{
  Link* t=head[boy];
  while (t!=NULL) //boy仍有未查找的邻接结点时
  {
    if (!(state[t->data])) //如果邻接点t->data未被查找过
    {
      state[t->data]=true; //标记t->data为已经被找过
      if ((result[t->data]==0) || //如果t->data不属于前一个匹配M
          (find(result[t->data]))) //如t->data匹配到的结点可以寻找到增广路
      {
        result[t->data]=boy; //可更新匹配M',其中n1中的点t->data匹配boy
        return true; //返回匹配成功的标志
      }
    }
    t=t->next; //继续查找下一个n的邻接结点
  }
  return false;
}

int main()
{
  int t1=0, t2=0;
  cin>>n1>>n2>>m;
  for (int i=0; i<m; i++)
  {
    cin>>t1>>t2;
    if (last[t1]==NULL)
      last[t1]=head[t1]=new Link(t2);
    else
      last[t1]=last[t1]->next=new Link(t2);
  }
  for (int i=1; i<=n1; i++)
  {
    memset(state, 0, sizeof(state));
    if (find(i)) ans++;
  }
  cout<<ans<<endl;
  return 0;
}

