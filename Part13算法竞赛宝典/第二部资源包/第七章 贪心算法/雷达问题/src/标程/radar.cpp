//雷达问题 
#include<iostream>
#include<cmath>
using namespace std;
struct coordinate//坐标结构体(x,y)
{
  int x,y;
}p[1001];

struct interval//区间结构体[a,b]
{
	double a;
	double b;
}b[1001];

int cmp_interval(const void *a,const void *b)//qsort的比较函数
{
	interval *A = (interval*) a;
	interval *B = (interval*) b;
	return (*A).b > (*B).b ? 1: -1;
}

double search(coordinate a,int d)//找出所给坐标对应的圆心允许范围
{
	double x;
	x = sqrt((double)(d*d - a.y*a.y)) + a.x;
	return x;
}
int main()
{
  freopen("radar.in","r",stdin);
  freopen("radar.out","w",stdout);  
  int n,d,c = 0,r;//r = number of radar,c = case
  bool impossible;
  while(cin >> n >> d)
  {
    if(n == 0)
      break;
    ++c;
    impossible = 0;
    r = 1;
    for(int i = 0;i < n;++i)
    {
	  cin >> p[i].x >> p[i].y;
      if(abs(p[i].y) > d)
		impossible = 1;
      b[i].a = 2*p[i].x - search(p[i],d);
      b[i].b = search(p[i],d);
    }
    qsort(b,n,sizeof(b[0]),cmp_interval);//将区间按右边界从小到大排序
    if(impossible)
      cout <<"Case "<< c <<": " << -1 <<endl;
    else
    {
      int temp = 0;
      
      for(int i = 0;i < n;++i)
      {
		if(b[i].a > b[temp].b)//对包含区间取最右端的点
		{
		  ++r;//若左边界大于之前区间的右边界，则必须增加雷达 
		  temp = i;
        }
      }
      cout <<"Case "<< c <<": " << r << endl;
    }
  }
  return 0;
}
