//和谐俱乐部 
#include <iostream>
#include <cstdlib>
using namespace std;
int N;
int a[5][100001];//0维存序号,1维存a,2维存b,3维存最长序列数,4维存下个链接 
int out[100001];//输出排序
 
void quick(int i,int j)//对a[1][n]排序 
{
 int m=i,n=j,temp,k;
 k=a[1][(i+j)/2];  /*选取的中间参照值*/
 while(m<=n)
 {
    while(a[1][m]<k&&m<j)  m++;  /* 从左到右找比k大的元素*/
    while(a[1][n]>k&&n>i)  n--;  /* 从右到左找比k小的元素*/ 
    if(m<=n)
    {               /*若找到且满足条件，则a数组全交换*/
       temp=a[0][m]; a[0][m]=a[0][n]; a[0][n]=temp;
       temp=a[1][m]; a[1][m]=a[1][n]; a[1][n]=temp;
       temp=a[2][m]; a[2][m]=a[2][n]; a[2][n]=temp;       
       m++;
       n--;
    }
 }
 if(m<j)  quick(m,j);  /*运用递归*/
 if(n>i)  quick(i,n);
}

void GetLong()//找最长序列
{
  int i,j;   
  for(i=2;i<=N;i++)
    for(j=1;j<i;j++)//比较前面的序列 
    {
      if(a[1][i]>a[1][j] && a[2][i]>a[2][j] && a[3][j]+1>a[3][i])   
      {
        a[3][i]=a[3][j]+1;//最长序列数加1 
        a[4][i]=j;//保存上个链接 
        if(a[3][i]>a[0][0])
        { 
            a[0][0]=a[3][i];//a[0][0]保存最长序列数 
            a[1][0]=i;//Long保存当前最长序列数所在下标 
        }    
      }
    }
}

void OK()//输出结果
{ int i,t;
  printf("%d\n",a[0][0]);
  t=a[1][0];
  for(i=1;i<=a[0][0];i++)//对最长链排序 
  {      
    out[t]=a[0][t];     
    t=a[4][t]; 
  }
  for(i=1;i<=100001;i++)//输出最长链
  {   
    if (out[i]!=0)
     printf("%d ",out[i]);  
  }    
}

int main()
{
  freopen("Beautiful.in","r",stdin);
  freopen("Beautiful.out","w",stdout);  
  int i;
  scanf("%d",&N);//输入会员数N 
  for(i=1;i<=N;i++)
  {
    a[0][i]=i;//加下标
    a[3][i]=1;//初始最长序列数均为1 
    scanf("%d %d",&a[1][i],&a[2][i]);//获得A,B值 
  }

  quick(1,N);
  GetLong();     
  OK();
  return 0; 
}
