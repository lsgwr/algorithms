//ÓïÑÔÖ®Õù
#include<fstream>
#include<string>
using namespace std;

ifstream cin("language.in");
ofstream cout("language.out");

string a;
int n;

int main()
{
  int i;
  bool java = false,cpp = false,word = false,err = false;
  cin>>a;
  n = a.size();

  for(i = 0;i < n ;i ++ )
  {
    if(a[i] >= 'A' and a[i] <= 'Z')
	  java = true;
    if(a[i] == '_')
	  cpp = true;
  }

  if(a[0] >= 'A' and a[0] <= 'Z') 
    java = false;
  for(i = 0 ;i < n; i++) 
    if(a[i] == '_' and a[i + 1] == '_') 
      cpp = false;

  if( (not java and not cpp) or (java and cpp) )
    err = true ;
  
  if(err)
    cout<<"Error!\n";
  else
  {
    if(cpp)//change to java
    {
	  word = false;
	  for(i = 0 ;i < n; i++)
	    if(word)
	      {cout<<char(a[i]-32);word = false;}
	    else if(a[i] >= 'a' and a[i]<= 'z')
	      cout<<a[i];
	    else if(a[i] == '_')
	      word = true;
    }
    else//change to cpp
	{
	  for(i = 0 ;i < n ; i++)
	    if(a[i] >= 'A' and a[i] <= 'Z')
        {
		  if(i > 0) cout<<"_";
		    cout<<char(a[i]+32);
        }
	    else
	      cout<<a[i];
	}
    cout<<"\n";
  }
  cin.close();
  cout.close();
  return 0;
}
