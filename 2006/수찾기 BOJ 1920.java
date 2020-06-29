import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 수찾기 백준 1920
public class Main {

	static int N, M;
	static int[] array;	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		// 숫자를 받을 배열
		array = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		// 입력받은 숫자를 오름차순 정렬	
		Arrays.sort(array);
	
		M = Integer.parseInt(br.readLine()); 
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(binarySearch(tmp))+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	// num가 array에 있으면 1 return 없으면 0 return 하는 이진탐색 함수
	static int binarySearch(int num) {
		int start,mid,end;
		start = 0;		
		end = N-1;		
		while(start<=end) {
			mid = (start+end) / 2;
			int val = array[mid];
			if(num == val) return 1;
			if(num>val) {
				start = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		return 0;
	}
}