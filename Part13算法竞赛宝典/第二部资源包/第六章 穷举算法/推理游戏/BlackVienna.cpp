//推理游戏 
#include<iostream>
using namespace std;
 
const int o = 3,h = 15, q = 5, m=13;//通过定义常数防止栈溢出 
char c[h][3+1],d[o + 1][5+1];//审问内容,实际手牌
char card[o + 1][q+1],eson[m];//可能手牌
int a[h],b[h];//被审问的人,审问答案
int mas[o+1]; 
int v,w;//审问次数
int i,j,p; 
 
int Max(int a,int b)//返回最大值
{
  if(a>b)
    return a;
  else 
    return b;
}
 
int msn(char a1[],char a2[])
{
  int n=0;
  for(i=0;i<5;i++)
  {
    for(j=0;j<3;j++)
    {
      if(a1[i]==a2[j])
        n++;
    }
  }
  return n;
}

int mqn(char jook[][q+1])
{
  int t=0;
  while(t<w &&msn(jook[a[t]],c[t])==b[t])
    t++;
  return t;
}

void men(int r,char l[])
{
  if(r==(18-5))
  { 
    if (card[o][0] == l[r-o])
      return; 
    v = Max(v, mqn(card));
  }
  else
  {
    int j;
    for(j=0;j<=o;j++)
    {
      if((j<o && mas[j]<5) || (j==o && mas[j]<3))
      { 
        card[j][mas[j]]=l[r];
        mas[j]++;
        men(r+1,l);
        mas[j]--; 
      }
    }
  }
}

int main()
{
  int k,t;
  while(scanf("%d",&w)!=EOF && w>0) 
  {
    for(i=0;i<=o;i++)
      scanf("%s",&d[i]);
    for(t=0;t<w;t++)
    {
      scanf("%d",&a[t]);
      a[t]--; 
      scanf("%s",&c[t]);
      scanf("%d",&b[t]);
    }//输入 
    k=h;
    for(p=0;p<o;p++)
    {
      for(int z=0;z<13;z++)
        eson[z]=0;//将数组中元素初始为0
      for(j=0;j<=o;j++)
      {
        if(j!=p)
          strcat(eson,d[j]);//连接两数组 
      }
      strcpy(card[p],d[p]); 
      mas[p] = q; 
      v = 0; 
      men(0,eson);
      memset(card[p],'\0',sizeof(card[p]));//如上方的清0方法 
      mas[p]=0;
      if(v<k)
        k=v;
    }
    if(k<w)
      printf("%d\n",k+1);
    else
      printf("?\n");
  }
  return 0;
}
 

