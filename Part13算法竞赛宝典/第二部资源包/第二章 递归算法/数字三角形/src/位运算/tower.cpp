//数字三角形－位运算 
#include <stdio.h>			
#include <stdlib.h>		
#define maxn 50             //最大实际不超过31，否则会出错

int a[maxn][maxn],N,total,Max,b;
 
void f()					
{							
  int i,j,t=1<<(N-1),s=0; //t用来取值，s为总和
  for (i=j=0;i<N;i++)     //i,j分别表示行、列 
  {						
    if (b&t) //如果当前位为1则向右
      j++;       
    s+=a[i][j];			//增加 
    t>>=1;				//1向右移一位比较 
  }						
  if (s>Max)
    Max=s;       //找出最大值 
}							

int main()
{
  FILE *in =fopen("tower.in","r");
  FILE *out =fopen("tower.out","w");
  int i,j,all;
  fscanf(in,"%d",&N); 	
  all=1<<(N-1);           //可能的数目 
  for (i=0;i<N;i++)       //输入（从0开始） 
    for (j=0;j<=i;j++) 
      fscanf(in,"%d",&a[i][j]);  
  for (b=0;b<all;b++)     //穷举所有可能 
    f();                //计算当前情况大小 
  fprintf(out,"%d\n",Max);   
  return 0; 				
}							
