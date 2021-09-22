//fbi树 
#include <iostream> 
#include <cmath> 
using namespace std; 
char s1[2]="0",s2[2]="1"; 
char s[1200]; 

struct FBI
{ 
  char s; 
  FBI *lchild,*rchild; 
}; 

void showtree(FBI *head)//后序遍历树
{    
  if(head==NULL) 
    return; 
  showtree(head->lchild); 
  showtree(head->rchild); 
  printf("%c",head->s); 
} 

void maketree(FBI *node,char *p,int len)
{ //递归构建二叉树，每次把字符串一份为二作为结点的儿子
  if(len==1)
  { 
    if(strstr(p,s1)&&strstr(p,s2)) 
      node->s='F'; 
    else if(strstr(p,s1)&&!strstr(p,s2)) 
      node->s='B'; 
    else if(!strstr(p,s1)&&strstr(p,s2)) 
      node->s='I'; 
    node->lchild=NULL; 
    node->rchild=NULL; 
    return; 
  } 
  char q[520],*r=new char;  
  FBI *z=new FBI; 
  strncpy(q,p,len/2); 
  r=p; 
  int i=len/2; 
  while(i--) 
    r++;        //将p一分为二 q和r两个字符串 
  if(strstr(q,s1)&&strstr(q,s2))   //判断左儿子的类型 
    z->s='F'; 
  else if(strstr(q,s1)&&!strstr(q,s2)) 
    z->s='B'; 
  else if(!strstr(q,s1)&&strstr(q,s2)) 
    z->s='I'; 
  node->lchild=z; 
  FBI *c=new FBI; 
  if(strstr(r,s1)&&strstr(r,s2))    //判断右儿子的类型 
    c->s='F'; 
  else if(strstr(r,s1)&&!strstr(r,s2)) 
    c->s='B'; 
  else if(!strstr(r,s1)&&strstr(r,s2)) 
    c->s='I'; 
  node->rchild=c; 
  maketree(z,q,len/2);   //递归构建 
  maketree(c,r,len/2); 
} 

int main()
{ 
  freopen("fbi.in","r",stdin);
  freopen("fbi.out","w",stdout);
  int n; 
  while(scanf("%d",&n)!=EOF)
  { 
    scanf("%s",&s); 
    if(strlen(s)==1)
    { 
      if(!strcmp(s,"0")) 
        printf("B\n"); 
      else if(!strcmp(s,"1")) 
        printf("I\n"); 
      continue; 
    } 
    FBI *head=new FBI; 
    char s1[2]="0",s2[2]="1"; 
    if(strstr(s,s1)&&strstr(s,s2)) 
      head->s='F'; 
    else if(strstr(s,s1)&&!strstr(s,s2)) 
      head->s='B'; 
    else if(!strstr(s,s1)&&strstr(s,s2)) 
      head->s='I'; 
    maketree(head,s,(int)pow(2.0,(double)n)); 
    showtree(head); 
    printf("\n"); 
  } 
  return 0; 
} 
