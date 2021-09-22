#include <iostream>
#include <cstdlib>
using namespace std;

void  Delete_Num(char str[],int n,int k)
{
  while(k--)
  {
    int m=0,i=0;
    while(i<n && str[i]<=str[i+1])//¶¨Î»  
      i++;
    for(int j=i+1;j<n;j++)   
     str[j-1]=str[j];
  }
}

int main()
{
  freopen("DeleteNum.in","r",stdin);
  freopen("DeleteNum.out","w",stdout);
  int k;
  char str[300];
  while(scanf("%s%d",str,&k)!=EOF)
  {
    int lenth=strlen(str);
    Delete_Num(str,lenth,k);
    for(int i=0;i<lenth-k;i++)
      printf("%c",str[i]);
    printf("\n");
  }
  return 0;
}
