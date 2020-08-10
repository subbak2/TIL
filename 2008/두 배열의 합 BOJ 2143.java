
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 두 배열의 합 백준 2143
public class Main {
	
	static int T, N, M;					// T - 목표 숫자, N - A 배열의 개수, M - B 배열의 개수
	static int[] A, B;					// A, B 입력받는 배열
	static int ap, bp;					// a pointer, b pointer
	static long ans;					// ans 정답
	
	static ArrayList<Integer> sumA, sumB;	// A배열의 부분합, B배열의 부분합		
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; 
		
		// T 입력
		T = Integer.parseInt(br.readLine());
		
		// N, A 배열 입력
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N ; i++ ) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// M, B 배열 입력
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M ; i++ ) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 1. A로 만들 수 있는 부분합을 만들자
		// 시간복잡도 N^2
		sumA = new ArrayList<Integer>(); 
		for (int i=0; i<N; i++) {
			int tmp = A[i];
			sumA.add(tmp);
			for (int j=i+1; j<N; j++) {
				tmp += A[j];
				sumA.add(tmp);
			}
		}
		
		
		// 2. B로 만들 수 있는 부분합을 만들자
		// M^2
		sumB = new ArrayList<Integer>(); 
		for (int i=0; i<M; i++) {
			int tmp = B[i];
			sumB.add(tmp);
			for (int j=i+1; j<M; j++) {
				tmp += B[j];
				sumB.add(tmp);
			}
		}
		
		
		// 3. A, B로 만들 수 있는 부분합을 정렬 
		sumA.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		sumB.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		
		
		// 4. 투포인터로 T의 개수를 구함  
		int sumASize = sumA.size();
		int sumBSize = sumB.size();
		ap = 0;
		bp = sumBSize - 1;
		ans = 0;
		
		while(ap < sumASize && bp>=0) {
			int aTmp = sumA.get(ap);
			int bTmp = sumB.get(bp);
			int aCnt = 0;
			int bCnt = 0;
			// 4-1. A부분합 + B부분합 = T일 경우
			// 답이 아닐때까지 for문 돌면서 카운트하고 곱을 답에 더해줌
			if(aTmp + bTmp == T) {
				// 낮은 값 포인터 될때까지 상승
				while(ap < sumASize && sumA.get(ap) == aTmp) {
                    ap++;		
                    aCnt++;
                }
				// 높은 값 포인터 될떄까지 감소
                while(bp >= 0 && sumB.get(bp) == bTmp) {
                    bp--;
                    bCnt++;
                }
                ans += (long)aCnt*(long)bCnt;
			}
			// 4-2. 값 초과이므로 값을 깎음 (높은 값 포인터 감소)
			else if(aTmp + bTmp > T) {
                bp--;
            }
			// 4-3. 값 미달이므로 값을 높임 (낮은 값 포인터 증가)
			else { //if(aTmp + bTmp < T) {
                ap++;
            }
		}
		
		// 정답출력
		bw.write(String.valueOf(ans));
		
		bw.flush();
		bw.close();
		br.close();
	}
}