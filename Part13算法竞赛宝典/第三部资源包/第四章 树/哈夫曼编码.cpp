//哈夫曼编码 
#include <iostream>
using namespace std;

#define MAXN 1000
#define MAXLEAF     30
#define MAXNODE    MAXLEAF*2 -1

typedef struct //编码结构体
{
  int bit[100];//保存哈夫曼编码 
  int start;//编码的开始位置 
}HCode; 
       
typedef struct//结点结构体
{
  int weight;
  int parent;
  int lchild;
  int rchild;
}HNode;        

HNode HuffNode[MAXNODE];//定义一个结点结构体数组
HCode HuffCode[MAXLEAF];//定义一个编码结构体数组

void CreatHuffmanTree(HNode HNode[MAXNODE],int n)//构造一颗哈夫曼树
{ 
  int i, j;
  int m1, m2;//构造树过程中两个最小权值结点的权值
  int x1, x2;//构造树过程中两个最小权值结点在数组中的序号
  
  for (i=0; i<n-1; i++)//循环构造 Huffmantree
  {
    m1=m2=10000;//m1、m2中存放两个无父结点且结点权值最小的两个结点
    x1=x2=0;
        
    for (j=0; j<n+i; j++)//找出所有结点中权值最小、无父结点的两个结点，并合并之为一颗二叉树
    {
      if (HNode[j].weight < m1 && HNode[j].parent==-1)
      {
        m2=m1; 
        x2=x1; 
        m1=HNode[j].weight;
        x1=j;
      }
      else if (HNode[j].weight < m2 && HNode[j].parent==-1)
      {
        m2=HNode[j].weight;
        x2=j;
      }
    }
    //设置找到的两个子结点 x1、x2 的父结点信息
    HNode[x1].parent  = n+i;
    HNode[x2].parent  = n+i;
    HNode[n+i].weight = HNode[x1].weight + HNode[x2].weight;
    HNode[n+i].lchild = x1;
    HNode[n+i].rchild = x2;
  }
}

void CreatHuffmanCode(HNode HuffNode[MAXNODE],int n)
{
  HCode cd;//定义一个临时变量来存放求解编码时的信息   
  for (int i=0; i < n; i++)
  {
    cd.start = n-1;
    int c = i;
    int p = HuffNode[c].parent;
    while (p != -1)   /* 父结点存在 */
    {
      if (HuffNode[p].lchild == c)
        cd.bit[cd.start] = 0;
      else
        cd.bit[cd.start] = 1;
      cd.start--;        /* 求编码的低一位 */
      c=p;                    
      p=HuffNode[c].parent;    /* 设置下一循环条件 */
    } 
    for (int j=cd.start+1; j<n; j++)//保存求出的每个叶结点的哈夫曼编码和编码的起始位
    { 
      HuffCode[i].bit[j] = cd.bit[j];
    }
    HuffCode[i].start = cd.start;
 }                  
} 

void init(HNode HNode[],int n)//初始化
{
  for (int i=0; i<2*n-1; i++) 
  {
    HNode[i].weight = 0;
    HNode[i].parent =-1;
    HNode[i].lchild =-1;
    HNode[i].lchild =-1;
  }
  for (int i=0; i<n; i++)
  {
    cout<<"请输入第"<<i<<"个结点的权重";  
    cin>>HNode[i].weight;
  }    
}

int main()
{
  int i, j,n;
  cout<<"请输入字符个数n:\n";
  cin>>n;
  init(HuffNode,n);//初始化 
  CreatHuffmanTree(HuffNode, n);//建立哈夫曼树 
  CreatHuffmanCode(HuffNode, n);//生成哈夫曼编码  
  
  for (i=0; i<n; i++)//输出哈夫曼编码
  {
    cout<<i<<"的哈夫曼编码为:";
    for (j=HuffCode[i].start+1; j < n; j++)
      cout<<HuffCode[i].bit[j];
    cout<<"\n";
  }
  getchar();
  getchar();
  return 0;
}
