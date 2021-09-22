//魔法阵 
#include<iostream>
#include<cmath>
using namespace std;

int main()
{	
  double data[3][2];	
  double a[3],p;//存储三条边的长度。	
  int i;	
  for(i=0;i<3;i++)	
  {		
    cout<<"请输入第"<<i+1<<"个点"<<endl;		
    cin>>data[i][0]>>data[i][1];	
  }	
  p=pow(data[0][0]-data[1][0],2)+pow(data[0][1]-data[1][1],2);	
  a[0]=sqrt(p);	
  p=pow(data[0][0]-data[2][0],2)+pow(data[0][1]-data[2][1],2);	
  a[1]=sqrt(p);	
  p=pow(data[1][0]-data[2][0],2)+pow(data[1][1]-data[2][1],2);	
  a[2]=sqrt(p);	
  //判断能否构成三角形	
  if(a[0]+a[1]<=a[2]||a[0]+a[2]<=a[1]||a[1]+a[2]<=a[0]) 
    return 0;	
  //利用海伦公式。s=sqr(p*(p-a)(p-b)(p-c));	
  p=(a[0]+a[1]+a[2])/2;	
  double s=sqrt(p*(p-a[0])*(p-a[1])*(p-a[2]));	
  cout<<"面积是:"<<s<<endl;	
  system("pause");
  return 0;
}
