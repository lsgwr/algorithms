#include <iostream>
#include <string>

const int	MAXN	=	110;

char	A[MAXN], B[MAXN], ch;
int		F[MAXN][MAXN], s[256];
int		N;

int max (int a, int b)
{
	return a > b ? a : b;
}

int main()
{
	freopen ("erase.in", "r", stdin);
	freopen ("erase.out", "w", stdout);

	scanf ("%d\n", &N);
	for (int i = 0; i < N; ++i)
	{
		scanf ("%c", &ch);
		scanf ("%d\n", &s[ch]);
	}
	scanf ("%s\n", A);
	scanf ("%s\n", B);

	memset (F, 0, sizeof (F));
	for (int i = 1; i <= strlen (A); ++i)
		for (int j = 1; j <= strlen (B); ++j)
		{
			F[i][j] = max (F[i - 1][j], F[i][j - 1]);
			if (A[i - 1] == B[j - 1])
				F[i][j] = max (F[i][j], F[i - 1][j - 1] + s[A[i - 1]]);
		}
	
	printf ("%d\n", F[strlen (A)][strlen (B)]);
	
	return 0;
}
