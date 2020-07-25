//计算日期
#include <stdio.h>

int main()
{
  int leap(int year);
  struct
  {
    int year;
    int month;
    int day;
  }date;
  printf("输入日期：");
  scanf("%d%d%d",&date.year,&date.month,&date.day);
  if(date.year>0)
  switch(date.month)
  {
    case 1: if(date.day>0 && date.day<=31)
              printf("你输入的日期是一年之中的第%d天。\n",date.day);
            else printf("日期错误\n");break;
    case 2: if(date.day>0 && date.day<=(leap(date.year)?29:28))
              printf("你输入的日期是一年之中的第%d天。\n",31+ date.day); 
            else printf("日期错误\n");break;
    case 3: if(date.day>0 && date.day<=31)
              printf("你输入的日期是一年之中的第%d天。\n",59+date.day+leap (date.year));
            else printf("日期错误\n");break;
    case 4: if(date.day>0 && date.day<=30)
              printf("你输入的日期是一年之中的第%d天。\n",90+date.day+leap (date.year));
            else printf("日期错误\n");break;
    case 5: if(date.day>0 && date.day<=31)
              printf("你输入的日期是一年之中的第%d天。\n",120+date.day+leap (date.year));
            else printf("日期错误\n");break;
    case 6: if(date.day>0 && date.day<=30)
              printf("你输入的日期是一年之中的第%d天。\n",151+date.day+leap (date.year)); 
            else printf("日期错误\n");break;
    case 7: if(date.day>0 && date.day<=31)
              printf("你输入的日期是一年之中的第%d天。\n",181+date.day+leap (date.year));
            else printf("日期错误\n");break;
    case 8: if(date.day>0 && date.day<=31)
              printf("你输入的日期是一年之中的第%d天。\n",212+date.day+leap (date.year)); 
            else printf("日期错误\n");break;
    case 9: if(date.day>0 && date.day<=30)
              printf("你输入的日期是一年之中的第%d天。\n",243+date.day+leap (date.year));
            else printf("日期错误\n");break;
    case 10: if(date.day>0 && date.day<=31)
               printf("你输入的日期是一年之中的第%d天。\n",273+date.day+leap (date.year)); 
             else printf("日期错误\n");break;
    case 11: if(date.day>0 && date.day<=30)
               printf("你输入的日期是一年之中的第%d天。\n",304+date.day+leap (date.year)); 
             else printf("日期错误\n");break;
    case 12: if(date.day>0 && date.day<=31)
               printf("你输入的日期是一年之中的第%d天。\n",334+date.day+leap (date.year)); 
             else printf("日期错误\n");break;
    default: printf("日期错误\n"); break;
 }
 else
  printf("日期错误\n");getchar();getchar();
}

int leap(int year)
{
  if((year%4==0 && year%100!=0) || year%400==0)
    return 1;
  else
    return 0;
}
