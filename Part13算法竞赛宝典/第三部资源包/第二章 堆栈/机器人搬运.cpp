//机器人顺序放8块积木 
#include <stdio.h>

int a[4]={1,4,3,2},b[4]={5,8,7,6};//初始柱 
int c[2],d[2],e[2],f[2];//过渡柱 
int A[4],B[4];//结果柱 
int X[4]={1,2,3,4},Y[4]={5,6,7,8};//对比柱 
int aa,bb,cc,dd,ee,ff,AA,BB,XX,YY;//柱子们的指针 
int STEP;//统计步数 

int OK()//通过目标盘和参考盘对比，判断是否成功 
{
  int i;
  for(i=0;i<=3;i++)
    if(X[i]!=A[i])
      return 0;
  for(i=0;i<=3;i++)
    if(Y[i]!=B[i])
      return 0;  
  return 1;      
}

void out()//调试用，非正式程序 
{
  int i; 
  printf("a: ");  
  for(i=0;i<=3;i++)
   printf("%2d ",a[i]);
  printf("  ");
  
  printf("b: ");  
  for(i=0;i<=3;i++)
   printf("%2d ",b[i]);
  printf("    ");  
  
  printf("     c: ");  
  for(i=0;i<=1;i++)
   printf("%d ",c[i]);
  printf("  ");
  
  printf("d: ");  
  for(i=0;i<=1;i++)
   printf("%d ",d[i]);
  printf("  ");  

  printf("e: ");  
  for(i=0;i<=1;i++)
   printf("%d ",e[i]);
  printf(" ");  
  
  printf("f: ");  
  for(i=0;i<=1;i++)
   printf("%d ",f[i]);
  printf("\n");
  
  printf("A: ");  
  for(i=0;i<=3;i++)
   printf("%2d ",A[i]);
  printf("  ");
  
  printf("B: ");  
  for(i=0;i<=3;i++)
   printf("%2d ",B[i]);
  //printf("\n");  
  /* 
  printf("a:%d=%d,b:%d=%d  ",aa,a[aa],bb,b[bb]);
  printf("  c:%d=%d d:%d=%d ",cc,c[cc],dd,d[dd]);
  printf("e:%d=%d f:%d=%d   ",ee,e[ee],ff,f[ff]);
  printf("  A:%d=%d,B:%d=%d  ",AA,A[AA],BB,B[BB]);
  printf("  X:%d=%d,Y:%d=%d\n\n",XX,X[XX],YY,Y[YY]);  */
}

void step(char x,char y)//统计步数并打印步数，实际移动，可用此作接口 
{
   ++STEP;
   printf("%c->%c\n",x,y);  
}

void move(char W)//移动到过渡 
{
  if(W=='a')//如果是移动a柱 
  {
    if(cc==-1)//如果c柱为空，则放c柱 
    {
      step('a','c');          
      c[++cc]=a[aa];  
      a[aa--]=-1;
    } 
    else if(cc==0 && a[aa]<c[cc])//如果c柱已有一个，并且待放的比这个要小 
    {
      step('a','c'); //则再放一个，保证上面的一定比下面的小，以防止卡死 
      c[++cc]=a[aa];  
      a[aa--]=-1;
    }   
    else if(ee==-1)//如果e柱为空，则放  
    {
      step('a','e');  
      e[++ee]=a[aa];  
      a[aa--]=-1;
    }
    else if(ee==0 && a[aa]<e[ee])//如果e柱已有一个，则要保证待少的要小 
    {
      step('a','e');  
      e[++ee]=a[aa];  
      a[aa--]=-1;
    }    
    else if(dd==-1)//如果推荐的过渡柱都不能放，则只能借用另两个柱子    
    {
      step('a','d');  
      d[++dd]=a[aa];  
      a[aa--]=-1;
    }
    else if(ff==-1)
    {
      step('a','f');  
      f[++ff]=a[aa];  
      a[aa--]=-1;
    }   
  }
  if(W=='b')//同上 
  {
    if(dd==-1)
    {
      step('b','d');  
      d[++dd]=b[bb];   
      b[bb--]=-1;
    } 
    else if(dd==0 && b[bb]<d[dd])  
    {
      step('b','d');  
      d[++dd]=b[bb];   
      b[bb--]=-1;
    } 
    else if(ff==-1)
    {
      step('b','f');  
      f[++ff]=b[bb];  
      b[bb--]=-1;
    }
    else if(ff==0 && b[bb]<f[ff])
    {
      step('b','f');  
      f[++ff]=b[bb];  
      b[bb--]=-1;
    }   
    else if(cc==-1 )
    {
      step('b','c');  
      c[++cc]=b[bb];  
      b[bb--]=-1;
    }              
    else if(ee==-1 )
    {
      step('b','e');  
      e[++ee]=b[bb];  
      b[bb--]=-1;
    } 
  }     
}


int main()
{
  int i;

  /*for(i=0;i<=3;i++)//输入初始盘 
    scanf("%d",&a[i]);
  for(i=0;i<=3;i++)
    scanf("%d",&b[i]);*/
    
  //for(i=0;i<=3;i++)//输入目标参考盘 
  //  X[i]=i+1;//此处默认1,2,3,4,5,6,7,8 
  //  scanf("%d",&X[i]);
  //for(i=0;i<=3;i++)
  //  Y[i]=i+5;
  //  scanf("%d",&Y[i]);
  XX=0;YY=0;//比较柱指针指向最底层 
  aa=3;bb=3;//指针指向3 
  cc=-1,dd=-1,ee=-1,ff=-1,AA=-1,BB=-1;//指针初始化    
  while(!OK())//当没有成功时,摆放策略是能放就放，不能就暂移到过渡柱 
  {                          
    if(a[aa]==X[XX] && aa>=0 && XX<=3)//a柱，后面的并列条件是防止指针越界 
    {
      step('a','A');
      A[++AA]=a[aa];
      a[aa--]=-1;
      XX++;
    }
    else if(a[aa]==Y[YY] && aa>=0 && YY<=3)//必须要防止指针越界，否则会错 
    {
      step('a','B');
      B[++BB]=a[aa];
      a[aa--]=-1;
      YY++;         
    }                                  
    else if(b[bb]==X[XX] && bb>=0 && XX<=3)//b柱 
    {
      step('b','A');
      A[++AA]=b[bb];
      b[bb--]=-1;
      XX++;         
    } 
    else if(b[bb]==Y[YY] && bb>=0 && YY<=3)
    {
      step('b','B');
      B[++BB]=b[bb];
      b[bb--]=-1;
      YY++;         
    } 
    else if(c[cc]==X[XX] && cc>=0 && cc<=1 && XX<=3)//c柱 
    {
      step('c','A');
      A[++AA]=c[cc];
      c[cc--]=-1;
      XX++;         
    } 
    else if(c[cc]==Y[YY] && cc>=0 && cc<=1 && YY<=3)
    {
      step('c','B');
      B[++BB]=c[cc];
      c[cc--]=-1;
      YY++;         
    }  
    else if(d[dd]==X[XX] && dd>=0 && dd<=1 && XX<=3)//d柱 
    {
      step('d','A');
      A[++AA]=d[dd];
      d[dd--]=-1;
      XX++;         
    } 
    else if(d[dd]==Y[YY] && dd>=0 && dd<=1 && YY<=3)
    {
      step('d','B');
      B[++BB]=d[dd];
      d[dd--]=-1;
      YY++;         
    }            
    else if(e[ee]==X[XX] && ee>=0 && ee<=1 && XX<=3)//e柱 
    {
      step('e','A');
      A[++AA]=e[ee];
      e[ee--]=-1;
      XX++;         
    } 
    else if(e[ee]==Y[YY] && ee>=0 && ee<=1 && YY<=3)
    {
      step('e','B');
      B[++BB]=e[ee];
      e[ee--]=-1;
      YY++;         
    }            
    else if(f[ff]==X[XX] && ff>=0 && ff<=1 && XX<=3)//f柱 
    {
      step('f','A');
      A[++AA]=f[ff];
      f[ff--]=-1;
      XX++;         
    } 
    else if(f[ff]==Y[YY] && ff>=0 && ff<=1 && YY<=3)
    {
      step('f','B');
      B[++BB]=f[ff];
      f[ff--]=-1;
      YY++;         
    }
    else//如果不能直接放，就暂移到过渡柱上 
    {
      int u,v;
      if(aa<0)//如果a柱已经空了 
        move('b');//那么就移b柱 
      else if(bb<0)//如果b柱已经空了 
        move('a');//那么就移a柱 
      else//找到最容易移的柱子，即目标盘最靠上的柱子 
      {
        for(u=aa;u>=0;u--)
          if(a[u]==X[XX] || a[u]==Y[YY])
            break;
        for(v=bb;v>=0;v--)
          if(b[v]==X[XX] || b[v]==Y[YY])
            break;    
        if(u>v)
          move('a');
        else
          move('b');     
      }                      
    }   
    out(); 
    getchar();     
  }  
  printf("Use %d steps,OK!",STEP);
  getchar();
  return 0;
}
