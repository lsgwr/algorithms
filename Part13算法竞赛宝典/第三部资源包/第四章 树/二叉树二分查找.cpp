//二叉树二分查找法
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

b_tree create(int *node,int position)//递归建立二叉树
{
  b_tree newnode;
  if(node[position]==0 || position>15)
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

b_tree search(b_tree point,int locate)//二分查找
{
  while(point!=NULL)
  {
    if(point->date==locate)//如找到,则返回地址
      return point;
    else if(point->date>locate)
      point=point->left;//往左子树找
    else
      point=point->right;//往右子树找
  }
  return NULL;//未找到结点,则返回NULL
}

int main()
{
  int locate;
  b_tree root=NULL;
  b_tree point=NULL;
  int node[16]= {0,5,2,9,1,4,7,0,0,0,3,0,6,8,0,0};
  root=create(node,1);
  print(root);
  printf("\n请输入欲查找的结点值:  ");
  scanf("%d",&locate);

  point=search(root,locate);//调用二分查找函数
  if(point!=NULL)
    printf("The value is [%d]\n",point->date);
  else
    printf("Not find!\n");
  return 0;
}

