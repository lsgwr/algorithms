//已知前序中序求后序 
#include<iostream>
#include<string>
#define ANSN 10000
using namespace std;

string rx78;//用来记录上例中的那个包含括号和字母的字符串
int final,cho[ANSN+1];//用来跳转的数组

void hwg(string a,string b)
{
  int i,mark,ll,lr,len=a.length();
  if(len>1)//如果不止有一个结点
  {
    if(a[1]==b[len-2])//如果符合ABXXX、XXXBA的形式，就要分类处理
    {
      string p1(a,1,len-1),p2(b,0,len-1);//取出子树
      //可能性1，该树是右子树，先访问根节点
      rx78+='{';
      mark=rx78.length()-1;//记录下左括号的位置
      rx78+=a[0];
      hwg(p1,p2);//打下标记后处理子树
      rx78+='}';
      cho[mark]=rx78.length();//给左括号赋下跳转的位置
      mark=rx78.length()-1;//记录下右括号的位置

      //可能性2，该树是右子树，后访问根节点
      rx78+='{';
      hwg(p1,p2);//同上
      rx78+=a[0];
      rx78+='}';
      cho[rx78.length()-1]=rx78.length();
      cho[mark]=rx78.length();//给两个右括号赋上跳转的位置
    }
    else//如果可以确定左右子树
    {
      for(i=0;i<len-2;i++)
        if(a[1]==b[i])
          break;//先找到左子树的根结点
      ll=i+1;
      lr=len-1-ll;//计算左右子树的长度
      if(ll)
      {
        string part1(a,1,ll),part2(b,0,ll);//取出左子树
        hwg(part1,part2);//递归求左子树
      }
      rx78+=a[0];//中序遍历，根结点在访问左子树后，访问右子树前访问。
      if(lr)
      {
        string part3(a,1+ll,lr),part4(b,ll,lr);//取出右子树
        hwg(part3,part4);//递归求右子树
      }
    }
  }
  else//如果只有一个结点
    rx78+=a[0];
}

void dfs(string have,int pos)
{
  char ty=rx78[pos];
  if(pos>=final)//如果深搜搜到尽头
    cout<<have<<endl;
  else if(ty=='{')//当碰到左括号时
  {
    if(cho[pos]==-1)//如果这个左括号不能跳转，就只能向前走一格
      dfs(have,pos+1);
    else//如果可以跳转
    {
      dfs(have,pos+1);//要么直接向前走一格
      dfs(have,cho[pos]);//要么跳转
    }
  }
  else if(ty=='}')
    dfs(have,cho[pos]);//碰到右括号都跳转
  else
  {
    have+=rx78[pos];
    dfs(have,pos+1);//如果碰到字符就存入，并继续深搜
  }
}

int main()
{
  int i;
  string front,back;
  cin>>front>>back;
  for(i=0;i<=ANSN;i++)
    cho[i]=-1;
  hwg(front,back); 
  final=rx78.length();
  dfs("",0);
  return 0;
}
