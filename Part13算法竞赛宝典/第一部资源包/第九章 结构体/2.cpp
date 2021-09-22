# include <iostream>
using namespace std;

struct stud
{
  char num[10];
  float score[3];
};

int main()
{
  struct stud s[3]={{"11",90,89,78},{"22",98,56,76},{"44",76,84,82}},*p=s;
  int i;
  float sum=0;
  for(i=0;i<3;i++)
    sum=sum+p->score[i];
  cout<<sum<<endl;
  system("pause");
}
