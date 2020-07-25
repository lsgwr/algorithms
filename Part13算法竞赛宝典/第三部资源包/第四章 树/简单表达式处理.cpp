//简单表达式处理
#include<iostream>
using namespace std;

typedef struct node
{
  char data;
  struct node *lchild;
  struct node *rchild;
}BTNode;

BTNode *CRTree(char s[],int i,int j)
{
  int k,posi,plus=0;
  BTNode *p;
  if(i==j)//当只有一个数字时 
  {
    p=(BTNode *)malloc(sizeof(BTNode));
    p->data=s[i];
    p->lchild=NULL;
    p->rchild=NULL;
    return p;
  }
  for(k=i;k<=j;k++)
  {
    if(s[k]=='+'||s[k]=='-')
    {
      plus++;
      posi=k;
    }
  }
  if(plus==0)
    for(k=i;k<=j;k++)
      if(s[k]=='*'||s[k]=='/')
      {
         plus++;
         posi=k;
      }
    
  if(plus!=0)
  {
    p=(BTNode *)malloc(sizeof(BTNode));
    p->data=s[posi];
    p->lchild=CRTree(s,i,posi-1);
    p->rchild=CRTree(s,posi+1,j);
    return p;
  }
}

void DispPre(BTNode *T)  //打印前序表达式 
{
  if(T!=NULL)
  {
    printf("%c ",T->data);
    DispPre(T->lchild);
    DispPre(T->rchild);
  }
}

void DispIn(BTNode *T)  //打印中序表达式 
{
  if(T!=NULL)
  {
    DispIn(T->lchild);
    printf("%c ",T->data);
    DispIn(T->rchild);
  }
}

void DispPost(BTNode *T)  //打印后序表达式 
{
  if(T!=NULL)
  {
    DispPost(T->lchild);
    DispPost(T->rchild);
    printf("%c ",T->data);
  }
}

int main()
{
  char s[20];
  BTNode *T;
  gets(s);
  T=CRTree(s,0,strlen(s)-1);
  printf("\n前序表达式为:\n");
  DispPre(T);
  printf("\n中序表达式为:\n");
  DispIn(T);
  printf("\n后序表达式为:\n");
  DispPost(T);
  getchar();
  getchar();
  return 0;
}
