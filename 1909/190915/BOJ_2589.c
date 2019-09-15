#include <stdio.h>
#define MAX (1050)
typedef struct st {
	int y, x, val;
}
INFO;
int yy[4] = {
	-1,1,0,0
}
;
int xx[4] = {
	0,0,-1,1
}
;
int map[52][52];
int visit[52][52];
INFO Queue[52*52*4];
int W, H, sol, wp, rp;
void input(void);
void makeSol(void);
void BFS(int sy, int sx);
int main(void) {
	input();
	makeSol();
	printf("%d\n", sol);
	return 0;
}
void input(void) {
	register int i,j;
	char tmp;
	scanf("%d %d", &H, &W);
	for (i=1; i<=H; i++) {
		for (j=1; j<=W; j++) {
			scanf(" %c", &tmp);
			if (tmp=='W') {
				map[i][j] = -1;
			}
		}
	}
	sol = 0;
	return;
}
void makeSol(void) {
	register int i, j;
	for (i=1; i<=H; i++) {
		for (j=1; j<=W; j++) {
			if (map[i][j]==0) {
				BFS(i, j);
			}
		}
	}
	return;
}
void BFS(int sy, int sx) {
	INFO tmp;
	register int i, j;
	int nr, nc;
	for (i=1; i<=H; i++) {
		for (j=1; j<=W; j++)
		visit[i][j] = 0;
	}
	wp = rp = 0;
	visit[sy][sx] = 1;
	Queue[wp].y = sy;
	Queue[wp].x = sx;
	Queue[wp++].val = 0;
	while(rp<wp) {
		tmp = Queue[rp++];
		for (i=0; i<=3; i++) {
			nr = tmp.y + yy[i];
			nc = tmp.x + xx[i];
			if (nr < 1 || nr >H || nc < 1 || nc > W) continue;
			if (map[nr][nc] == -1 || visit[nr][nc] != 0) continue;
			visit[nr][nc] = 1;
			Queue[wp].y = nr;
			Queue[wp].x = nc;
			Queue[wp++].val = tmp.val + 1;
			if (tmp.val+1 > sol ) sol = tmp.val+1;
		}
	}
	return;
}
