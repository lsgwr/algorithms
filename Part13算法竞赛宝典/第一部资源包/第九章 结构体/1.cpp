#include <iostream>
using namespace std;

struct stud
{
  char *name;
  long stu_no;
  float math;
  float english;
};

int main()
{
  struct stud s1={"张三",11000,90.5,78.5},s2={"张三",11002,91.0,88.5};
  float m1,m2;
  m1=(s1.math+s1.english)/2;
  m2=(s2.math+s2.english)/2;
  cout<<s1.name<<'\t'<<m1<<endl;
  cout<<s2.name<<'\t'<<m2<<endl;
  system("pause");
}
