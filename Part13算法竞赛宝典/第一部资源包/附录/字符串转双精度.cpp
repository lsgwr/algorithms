//字符串数字转双精度浮点数
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <iostream>
using namespace std;
int main()
{
  char s1[50];
  double a;
  gets(s1);
  a=atof(s1);
  cout<<a;
}
