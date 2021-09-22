//sscanf用法2
# include <stdio.h>
int main()
{
  char str[100],str1[100],str2[100];
  gets(str);
  sscanf(str, "%4s%s",str1,str2);//请与上一个程序比较看看
  printf("%s  |  %s",str1,str2);
  getchar();
  getchar();
}
