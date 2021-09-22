//一个简单的二叉查找树的例子,用数组模拟二叉树的结构,使用栈优化
#include<iostream>
#define MAX_TREE_NODE 1000000 /*树中最多节点数*/
using namespace std;

/*data为数据lc,rc为节点的左右孩子*/
int data[MAX_TREE_NODE],p[MAX_TREE_NODE],lc[MAX_TREE_NODE],rc[MAX_TREE_NODE];
int root = -1;

/*用于提供数组空位的栈*/
int stack[MAX_TREE_NODE];
int top = 0;

/*搜索树上值为k的节点，并返回其在数组中的位置，如果不存在则返回-1*/
int tree_search(int x,int k)
{
  /*这里使用较快的迭代方式*/
  while(x != -1 and data[x] != k)
    {
      if(k < data[x])
	x = lc[x];
      else
	x = rc[x];
    }
  return x;
}

/*在树中插入一个节点z，这个节点在pre_insert()过程中已经赋值*/
void tree_insert(int z)
{
  /*insert node*/
  int x = root, y = -1;
  while(x != -1)
    {
      y = x;
      if(data[z] < data[x])
	x = lc[x];
      else
	x = rc[x];
    }  

  p[z] = y;

  if(y == -1)
    root = z;
  else if (data[z] < data[y])
    lc[y] = z;
  else
    rc[y] = z;

  return ;
}

/*在数组中找到一个空位并且给这个节点赋值，将其插入到树中*/
void pre_insert(int ins_data)
{
  int z;
  z = stack[top++];
  data[z] = ins_data;
  tree_insert(z);
  return ;
}

/*找树上的最小值*/
int tree_minimum(int x)
{
  while(lc[x] != -1 )
    x = lc[x];
  return x;
}

/*求某节点的后继*/
int tree_successor(int x)
{
  /*若该节点存在右子树，则寻找其右子树最左节点*/
  if(rc[x] != -1)
    return tree_minimum(rc[x]);
  /*否则则查找其最低祖先，查找直到遇到某个节点是其父节点的左孩子节点为止*/
  int y = p[x];
  while(y != -1 and x == rc[y])
    {
      x = y;
      y = p[y];
    }
  return y;
}

/*删除数组z位置上的节点，这个节点在pre_delete()过程中已经确定了*/
void tree_delete(int z)
{
  int x,y;
  /*如果这个节点至多有一个孩子，则删除该节点*/
  if(lc[z] == -1 or rc[z] == -1)
    y = z;
  else/*否则则寻找其后继*/
    y = tree_successor(z);
  /*将x指向y的左孩子或右孩子(如果有的话)*/
  if(lc[y] != -1)
    x = lc[y];
  else
    x = rc[y];

  /*如果y有孩子，那么则将在上面步骤中指定过的孩子的父节点指向y的父节点*/
  if(x != -1)
    p[x] = p[y];
  
  if(p[y] == -1)/*删除的节点为根节点*/
    root = x;
  else if(y == lc[p[y]])/*删除的节点y为y的左孩子*/
    lc[p[y]] = x;
  else/*删除的节点y为y的右孩子*/
    rc[p[y]] = x;

  if(y != z)/*将y的值赋给z*/
    data[z] = data[y];
  
  /*delete node y*/
  data[y] = -1;
  lc[y] = -1;
  rc[y] = -1;
  p[y] = -1;
  stack[--top] = y;
  return ;
}

/*删除之前的操作，在树中找到删除节点所在的位置*/
void pre_delete(int data)
{
  int k;
  k = tree_search(root,data);
  if(k != -1)
    tree_delete(k);
  return ;
}

/*初始化所有节点，默认空值为-1*/
void init()
{
  int i;
  for(i = 0 ;i < MAX_TREE_NODE; ++ i)
    {
      data[i] = rc[i] = lc[i] = p[i] = -1;
      stack[i] = i;
    }
  return ;
}

/*以中序遍历都方式显示二叉树中所有的节点及每个节点的信息*/
void display(int x)
{
  if(x == -1) return ;
  if(lc[x] != -1)
    display(lc[x]);
  cout<<x<<" value : "<<data[x]<<" left: "<<lc[x]<<" right: "<<rc[x]<<" parent : "<<p[x]<<"\n";
  if(rc[x] != -1)
    display(rc[x]);
  return ;
}

/*演示部分*/
int main()
{
  int ins,tmp;

  cout<<"please insert a instruction:\n";
  cout<<"1.insert a value\n";
  cout<<"2.delete a value\n";
  cout<<"3.display the tree in middle order\n";

  init();

  while(cin>>ins)
    {
      //cout<<"top : "<<top<<" val : "<<stack[top]<<"\n";
      switch(ins)
	{
	case 1:/*输入1，插入一个值*/
	  cin>>tmp;
	  cout<<"please input insert data \n";
	  pre_insert(tmp);
	  display(root);
	  break;

	case 2:/*输入2，删除一个值，输入的删除值*/
	  cin>>tmp;
	  cout<<"please input the data need to delete\n";
	  pre_delete(tmp);
	  display(root);
	  break;

	case 3:/*显示二叉树中的所有节点*/
	  display(root);
	  break;

	default :
	  return 0;
	}
    }
  return 0;
}
