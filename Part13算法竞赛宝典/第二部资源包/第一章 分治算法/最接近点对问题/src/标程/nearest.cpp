//最接近点对 
#include <iostream>
#include <cstdlib>
#include <cmath>
using namespace std ;

const int  maxn = 1000001 ;
const int  INF = 1000000001 ;
int n,temp[maxn];

struct Point
{
  double x , y ;
}S[ maxn ] ;

bool cmp(const  Point &a , const Point &b )
{
  if( a.x == b.x )
    return  a.y < b.y ;
  else
    return a.x < b.x ;
}

bool cmpy( const int& a , const int& b )
{
  return S[ a ].y < S[ b ].y ;
}

double min( double a , double b )
{
  return a < b ? a : b ;
}

double dist( int i , int j )
{
  double x=(S[i].x-S[j].x)*(S[i].x-S[j].x);
  double y=(S[i].y-S[j].y)*(S[i].y-S[j].y);     
  return sqrt(x+y);
}

double merge( int left , int right )
{
  double d = INF ;
  if( left == right )//若左右边界重合 
    return d ;
  if( left + 1 == right )//若左右边界差值为１即只有两个点 
    return dist( left , right ) ;

  int mid = ( left + right ) >> 1 ;//从中间划分 
  double d1 = merge( left , mid ) ;
  double d2 = merge( mid + 1 , right ) ;
  d = min( d1 , d2 ) ;//递归求出最小值d 
  int i , j , k = 0 ;
  for( i = left ; i <= right ; ++i )//找出d范围内的点存入temp[] 
    if( fabs( S[ mid ].x - S[ i ].x ) <= d )
      temp[ k++ ] = i ;
  sort( temp , temp + k , cmpy );//按y坐标排序 
  for(i=0;i<k;++i)//穷举存入temp[]内的这些点 
    for(j=i+1;j<k && S[temp[j]].y-S[temp[i]].y<d;++j)//找出最近(p,q) 
    {
      double d3 = dist( temp[ i ] , temp[ j ] ) ; 
      if( d > d3 )
		d = d3 ;
    }
  return d ;
}

int main()
{
  freopen("nearest.in","r",stdin);
  freopen("nearest.out","w",stdout);
  scanf( "%d" , &n);
  for(int  i = 0 ; i < n ; ++i )
    scanf( "%lf%lf" , &S[ i ].x , &S[ i ].y ) ;
  sort( S , S + n , cmp );//点按x坐标由小到大排序 
  printf( "%.2lf\n" , merge( 0 , n - 1 )/2);
  return 0 ;
}
