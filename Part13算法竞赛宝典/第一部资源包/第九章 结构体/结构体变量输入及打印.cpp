//结构体变量输入及打印
# include <iostream>
using namespace std;

struct student
{
  int num;
  char name[20];
  char sex;
  int age;
  float score;

};
struct student a,b;

int main()
{
  cin>>a.num>>a.name>>a.sex>>a.age>>a.score;
  cin>>b.num>>b.name>>b.sex>>b.age>>b.score;
  cout<<a.num<<a.name<<a.sex<<a.age<<a.score<<endl;     
  cout<<b.num<<b.name<<b.sex<<b.age<<b.score<<endl;   
  system("pause");
}
