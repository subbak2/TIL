// 고스택 3425
#if 1
#include <stdio.h>
#define NUM (1000000000)
#define ABS(a) (((a) > 0) ? (a) : (-(a)))

void input();
void program();
void numX(int x);
void pop();
void inv();
void dup();
void swp();
void add();
void sub();
void mul();
void div();
void mod();	

int flag, i, x, N;
char inChar[5];
int cmd[100001], num[100001], inNum[10001];
long long stack[1002];
int cCnt, nCnt, pt;
int main(void) {

	//freopen("input.txt", "r", stdin);
	for (;;) {
		input();
		if (flag == -1) return 0;
		program();
	}
	return 0;
}

void input() {
	flag = cCnt = nCnt = 0;
	for (;;) {
		scanf("%s", &inChar[0]);
		if (inChar[0] == 'N') {
			scanf("%d", &x);
			cmd[cCnt++] = 0;
			num[nCnt++] = x;
		}
		else if (inChar[0] == 'P') cmd[cCnt++] = 1;
		else if (inChar[0] == 'I') cmd[cCnt++] = 2;
		else if (inChar[0] == 'D') {
			if (inChar[1] == 'U') cmd[cCnt++] = 3;
			else cmd[cCnt++] = 4;
		}
		else if (inChar[0] == 'S') {
			if (inChar[1] == 'W') cmd[cCnt++] = 5;
			else cmd[cCnt++] = 6;
		}
		else if (inChar[0] == 'A') cmd[cCnt++] = 7;
		else if (inChar[0] == 'M') {
			if (inChar[1] == 'U') cmd[cCnt++] = 8;
			else cmd[cCnt++] = 9;
		}
		else if (inChar[0] == 'E') break;
		else if (inChar[0] == 'Q') {
			flag = -1;
			return;
		}
	}
	scanf("%d", &N);
	for (i = 1; i <= N; i++) {
		scanf("%d", &inNum[i]);
	}
}

void program() {
	int j;
	for (i = 1; i <= N; i++) {
		nCnt = pt = 0;
		stack[pt++] = inNum[i];
		for (j=0; j<cCnt;j++){
			flag = cmd[j];
			if (flag == 0) {	// NUM
				numX(num[nCnt++]);
			}
			else if (flag == 2) {	// INV
				inv();
			}
			else if (pt == 0) {
				flag = -2;
				break;
			}
			else if (flag == 1) {	// POP
				pop();
			}
			else if (flag == 3) {	// DUP
				dup();
			}
			else if (pt == 1) {
				flag = -2;
				break;
			}
			else if (flag == 4) {	// DIV
				div();
			}
			else if (flag == 5) {	// SWP
				swp();
			}
			else if (flag == 6) {	// SUB
				sub();
			}
			else if (flag == 7) {	// ADD
				add();
			}
			else if (flag == 8) {	// MUL
				mul();
			}
			else if (flag == 9) {	// MOD
				mod();
			}
			if (flag == -2) break;
		}
		if (flag == -2 || pt != 1) printf("ERROR\n");

		else printf("%lld\n", stack[0]);
	}
	printf("\n");
}

void numX(int x) {
	stack[pt++] = x;
}
void pop() {
	pt--;
}
void inv() {
	stack[pt - 1] = -stack[pt - 1];
}
void dup() {
	stack[pt++] = stack[pt - 1];
}
void swp() {
	long long tmp = stack[pt - 1];
	stack[pt-1] = stack[pt - 2];
	stack[pt - 2] = tmp;
}
void add() {
	long long tmp = stack[pt - 1] + stack[pt - 2];
	if (ABS(tmp) > NUM) {
		flag = -2;
		return;
	}
	stack[pt - 2] = tmp;
	pt--;
}
void sub() {
	long long tmp = stack[pt - 2] - stack[pt - 1];
	if (ABS(tmp) > NUM) {
		flag = -2;
		return;
	}
	stack[pt - 2] = tmp;
	pt--;
}
void mul() {
	long long tmp = stack[pt - 2] * stack[pt - 1];
	if (ABS(tmp) > NUM) {
		flag = -2;
		return;
	}
	stack[pt - 2] = tmp;
	pt--;
}
void div() {
	long long tmp1, tmp2;
	int tFlag = 0;
	if (stack[pt - 1] == 0) {
		flag = -2;
		return;
	}
	tmp1 = stack[pt - 2];
	tmp2 = stack[pt - 1];
	if (tmp1 < 0) tFlag++;
	if (tmp2 < 0) tFlag++;
	tmp1 = ABS(tmp1) / ABS(tmp2);
	if (tFlag == 1) stack[pt - 2] = -tmp1;
	else stack[pt - 2] = tmp1;
	pt--;
}
void mod() {
	long long tmp;
	if (stack[pt - 1] == 0) {
		flag = -2;
		return;
	}
	tmp = ABS(stack[pt - 2]) % ABS(stack[pt - 1]);
	if (stack[pt - 2]<0) stack[pt - 2] = -tmp;
	else stack[pt - 2] = tmp;
	pt--;
}
#endif