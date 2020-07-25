//╤андуб 
#include <iostream>
#include <string.h>
using namespace std;

int main()
{ int i,j,upp,low,dig,spa,oth;
  char text[3][80];
  upp=low=dig=spa=oth=0;
  for(i=0;i<3;i++)
  { 
    gets(text[i]);
    for(j=0;j<80;j++)
    { if(text[i][j]>='A'&&text[i][j]<='Z')  
        upp++;
      else  if(text[i][j]>='a'&&text[i][j]<='z')
        low++;
      else  if(text[i][j]>='0'&&text[i][j]<='9')
        dig++;
      else  if(text[i][j]==' ')
        spa++;
      else
        oth++;
    }
  }

  cout<<upp<<endl;
  cout<<low<<endl;
  cout<<dig<<endl;
  cout<<spa<<endl;
  cout<<oth<<endl;
  return 0;
}
