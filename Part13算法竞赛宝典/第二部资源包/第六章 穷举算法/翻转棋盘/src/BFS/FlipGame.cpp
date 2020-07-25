//翻转棋盘-BFS 
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#define N 1000000
using namespace std;

int que[N];//宽度优先搜索中用到的队列 
int flag[65536];//flag[i]=1即i状态已出现过 
int step[65536];//step[i]即到i状态的步数 
int front=0,rear=0;

int flip(int state,int pos)//翻转pos及其周围位置
{
   state^=(1<<pos);
   if((pos-4)>=0)   state^=(1<<(pos-4));//翻转上方 
   if((pos+4)<=15)  state^=(1<<(pos+4));//翻转下方 
   if(pos%4!=0)     state^=(1<<(pos-1));//翻转左方 
   if(pos%4!=3)     state^=(1<<(pos+1));//翻转右方 
   return state;
}

int bfs()//广搜
{
  int OK=0,state,temp; 
  while(front<rear)//当队列不为空 
  {                                     
    state=que[front++];//取出队首状态 
    for(int i=0;i<16;i++) 
    {                                   
      temp=flip(state,i);//枚举当前状态下，翻转16个棋子后的状态
      if(temp==0||temp==65535)//若全0或全1
      {            
        OK=1;
        flag[temp]=1;
        step[temp]=step[state]+1;
        break;
      } 
      else if(!flag[temp])//若该状态没有出现过 
      {
        que[rear++]=temp;
        flag[temp]=1;//保存该状态 
        step[temp]=step[state]+1;  
      }
     } 
     if(OK)  
       break;               
   } 
   return OK;
} 

int main()
{
  freopen("FlipGame.in","r",stdin);
  freopen("FlipGame.out","w",stdout);
  int i,j;
  int state=0;
  char s[5];
  for(i=0;i<4;i++)
  {
    scanf("%s",s);
    for(j=0;j<4;j++)
    {
      if(s[j]=='b')//令'b'=1,'w'=0,当该位为0时，不需加 
        state|=(1<<(i*4+j));//该位为1时，state+=1^位置              
    }
  }
  if(state==0||state==65535)//或全白或全黑  
    printf("0\n");
  else
  {
    que[rear++]=state;//初始状态入队列 
    flag[state]=1;//表示该状态已出现过 
    memset(step,0,sizeof(step));
    if(bfs())//广度优先搜索     
      printf("%d\n",flag[0]==1?step[0]:step[65535]);        
    else            
      printf("Impossible\n");
  }
  return 0;
}
