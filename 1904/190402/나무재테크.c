//나무 재테크
#if 1
#include <stdio.h>
#include <queue>
#pragma warning (disable: 4996)

using namespace std;

int N, M, K;
int yy[8] = { -1,-1,-1,0,0,1,1,1 };
int xx[8] = { -1,0,1,-1,1,-1,0,1 };

typedef struct st {
	int age;
	bool operator < (const struct st & ref) const {
		return age > ref.age;
	}
}INFO;

typedef struct st2 {
	int val;
	priority_queue<INFO> trees;
	queue<int> deads;
}MAP;

queue<INFO> tmp;
MAP forest[17][17];
int map[17][17];

void Input(void);
void Spring(void);
void Summer(void);
void Fall(void);
void Winter(void);
int CNT(void);

int main(void)
{
	int i;
	Input();
	for (i = 1; i <= K; i++) {
		Spring();
		Summer();
		Fall();
		Winter();
	}
	printf("%d\n", CNT());
	return 0;
}

int CNT(void) {
	register unsigned int i, j;
	int sol;
	sol = 0;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			sol +=forest[i][j].trees.size();
		}
	}
	return sol;
}

void Spring(void) {
	register int i, j;
	int size;
	INFO t_age;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			while (!forest[i][j].trees.empty()) 
			{
				t_age = forest[i][j].trees.top(); forest[i][j].trees.pop();
				if (forest[i][j].val < t_age.age) {
					forest[i][j].deads.push({ t_age.age });
				}
				else
				{
					forest[i][j].val -= t_age.age;
					t_age.age++;
					tmp.push(t_age);
				}
			}
			while (!tmp.empty()) {
				forest[i][j].trees.push(tmp.front());
				tmp.pop();
			}
		}
	}
	return;
}

void Summer(void) {
	register int i, j;
	int t_age;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			while (!forest[i][j].deads.empty()) {
				t_age = forest[i][j].deads.front(); forest[i][j].deads.pop();
				forest[i][j].val += t_age / 2;
			}
		}
	}
	return;
}

void Fall(void) {
	register int i, j, k;
	INFO t_age;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++) {
			while (!forest[i][j].trees.empty())
			{
				t_age = forest[i][j].trees.top(); forest[i][j].trees.pop();
				if (t_age.age % 5 == 0) {
					int nr, nc;
					for (k = 0; k <= 7; k++) 
					{
						nr = i + yy[k];
						nc = j + xx[k];
						if (nr<1 || nc<1 || nr>N || nc>N) continue;
						forest[nr][nc].trees.push({ 1 });
					}
				}
				tmp.push(t_age);
			}
			while (!tmp.empty()) {
				forest[i][j].trees.push(tmp.front());
				tmp.pop();
			}
		}
	}
	return;
}

void Winter(void) {
	register unsigned int i, j;
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= N; j++)
		{
			forest[i][j].val += map[i][j];
		}
	}
	return;
}

void Input(void) {
	register unsigned int i, j;
	int tmp1, tmp2, tmp3;
	scanf("%d %d %d", &N, &M, &K);
	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= N; j++) {
			scanf("%d", &map[i][j]);
			forest[i][j].val = 5;
			while (!forest[i][j].trees.empty()) {
				forest[i][j].trees.pop();	//age의 emptu
			}
		}
	}
	for (i = 1; i <= M; i++)
	{
		scanf("%d %d %d", &tmp1, &tmp2, &tmp3);
		forest[tmp1][tmp2].trees.push({ tmp3 });
	}
	return;
}
#endif