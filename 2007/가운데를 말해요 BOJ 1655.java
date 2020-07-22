
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

// 가운데를 말해요 백준 1655
public class Main {
	
	static int N, input, ans;			// N 숫자 개수, input 입력받는 수, ans 출력할 답
	static int minSize, maxSize, dif;	// 각각 최소힙, 최대힙의 개수, 각 자료구조 개수의 차이
	
	static PriorityQueue<Integer> minHeap;
	static PriorityQueue<Integer> maxHeap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 최소힙 선언, comparator에서 오름차순정렬
		minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		// 최대힙 선언, comparator에서 내림차순정렬
		maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		// 최초크기 0으로 선언
		minSize = maxSize = 0;
		
		// N 입력
		N = Integer.parseInt(br.readLine());

		// 첫번째 값은 입력받 값이 곧 답이므로 입력 후 바로 출력하고 ans에 갱신
		ans = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(ans)+"\n");
		// ans를 최대힙에 넣기 (최대힙 : 작은 값들 중에 후보군)
		
		for (int i=2; i<=N; i++) {
			input = Integer.parseInt(br.readLine());
			// 1. input <= ans이면 최대힙에 넣기 (ans보다 작은 값 중에 최댓값이 그나마 다음 후보니까)
			if (input<=ans) {
				maxHeap.offer(input);
				maxSize++;
				
				// 작은값 개수가 1개 이상 많다면 중앙값을 바꿔줌 (짝수개일경우 작은값이 답이므로)
				dif = maxSize - minSize;
				if (dif>=1) {
					minHeap.offer(ans);
					minSize++;
					ans = maxHeap.poll();
					maxSize--;
				}
			}
			// 2. input > ans이면 최소힙에 넣기 (ans보다 큰 값 중 최솟값이 그나마 다음 후보니까)
			else { // if (input>ans) {
				minHeap.offer(input);
				minSize++;
				
				// 큰값이 개수가 2개 이상 많다면 중앙값을 바꿔줌 (짝수개일경우 작은값이 답이므로)
				dif = minSize - maxSize;
				if (dif>=2) {
					maxHeap.offer(ans);
					maxSize++;
					ans = minHeap.poll();
					minSize--;
				}
			}
			// 중앙값 출력
			bw.write(String.valueOf(ans)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}