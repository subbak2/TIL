import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 탑 2493
public class Main {
	
	static int N, sp;			// N 탑 개수 / sp 스택 포인터
	static tower[] stack;		// 탑 Stack

	static class tower{
		int id, height;		// id 탑 순서 / height 높이

		public tower(int id, int height) {
			this.id = id;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 0. N 입력 / sp 스택 포인터 초기화
		N = Integer.parseInt(br.readLine());
		sp = 0;
		stack = new tower[N+1];
		
		// 1. 입력받으면서 Stack에 넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int input;
		for (int i=1; i<=N; i++) {
			input = Integer.parseInt(st.nextToken());
			
			// 1-1. 이전 값들이랑 비교해서 더 낮은 값이면 다 pop하기
			while(sp > 0) {
				if(stack[sp-1].height <= input) sp--;
				else break;
			}
			// 1-2. Stack에 남은게 없다면 0 출력
			if (sp==0) {
				bw.write(String.valueOf(0)+" ");				
			}
			// 1-3. Stack에 남은게 있다면 해당 id 출력
			else {
				bw.write(String.valueOf(stack[sp-1].id)+" ");
			}
			// 1-4. Stack에 현재 탑 넣기
			stack[sp++] = new tower(i, input);
		}
		bw.flush();
		bw.close();
		br.close();
	}
}