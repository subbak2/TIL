import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 구간 합 구하기 백준 2042
public class Main {
	
	static int N, M, K;				// N 숫자 개수, M 변경 개수, K 질의 개수
	static int nn;					// 인덱스드트리 개수
	static long[] indexedTree;		// 인덱스드트리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M, K 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (nn=1; nn<N; nn*=2);	// 인덱스드트리 크기 특정 및 선언   (N보다 큰 최소 2^N)
		indexedTree = new long[nn*2+2];
		
		// 인덱스드트리에 데이터 입력			- 트리 하단에 1~N개 수의 최초 값을 입력
		for (int i=1; i<=N; i++) {
			indexedTree[nn+i-1] = Integer.parseInt(br.readLine());
		}
		// 구간합 데이터 만들어줌			- 밑에서부터 데이터를 쌓아올림
		for(int i=nn-1; i>=1; i--) {
			indexedTree[i] = indexedTree[i*2]+indexedTree[i*2+1];
		}
		
		int size = M + K;			// 변경과 쿼리의 합을 반복문 크기로 설정
		for (int i=1; i<=size; i++) {
			int type, x, y;		
			st = new StringTokenizer(br.readLine());
			type = Integer.parseInt(st.nextToken());		// 1이면 edit, 2이면 쿼리
			x = Integer.parseInt(st.nextToken());			// type=1 바꿀 수 / type=2 startId
			y = Integer.parseInt(st.nextToken());			// type=1 변경값 / type=2 endId
			
			if (type==1) {
				edit(x,y);
			}
			else {
				bw.write(String.valueOf(sum(x,y))+"\n");				
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 인덱스드트리 수정 1) 해당 id 값 수정 2) 위로 올라가면서 값 갱신 -> logN의 시간복잡도
	static void edit(int id, int value) {
		int x = id + nn - 1;
		// 인덱스드트리 위치에 value로 값을 덮어쓰고
		indexedTree[x] = value;
		x /= 2;
		while(x>0) {
			indexedTree[x] = indexedTree[x*2] + indexedTree[x * 2 + 1];
			x /=2;
		}
		return;
	}
	
	// 인덱스드트리 합 구하기 위로 올라가면서 합 구하기
	static long sum(int start, int end) {
		int l = start + nn - 1;
		int r = end + nn - 1;
		long ret = 0;
		while(l<=r) {
			if ((l & 1) == 1) ret += indexedTree[l++];	// 왼쪽 id가 홀수이면 값이 튀므로 더해주고 l++ 해줌 (짝수부터 시작해야하니까)
			if ((r & 1) == 0) ret += indexedTree[r--];	// 오른쪽 id가 짝수이면 값이 튀므로 더해주고 r-- 해줌 (홀수로 끝나야하니까)
			l/=2;	// 위로 올라가기
			r/=2;	// 위로 올라가기
		}
		return ret;
	}
}