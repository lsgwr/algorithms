//复杂表达式处理 
#include<iostream>
#include<string>
#include<iomanip>//横向显示生成的树，头文件 
#include<windows.h>//纵向显示生成的树，头文件 
#define max 1001
using namespace std;

string tree[max];
int qkh(string str[],int i,int *n)//去括号 
{
  string a[max];    
  int j,k=0;
  int sum=0;
  if(i==*n&&str[1]=="(")//判断整个表达式是否被（）包住 
  { 
    for(j=1;j<=i;j++)
      if(str[j]=="("||str[j]==")")//统计括号的位置与方向 
      {
        k++;
        a[k]=str[j];
      }
    if((a[2]=="("&&a[k-1]==")")||k==2)
    //如（1+2）*（3+4）是表达式前后为（），整个表达式却不是在一个括号内，还需分类讨论 
    {
      for(j=1;j<=*n-2;j++)
        str[j]=str[j+1];
      str[*n]="\0";str[*n-1]="\0";
      *n=*n-2;
      return *n+1;
    }
  } 
  for(j=i;j>=0;j--)//如果括号没有覆盖整个 字符串则跳过括号内的内容 
  {
    if(str[j]==")")
      sum++;
    if(str[j]=="(")
      sum--;
    if(sum==0)
      break;
  }
  return j;
}

void zhh(int n,string str2[],int level,int m)
{
  if(m==1)//标记下一个存放的位置、此句为左子树 
    level=2*level;
  if(m==-1)//同上、右子树 
    level=2*level+1;
     
  int l=0,r=0,i,j;
  int a=1,k;
  /*for(i=1;i<=n;i++)
    cout<<str2[i];cout<<endl;*/
  //过程中的子串，可取消注释 
  if(n!=1)
    for(i=n;i>=1;i--)
    {
      if((str2[i]=="*"||str2[i]=="/")&&a==1)
      //a保证此语句运行一次，即括号外的、最右边的*、/号 
        {k=i;a--;}
      if(str2[i]==")")//对括号进行处理 
        {i=qkh(str2,i,&n);continue;}
      else if(str2[i]=="+"||str2[i]=="-")
      //提取最右边括号外的+、-号进行左右子树的分节 
        {l=i-1;r=n-i;break;}
    }
  if(i==0&&a==0&&l==0&&r==0)
  //若括号外无+、-号，则去*、/进行运算 
    {l=k-1;r=n-k;}
  if(l>0)//左子树 
  {
    string part1[l+1];//对左子树赋值 
    for(i=0;i<l+1;i++)
      part1[i]=str2[i];
    zhh(l,part1,level,1);       
  }
  if(r>0)//右子树 
  {
    string part2[r+1];//对右子树赋值 
    for(i=0,j=l+1;i<=r+1,j<=n;i++,j++)
      part2[i]=str2[j];
    zhh(r,part2,level,-1);
  }
  tree[level]=str2[l+1];
}

void qx(int level)//前序遍历 
{
  cout<<tree[level];
  if(tree[2*level]!="\0")
    qx(2*level);
  if(tree[2*level+1]!="\0")
    qx(2*level+1);
}

void hx(int level)//后序遍历 
{
  if(tree[2*level]!="\0")
    hx(2*level);
  if(tree[2*level+1]!="\0")
    hx(2*level+1);
  cout<<tree[level];
}

void draw1(int loo,int level)//画出二叉树 
{
  if(tree[level]!="\0")
  {
    draw1(loo+8,2*level+1);
      cout<<setw(loo)<<tree[level]<<endl;
    draw1(loo+8,2*level);
  }
}
void draw2(int level,int x,int y,int k,int space)
{
   if(tree[level]!="\0")
   {
     HANDLE hOutput;
     COORD location;
     location.X=x;
     location.Y=y;
     hOutput=GetStdHandle(STD_OUTPUT_HANDLE);
     SetConsoleCursorPosition(hOutput,location);
     if(k==1)
       cout<<tree[level]<<"/";
     else if(k==2)
       cout<<"\\"<<tree[level];
     else
      cout<<tree[level];
     draw2(2*level,x-space,y+1,1,space/2);
     draw2(2*level+1,x+space,y+1,2,space/2);
   }
}

int main()
{
  char a;
  string str[max];
  int num=1;
   
  while(1)
  {
    if((a=getchar())=='\n')
      break;
    else
    {
      str[num]=a;
      num++;
    }       
  }
  num--; 
  zhh(num,str,1,0);
  cout<<"中序表达式：";
  for(int i=1;i<=num;i++)
    cout<<str[i];
  cout<<endl;
  cout<<"前序表达式:";
  qx(1);
  cout<<endl;
  cout<<"后序表达式：";
  hx(1);
  cout<<endl;
  //draw1(0,1);//横向显示树 
  cout<<endl;
  draw2(1,40,3,0,20);//纵向显示树  
  getchar();
  getchar();
  return 0;  
}
