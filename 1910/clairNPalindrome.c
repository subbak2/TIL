#include <stdio.h>
#define MAX (105)

int N;
char array[MAX];
void input();
void makePalindrome();
void print();

int main(void){
	input();
	makePalindrome();
	print();
	return 0;
}


void input(){
	register int i;
	scanf("%d", &N);
	for (i=1; i<=N; i++){
		scanf(" %c", &array[i]);
	}
	return;
}

void makePalindrome(){
	register int i;
	int mid = (N+1)/2;
	for (i=1; i<=mid; i++){
		if (array[i]=='?'){
			if (array[N-i+1]=='?'){
				array[i] = array[N-i+1] = 'a';
			}
			else{
				array[i] = array[N-i+1];
			}
		}
		else if(array[N-i+1]=='?'){
			array[N-i+1] = array[i];
		}
	}
	return;
}

void print(){
	register int i;
	for (i=1; i<=N; i++)
		printf("%c", array[i]);
	return;
}
