//FBIÊ÷
#include <iostream>   
using namespace std;  
const int NMAX = ( 1 << 11 ) + 1;  

struct node 
{  
    char c;     
    int value;  
    int leftson,rightson;  
};  
int N,i,j;  
node tree[NMAX];  
  
char check( int left , int right )  
{  
    if( tree[left].c == 'B' && tree[right].c == 'B' )  
        return 'B';   
    else if( tree[left].c == 'I' && tree[right].c == 'I' )  
        return 'I';  
    else  
        return 'F';   
}  
  
void LRD( int root )  
{  
    if( root != -1 ){  
        LRD( tree[root].leftson );  
        LRD( tree[root].rightson );   
        printf( "%c", tree[root].c );  
    }  
}  
  
int main()  
{  
  freopen("fbi.in","r",stdin);
  freopen("fbi.out","w",stdout);
    scanf( "%d", &N );  
    for( i = ( 1 << N ) ; i < ( 1 << ( N + 1 ) ) ; i++ )
    {  
      scanf( "%1d", &tree[i].value );  
      tree[i].leftson = -1;  
      tree[i].rightson = -1;  
      if( tree[i].value ) 
        tree[i].c = 'I';  
      else 
        tree[i].c = 'B';  
    }  
    int temp = N - 1 ;  
    while( temp >= 0 )
    {     
      for( i = 1 << temp ; i < 1 << ( temp + 1 ) ; i++ )
      {  
        tree[i].leftson = 2 * i;  
        tree[i].rightson = 2 * i + 1;  
        tree[i].c = check( 2 * i , 2 * i + 1 );  
      }  
      temp -- ;  
    }  
    LRD( 1 );  
    printf( "\n" );     
    return 0;  
}  
