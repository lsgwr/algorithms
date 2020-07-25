//数组结构二叉树递归转链表结构二叉树
#include <bits/stdc++.h>
using namespace std;

struct tree
{
  struct tree *left;
  int date;
  struct tree *right;
};
typedef struct tree treenode;
typedef struct tree *b_tree;
int N;

b_tree create(int *node,int position)//递归建立二叉树
{
  b_tree newnode;
  if(node[position]==0 || position>N)
    return NULL;
  else
  {
    newnode=new treenode;
    newnode->date=node[position];
    newnode->left=create(node,2*position);
    newnode->right=create(node,2*position+1);
    return newnode;
  }
}

void print(b_tree root)//中序遍历
{
  if(root!=NULL)
  {
    print(root->left);
    printf("%d ",root->date);
    print(root->right);
  }
}

int main()
{
  freopen("array2link.in","r",stdin);
  freopen("array2link.out","w",stdout);
  int i=1,temp;
  int node[10000];
  b_tree root=NULL;
  while(cin>>temp)
    node[i++]=temp;
  N=i-1;
  root=create(node,1);
  print(root);
  cout<<endl;
  return 0;
}

