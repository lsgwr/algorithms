//星际争霸2 
#include<cstdio>
#include<cmath>
#include<iostream>
using namespace std;
int fa[30001],s[30001],t[30001],m;
/*
fa[i]  i的父亲  初始值为i  自己
s[i]   i所在队列中 i之前的元素的个数  初始值为0
t[i]   i所在队列中 所有的元素和       初始值为1 
*/

/*查集的时候用路径压缩，进行路径压缩的时候s也要跟着修改：
s[i]=s[i]+s[fa[i]]  因为i要接在根上，  这里要更新下S */
int find(int v)
{
  int p;
  if(fa[v]==v)return(v);
  p=find(fa[v]);
  s[v]+=s[fa[v]];
  fa[v]=p;
  return(fa[v]);
}

int main()
{
  int i,fx,fy,x,y;
  char ch;
  freopen("galaxy.in","r",stdin);
  freopen("galaxy.out","w",stdout);
  scanf("%d\n",&m);

  for(i=1;i<=30000;i++)
  {
    fa[i]=i; 
	s[i]=0;
	t[i]=1;
  }

  for(i=1;i<=m;i++)
  {
	scanf("%c %d %d\n",&ch,&x,&y);
	fx=find(x);
	fy=find(y);
	if(ch=='M')
    {
	 if(fx!=fy)
     {
		fa[fx]=fy;
		s[fx]+=t[fy];
		t[fy]+=t[fx];
	 }
	}
	else{
     if(fx!=fy)
		printf("-1\n");
	 else 
		if(x==y)printf("0\n");
		else printf("%d\n",int(abs(s[x]-s[y])-1));
	}
  }
  fclose(stdin);
  fclose(stdout);
  return(0);
}
