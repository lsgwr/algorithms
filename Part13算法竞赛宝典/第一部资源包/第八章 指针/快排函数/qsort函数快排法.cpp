//qsort函数快排法 
#include <stdio.h> 
#include <stdlib.h> 

int comp(const void *p, const void *q)//不可修改的参数 
{ 
  return (*(int *)p - *(int *)q); //转换两个指针为int后再比较
} 

int comp2(const void *p, const void *q)//不可修改的参数 
{ 
  return (*(int *)q - *(int *)p); //转换两个指针为int后再比较
} 

int main() 
{ 
  int i; 
  int array[]={6,8,2,9,1,0}; 
  qsort(array,6,sizeof(int),comp); //数组名，元素数，元素大小，比较函数名
  for (i=0;i<6;i++) //由小至大排序 
    printf("%d\t", array[i]); 
  printf("\n");  
  
  qsort(array,6,sizeof(int),comp2); //数组名，元素数，元素大小，比较函数名
  for (i=0;i<6;i++) //由大至小排序 
    printf("%d\t", array[i]);     
  getchar();
}
