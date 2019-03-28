// µ¿Àü
#if 1
#pragma warning (disable : 4996)
#include <stdio.h>
#define MAX (22)
#define MAX2 (10010)

unsigned int T, tc;
int N, M;
int coin[MAX];
int value[MAX2];

void Input(void);
void MakeSol(void);

int main(void)
{
	scanf("%u", &T);
	for (tc = 1; tc <= T; tc++){
		Input();
		MakeSol();
		printf("%d\n", value[M]);
	}
	return 0;
}

void MakeSol(void){
	register int i,j;
	for (i = 1; i <= N; i++){
		value[coin[i]]++;
		for (j = coin[i] + 1; j <= M; j++){
			value[j] += value[j - coin[i]];
		}
	}
	return;
}

void Input(void){
	register int i;
	scanf("%d", &N);
	for (i = 1; i <= N; i++){
		scanf("%d", &coin[i]);
	}
	scanf("%d", &M);
	for (i = 1; i <= M; i++){
		value[i] = 0;
	}
	return;
}
#endif