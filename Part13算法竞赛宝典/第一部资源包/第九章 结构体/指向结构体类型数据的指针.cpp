//指向结构体类型数据的指针 
# include <iostream>
using namespace std;

int main()
{
  struct student
  {
    int num;
    char name[20];
    char sex;
    float score;
  };
  struct student stu;
  struct student *p;
  p=&stu;
  stu.num=10001;
  strcpy(stu.name,"Mike");//注意此处赋值的方式
  stu.sex='M';
  stu.score=90.5;
  cout<<stu.num<<stu.name<<stu.sex<<stu.score<<endl;     
  cout<<(*p).num<<(*p).name<<(*p).sex<<(*p).score<<endl;  
}
