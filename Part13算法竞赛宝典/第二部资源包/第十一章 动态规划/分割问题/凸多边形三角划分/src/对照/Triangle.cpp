//凸多边形三角划分 
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <vector>
#include <algorithm>
using namespace std;

int f[51][51];
int data[51];
int n, psan;
int mid[51][51];

struct node
{
	int x, y, z;
}san[51];

int _min(int x, int y){ return x > y ? y : x; }

bool cmp(node a, node b)
{
	if (a.x == b.x)
	{
		if (a.y == b.y)
		{
			return a.z < b.z;
		}
		return a.y < b.y;
	}
	return a.x < b.x;
}

int dp(int l, int r)
{
	if (f[l][r] != 0)
		return f[l][r];
	if (r - l == 1)
		return 0;
	if (r - l == 2)
	{
		f[l][r] = data[l] * data[l + 1] * data[r];
		mid[l][r] = l + 1;
		return f[l][r];
	}
	int i;
	for (i = l + 1; i <= r - 1; i++)
	{
		if (f[l][r] == 0)
		{
			f[l][r] = dp(l, i) + dp(i, r) + data[l] * data[i] * data[r];
			mid[l][r] = i;
		}
		else
		{
			if (f[l][r] > (dp(l, i) + dp(i, r) + data[l] * data[i] * data[r]))
			{
				mid[l][r] = i;
				f[l][r] = dp(l, i) + dp(i, r) + data[l] * data[i] * data[r];
			}
		}
	}
	return f[l][r];
}

void getsan(int l, int r)
{
	if (r - l < 2)
		return;
	san[++psan].x = l;
	san[psan].y = mid[l][r];
	san[psan].z = r;
	getsan(l, mid[l][r]);
	getsan(mid[l][r], r);
}

int main()
{
	freopen("Triangle.in", "r", stdin);
	freopen("Triangle.out", "w", stdout);
	scanf("%d", &n);
	int i, j;
	for (i = 1; i <= n; i++)
		scanf("%d", &data[i]);
	f[1][n] = dp(1, n);
	getsan(1, n);
	sort(san + 1, san + psan + 1, cmp);
	printf("%d\n", f[1][n]);
	for (i = 1; i < psan; i++)
		printf("%d %d %d,", san[i].x, san[i].y, san[i].z);
	printf("%d %d %d\n", san[i].x, san[i].y, san[i].z);
}
