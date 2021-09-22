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
}a={10001,"张琪曼",'M',16,95.0,"北京路134号"},//注意此处为逗号
 b={10002,"楚继光",'F',16,92,"团结路66号"};//结束为分号
 
int main()
{
  cout<<a.num<<a.name<<a.sex<<a.age<<a.score<<a.addr<<endl;     
  cout<<b.num<<b.name<<b.sex<<b.age<<b.score<<b.addr<<endl;    
}
