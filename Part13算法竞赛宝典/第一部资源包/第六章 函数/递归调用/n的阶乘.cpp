//nµÄ½×³Ë 
# include <iostream>
using namespace std;

int factorial(int n)
{
  int f;
  if(n<0)
     cout<<"´íÎóµÄÊäÈë";
  else if(n==0 || n==1)
     f=1;
  else 
     f=factorial(n-1)*n;
  return(f);         
}

 main()
{
  int n;
  cin>>n;
  cout<<n<<"!="<<factorial(n);
  system("pause");
  return 0; 
}
