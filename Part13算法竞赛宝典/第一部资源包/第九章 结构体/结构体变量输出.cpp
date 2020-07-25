# include <iostream>
using namespace std;

struct student
{
  int num;
  char name[20];
  char sex;
  int age;
  float score;
  char addr[50];
};
struct student stu[2]={{10001,"张琪曼",'M',16,95.0,"北京路134号"},
 {10002,"楚继光",'F',16,92,"团结路66号"}};//结束为分号
 
int main()
{
  cout<<stu[0].num<<stu[0].name<<stu[0].sex<<stu[0].age<<endl;     
  cout<<stu[1].num<<stu[1].name<<stu[1].sex<<stu[1].age<<endl;
}
