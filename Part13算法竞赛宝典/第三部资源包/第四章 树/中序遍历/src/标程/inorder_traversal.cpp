/*
程序名称：二叉树中序遍历 
*/ 
#include <iostream>
using namespace std;

struct tree
{
  struct tree *left;
  int date;
  struct tree *right;       
};
typedef struct tree treenode;
typedef struct tree *b_tree;

b_tree insert(b_tree root,int node)
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

void print(b_tree root)//前序遍历 
{
  if(root!=NULL)
  {
    print(root->left);
    cout<<root->date<<' ';
    print(root->right);              
  }
}

int main()
{
  freopen("inorder_traversal.in","r",stdin);
  freopen("inorder_traversal.out","w",stdout);
  int n,i;
  b_tree root=NULL;  
  cin>>n;
  int node[n+1];
  for(i=1;i<=n;i++)
    cin>>node[i];
  root=create(node,n);
  print(root);
  cout<<endl;
  return 0;
}

