//使用scanf函数输入数据
# include <stdio.h> //C语言的标准输入输出头文件，必须要加这一行

int main()
{
  int a;
  float b;
  scanf("%d",&a);
  scanf("%f",&b);
  printf("%d,%f",a,b);
  getchar();
  getchar();
}
