# include <iostream>
using namespace std;

struct stud
{
  int num;
  char name[10];
  int age;
};

void fun(struct stud *p)
{
  cout<<(*p).name<<endl;
}

int main()
{
  struct stud s[3]={{1,"z",90},{2,"w",76},{4,"c",82}};
  fun(s+2);
  int i;
}
