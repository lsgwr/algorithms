//深度优先搜索 
#include <iostream>
using namespace std;
#define Num 9

struct Node
{
  int vertex;       
  struct Node *next;     
};
typedef struct Node *Graph;
Node Head[Num];//9个顶点 
int visited[Num]={0};//标记是否访问过 

void CreateG(int node1,int node2)//建立邻接列表 
{
  Graph pointer;
  Graph New;
  New=(Graph)malloc(sizeof(struct Node));
  if(New!=NULL)
  {
    New->vertex=node2;
    New->next=NULL;
    pointer=&(Head[node1]);
    while(pointer->next!=NULL)
      pointer=pointer->next;
    pointer->next=New;
  }                      
}

void printG()//打印图 
{
  Graph pointer;
  int i,j;
  for(i=0;i<Num;i++)
  {
    pointer=Head[i].next;
    printf("Head[%d]",i);
    while(pointer!=NULL)
    {
      printf("-> %d ",pointer->vertex);
      pointer=pointer->next;                    
    }                                    
    printf("\n");   
  } 
}

void DFS(int vertex)//深度优先搜索 
{
  Graph pointer;
  visited[vertex]=1;
  printf("[%d]->",vertex);
  pointer=Head[vertex].next;
  while(pointer!=NULL)
  {
    if(visited[pointer->vertex]==0)
      DFS(pointer->vertex);
    pointer=pointer->next;
  }                           
}
     
int main()
{
  int i;
  int node[20][2]={{1,2},{2,1},{1,3},{3,1},{2,4},
                   {4,2},{2,5},{5,2},{3,6},{6,3},
                   {3,7},{7,3},{4,8},{8,4},{5,8},
                   {8,5},{6,8},{8,6},{7,8},{8,7}};
  for(i=0;i<Num;i++)
  {
    Head[i].vertex=i;                  
    Head[i].next=NULL;                
  }   
  for(i=0;i<20;i++)
    CreateG(node[i][0],node[i][1]);  
  printG();
  DFS(1); 
  printf("\n\n\n");
  system("pause"); 
}
