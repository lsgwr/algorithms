//切割能量棒 
#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{ 
  freopen("CuttingSticks.in","r",stdin);
  freopen("CuttingSticks.out","w",stdout);  
  int length,n,i,j,x,y,temp,l,k,tt,sum;
  int t[51][51],p[51]; 
  while((scanf("%d",&length)) && length)
  { 
    cin>>n; //获得切割的次数 
	y=0; 
	for(i=1;i<=n;i++)
    { 
	  cin>>x; //获得切割位置 
	  p[i]=x-y; //转成最小段能量棒长度 
	  y=x; 
	} 
	p[i]=length-y;//最后一段能量棒的长度 
	
	n = i;
	for(i=0;i<=n;i++) 
	  t[i][i] = 0; //初始化,第i段到第i段的最小代价为0 
	for(l=2;l<=n;l++)
    { 
	  for(i=1;i<=n-l+1;i++)
      { 
		j=i+l-1; 
		t[i][j]=100000;//先取个最大值 
		for(k=i;k<=j-1;k++)//逐个试k的值 
        { 
		  temp=t[i][k]+t[k+1][j]; 
		  sum = 0; 
		  for(tt=i;tt<=j;tt++) 
			sum+=p[tt]; 
		  temp+=sum; 
		  if(temp<t[i][j]) 
			t[i][j] = temp; 
		} 
	  } 
   } 
   printf("The minimum cutting is %d.\n",t[1][n]); 
 } 
}
