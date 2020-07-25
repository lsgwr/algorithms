//一笔画问题
//此程序样例与例图无关 
# include <stdio.h>
int graph[7][7]={0,0,0,0,0,0,0,
                 0,0,1,0,0,1,1,
                 0,1,0,1,1,0,1,
                 0,0,1,0,1,0,0,
                 0,0,1,1,0,1,1,
                 0,1,0,0,1,0,1,
                 0,1,1,0,1,1,0};
                 
int a[7]={0},total=0,edge=0;

int draw(int v)
{
  int i,k=0;
  
  if(total==edge) return 1;
  
  for(i=1;i<7;i++)
    {
      if(graph[v][i]==1)
        {
          k=1;
          graph[v][i]=0;
          graph[i][v]=0;
          edge+=2;
          if(draw(i))
            {
              printf("%3d",i);
              return 1;         
            }
          else
            {
               graph[v][i]=1;
               graph[i][v]=1;
               edge-=2;
               k=0;       
            }
        }         
    }
  if(k==0) return 0;
}

int main()
{
  int i,j,v,k=0; 
  
  for(i=1;i<7;i++)
    {
      for(j=1;j<7;j++)
       if(graph[i][j]==1) a[i]++;             
      
      total+=a[i]; 
      if(a[i]%2==1)
       {
         k++;
         v=i;
       }
    }
  
  if(k>2)
    printf("No solution\n");
  else 
  {
    draw(v); 
    printf("%3d",v);          
  }
  return 0;
}
