import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 합이 0인 네 정수 백준 7453
public class Main {
	
	static int N;			// N 숫자 개수
	static long ans;		// ans 답
	static int[] A,B,C,D;		// 입력받은 배열
	static int[] AB, CD;		// 입력받은 배열 2개의 합
	static int ABid, CDid;	// 투포인터를 위한 포인터
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N 입력
		N = Integer.parseInt(br.readLine());
		
		// 초기값 세팅
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		AB = new int[N*N];
		CD = new int[N*N];
		ans = 0;
		
		// 1. 주어진 배열 입력받기
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 배열 2개씩 짝지어서 합 배열 만들기
		ABid = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				AB[ABid] = A[i]+B[j];
				CD[ABid] = C[i]+D[j];
				ABid++;
			}
		}
		CDid = ABid-1;	// 둘의 수가 같으므로 CD 포인터 = AB 포인터 - 1
		
		// 3. 오름차순으로 2가지 배열 정리
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		// 4. 투포인터
		for (int i=0; i<ABid && CDid>=0; ) {
            int ABsum = AB[i];
            int CDsum = CD[CDid];
            long ABcnt = 0;
            long CDcnt = 0;
            int totSum = ABsum+CDsum;
            // 4-1. 합이 0인 경우(답인경우)
            if(totSum == 0) {
            	// 4-1-1. AB에서 중복인 답의 개수를 체크
                while(i<ABid && ABsum == AB[i]) {
                    i++;
                    ABcnt++;
                }
                // 4-1-2. CD에서 중복인 답의 개수를 체크
                while(CDid>=0 && CDsum == CD[CDid]) {
                    CDid--;
                    CDcnt++;
                }
                // 4-1-3. AB count * CD count
                ans+=ABcnt*CDcnt;
            }
            // 4-2. 0보다 클 경우 값을 줄여야하므로 CD의 포인터를 낮추기
            else if(totSum >0) {
                CDid--;
            }
            // 4-3. 0보다 작을 경우 값을 늘려야하므로 AB의 포인터를 낮추기
            else {
                i++;
            }
		}
		
		// 정답 출력
		bw.write(String.valueOf(ans)+"\n");				

		bw.flush();
		bw.close();
		br.close();
	}
}