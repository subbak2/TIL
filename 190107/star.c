// º°Âï±â 11
#if 1
#pragma warning (disable : 4996)
#include <stdio.h>

char arr[5000][10000];
int n, i, j;

void Init(void){
	register int i, j;
	for (i = 0; i < n; i++){
		for (j = 0; j < 2 * n; j++){
			if (j == (2 * n) - 1){
				arr[i][j] = '\0';
			}
			else{
				arr[i][j] = ' ';
			}
		}
	}
}

void star(int n, int x, int y){
	if (n == 3){
		arr[y][x] = '*';
		arr[y + 1][x - 1] = '*';
		arr[y + 1][x + 1] = '*';
		arr[y + 2][x - 2] = '*';
		arr[y + 2][x - 1] = '*';
		arr[y + 2][x] = '*';
		arr[y + 2][x + 1] = '*';
		arr[y + 2][x + 2] = '*';
		return;
	}
	star(n / 2, x, y);
	star(n / 2, x - (n / 2), y + (n / 2));
	star(n / 2, x + (n / 2), y + (n / 2));
}

void Print(void){
	register int i, j;
	for (i = 0; i < n; i++){
		for (j = 0; j < (2 * n) - 1; j++){
			printf("%c", arr[i][j]);
		}
		printf("\n");
	}
}

int main(void)
{
	scanf("%d", &n);
	Init();
	star(n, n - 1, 0);
	Print();
	return 0;
}
#endif