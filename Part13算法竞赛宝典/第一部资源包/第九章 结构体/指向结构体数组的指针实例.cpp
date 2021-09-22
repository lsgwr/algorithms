//指向结构体数组的指针实例 
# include <iostream>
using namespace std;

struct student
{
  int num;
  char name[20];
  char sex;
  float score;
};
struct student stu[2]={{10001,"张琪曼",'M',95.0},
 {10002,"楚继光",'F',92}};//结束为分号

int main()
{
 struct student *p;
 for(p=stu;p<stu+2;p++)//p为指向结构体的指针，自身加１即指向下一个结构体元素
  cout<<p->num<<p->name<<p->sex<<p->score<<endl;     
}
