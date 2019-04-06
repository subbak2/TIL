//Çà·ÄÁ¦°ö
#if 1
#include <stdio.h>
#pragma warning (disable: 4996)
#define NUM (1000)

unsigned int T, tc;
int N;
long long B;

int matrix[6][6];
int ans[6][6];
int tmp[6][6];

void Input(void);
void PRINT(void);

int main(void)
{
	Input();
	while (B > 0) {
		register int i, j, k;
		if (B % 2 == 1) 
		{			
			for (i = 1; i <= N; i++)
			{
				for (j = 1; j <= N; j++)
				{
					for (k = 1; k <= N; k++) {
						tmp[i][j] += (ans[k][j] * matrix[i][k])%NUM;
					}
				}
			}
			for (i = 1; i <= N; i++) {
				for (j = 1; j <= N; j++) {
					ans[i][j] = tmp[i][j]%NUM;
					tmp[i][j] = 0;
				}
			}
		}
		for (i = 1; i <= N; i++)
		{
			for (j = 1; j <= N; j++)
			{
				for (k = 1; k <= N; k++) {
					tmp[i][j] += (matrix[k][j] * matrix[i][k])%NUM;
				}
			}
		}
		for (i = 1; i <= N; i++) {
			for (j = 1; j <= N; j++) {
				matrix[i][j] = tmp[i][j]%NUM;
				tmp[i][j] = 0;
			}
		}
		B /= 2;
	}
	PRINT();
	return 0;
}

void PRINT(void) {
	register int i, j;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			printf("%d ", ans[i][j]);
		}
		printf("\n");
	}
	return;
}

void Input(void) {
	register int i, j;
	scanf("%d %lld", &N, &B);
	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= N; j++)
		{
			scanf("%d", &matrix[i][j]);
			if (i == j) ans[i][j] = 1;
			else ans[i][j] = 0;
			tmp[i][j] = 0;
		}
	}
	return;
}

#endif