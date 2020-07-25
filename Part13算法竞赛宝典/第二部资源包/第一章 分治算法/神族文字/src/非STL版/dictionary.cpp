//神族文字－非STL版 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <algorithm>
using namespace std;
#pragma warning(disable:4996)

struct word
{
	char get[11];
	char have[11];
}dic[100001];

char str[25];
int n;

int cmp(const void * x, const void * y)
{
	return strcmp((*(word *)x).have, (*(word *)y).have);
}

int judge()
{
	int l = 1, r = n;
	int ans;
	int mid;
	while (l <= r)
	{
		mid = (l + r) >> 1;
		ans = strcmp(str, dic[mid].have);
		if (ans == 0)
			return mid;
		else if (ans == 1)
			l = mid + 1;
		else
			r = mid - 1;
	}
	return -1;
}

int main()
{
	freopen("dictionary.in", "r", stdin);
	freopen("dictionary.out", "w", stdout);
	int a;
	while (gets(str))
	{
		if (str[0] == '\0')
			break;
		n++;
		sscanf(str, "%s%s", dic[n].get, dic[n].have);
	}
	qsort(dic + 1, n, sizeof(dic[0]), cmp);
	int l;
	while (scanf("%s", &str) != EOF)
	{
		a=judge();
		if (a == -1)
			printf("eh\n");
		else
			printf("%s\n", dic[a].get);
	}
}
