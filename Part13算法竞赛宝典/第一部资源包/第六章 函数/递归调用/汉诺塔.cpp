//汉诺塔
# include <iostream>
using namespace std;
/*将n个金片从one座借助two座，移到three座*/
void hanoi(int n,char one,char two,char three)
{     
  if(n==1)                            //当盘片只剩1个时 
   printf("%c->%c ",one,three);     //移动金片从one到three 
  else
  {
    hanoi(n-1,one,three,two);         //递归调用,金片数-1,三个针换位置 
    printf("%c->%c ",one,three);    //移动金片从one到three 
    hanoi(n-1,two,one,three);        //递归调用,金片数-1,三个针换位置
  }
}

int main()
{
  int m;                   //金片数 
  printf("请输入金片数");
  scanf("%d",&m);
  hanoi(m,'A','B','C');
}
