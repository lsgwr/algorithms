//邮票面值问题－改进 
#include <stdio.h>
#include <stdlib.h> 
FILE *in=fopen("Stamp.in","r"),*out=fopen("Stamp.out","w");
 
#define M 50  // N+K≤40
int N,K;
int k[M],n[M];  //k储存选中的面值组合，n[i]表示选定的k[i]面值的张数
bool line[300];  //line[i]表示能否得到面值i，能为1，不能为0，估计MAX不超过300
int ANS;  //最优MAX
int ans[M];  //储存最优组合

void finish()
//步骤c.计算MAX，并判断是否最优解
{
	int i,MAX;
	for(i=1;line[i];i++) line[i]=0;
	MAX=i-1;   //记录当前能取得的MAX
	for(;i<300;i++) line[i]=0;   //初始化line数组
	
	if(MAX>ANS)  //如果当前MAX为最优，储存之
	{
		ANS=MAX;
		for(i=2;i<=K;i++)
			ans[i]=k[i];
	}
}

void toline()
{
	int i,s=0;
	for(i=1;i<=K;i++)
		s+=k[i]*n[i];
     //计算当前构造出的面值

	if(s<300)  //防止爆出line，干扰其他数据
		line[s]=1;
}

void nn(int dep,int s)
//步骤b.穷举张数 n[dep]:面值k[dep]的邮票的张数;s:剩余可贴的张数
{
	int i;
	for(i=0;i<=s;i++)  //k[dep]可取0到s张
	{
		n[dep]=i;
	    if(s-i<0) return;
	    
		else if(dep==K)
			toline();  //将当前拼出的面值记入line中
			
		else if(dep<K)
			nn(dep+1,s-i);
	}
}

void kk(int dep)
//步骤a.面值组合,55选K
{
	int i;
	for(i=k[dep-1]+1;i<=55-K+dep;i++)
	{
		k[dep]=i;
		if(dep==K)  //完成步骤a.，确定一种组合
		{
			nn(1,N);  //b.穷举选中的各面值邮票的张数
			finish();  //c.确定该组合的MAX，并判断该面值组合是否最佳
		}
		else
			kk(dep+1);
	}
}

int main()
{
	fscanf(in,"%d %d",&N,&K);
	k[1]=1;ans[1]=1;
	kk(2);  //k[1]必为1，从2开始递归组合

	//输出
	int i;
	for(i=1;i<=K;i++)
	    fprintf(out,"%d ",ans[i]);
	fprintf(out,"%d\n",ANS);
	return 0;
}
