import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 히스토그램에서 가장 큰 직사각형 백준 6549
public class Main {
		
	static class info{
		int id, height;		// 위치, 높이 

		public info(int id, int height) {
			this.id = id;
			this.height = height;
		}
	}
	
	static int N, input, wp;		// N 줄의 개수, input 입력받는 수, wp 스택의 writing pointer
	static long ans;				// ans 정답
	static info[] stack;			// stack 스택
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; 
		
		for ( ; ; ) {
			st = new StringTokenizer(br.readLine());
			// N 입력
			N = Integer.parseInt(st.nextToken());
			// N이 0이면 프로그램 종료
			if(N==0) break;
			
			// 초기화 Stack 초기화, pointer, answer 0으로 만들기
			stack = new info[N];
			wp = 0;
			ans = 0;
			
			// 1. 입력받는 직사각형의 높이가 Stack의 첫번째 수보다 작을 경우
			//     → Stack에서 더 작은 수가 나올때까지 빼면서 넓이 구해주고 갱신
			// 2. 입력받는 직사각형의 높이가 Stack의 첫번째 수보다 클 경우 
			//     → Stack에 추가 (더 큰 넓이가 될 가능성이 있으므로 추가)
			for(int i=0; i<N; i++) {
				int tId = i;		// 임시 id는 현재 위치 
				input = Integer.parseInt(st.nextToken()); 
			
				// 1. 입력받는 직사각형의 높이가 Stack의 첫번째 수보다 작을 경우
				//     → Stack에서 더 작은 수가 나올때까지 빼면서 넓이 구해주고 갱신
				while(wp > 0 && stack[wp-1].height > input) {
					long tmpSize = (long)(i - stack[wp-1].id) * stack[wp-1].height;
					ans = ans > tmpSize ? ans : tmpSize;
					tId = stack[wp-1].id;		// 임시 id를 앞으로 당김
					wp--;
				}
								
				// 2. 입력받는 직사각형의 높이가 Stack의 첫번째 수보다 클 경우 
				//     → Stack에 추가 (더 큰 넓이가 될 가능성이 있으므로 추가)
				stack[wp] = new info(tId, input);
				wp++;
				
			}
			// 3. 마지막으로 Stack에 남아있는 애들 쭉 빼면서 넓이 확인
			while(wp > 0) {
				long tmpSize = (long)(N - stack[wp-1].id) * stack[wp-1].height;
				ans = ans > tmpSize ? ans : tmpSize;
				wp--;
			}
			// 정답출력
			bw.write(String.valueOf(ans)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}