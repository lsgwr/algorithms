//sscanfÓÃ·¨1 
# include <stdio.h>
int main()
{
  char str[100],str1[100],str2[100];
  gets(str);
  sscanf(str, "%s%s",str1,str2);
  printf("%s  |  %s",str1,str2);
  getchar();
  getchar();
}
