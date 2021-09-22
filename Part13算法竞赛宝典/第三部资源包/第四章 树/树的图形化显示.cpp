//树的图形化显示
#include <iostream>
#include <windows.h>
using namespace std;

struct tree
{
  struct tree *left;
  int date;
  struct tree *right;       
};
typedef struct tree treenode;
typedef struct tree *b_tree;

b_tree insert(b_tree root,int node)//插入节点
{
  b_tree newnode;
  b_tree currentnode;
  b_tree parentnode;
  
  newnode=(b_tree)malloc(sizeof(treenode));
  newnode->date=node;
  newnode->left=NULL;
  newnode->right=NULL;
  
  if(root==NULL)    //第一个节点建立 
    return newnode;
  else
  {
    currentnode=root;//存储当前节点 
    while(currentnode!=NULL)
    {
      parentnode=currentnode;//存储父节点 
      if(currentnode->date>node)                      
        currentnode=currentnode->left;//左子树 
      else
        currentnode=currentnode->right; //右子树   
    } 
    if(parentnode->date>node)
      parentnode->left=newnode;
    else
      parentnode->right=newnode;  
  }              
  return root;
}

b_tree create(int *data,int len)//建立二叉树 
{
  int i;
  b_tree root=NULL;
  for(i=1;i<=len;i++)
    root=insert(root,data[i]);
  return root;  
}

void GraphiShow(b_tree root,int x,int y,int k,int space)
{
  if(root!=NULL)
  {
    //此段代码用于定位光标，可另写成函数调用的形式 
    HANDLE hOutput;
    COORD location;
    location.X=x;
    location.Y=y;
    hOutput=GetStdHandle(STD_OUTPUT_HANDLE);//获得屏幕句柄 
    SetConsoleCursorPosition(hOutput,location);//定位光标到坐标（x,y）处 
 
    if(k==1)
      cout<<root->date<<"/";//输出表示左子树   
    else if(k==2)      
      cout<<"\\"<<root->date;//输出表示右子树                
    else 
      cout<<root->date;//输出根结点 
    GraphiShow (root->left,x-space,y+1,1,space/2);
    GraphiShow (root->right,x+space,y+1,2,space/2);              
  }
}


int main()
{
  int n,i;
  b_tree root=NULL;  
  cin>>n;
  int node[n+1];
  for(i=1;i<=n;i++)
    cin>>node[i];
  root=create(node,n);
  GraphiShow (root,40,3,0,20);
  cout<<endl;
  system("pause");
  return 0;
}
