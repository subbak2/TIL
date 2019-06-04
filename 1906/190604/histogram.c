// histogram
#if 1
#pragma warning (disable : 4996)
#include <stdio.h>
#define MAX (100050)

int N, wp, sol;
int input[MAX];
int stack[MAX];

void Input(void);
int Answer(void);
int Max(int a, int b);

int main(void)
{
	Input(); 
	printf("%d\n", Answer());
	return 0;
}

void Input(void){
	register int i;
	scanf("%d", &N);
	for (i = 1; i <= N; i++){
		scanf("%d", &input[i]);
	}
	input[N + 1] = 0;
	return;
}

int Answer(void)
{
	register int i;
	int height;
	wp = sol = 0;
	stack[wp++] = 0;
	for (i = 1; i <= N+1; i++){
		while (wp > 0 && input[stack[wp - 1]] > input[i]){
			height = input[stack[wp - 1]];
			wp--;
			sol = Max(sol, height * (i - stack[wp - 1]-1));
		}
		stack[wp++] = i;
	}
	return sol;
}

int Max(int a, int b){
	return a >= b ? a : b;
}
#endif