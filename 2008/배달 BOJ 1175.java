import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 배달 백준 1175
public class Main {
	
	static int N, M;					// N 세로 길이, M 가로 길이
	static char[][] map;				// 지도
	static int ans;						// 정답
	static final int IMPOSSIBLE = 987654321; // 불가능할 경우를 위한 답
	
	static int[] dy = {0,0,1,-1};		// 세로방향 이동
	static int[] dx = {1,-1,0,0}; 		// 가로방향 이동
	
	static boolean[][][][] visit;		// [세로좌표][가로좌표][0~3 : 동서남북]
	                                    // [0 방문 X / 1 C1만 방문 / 2 C2만 방문] 
	
	// S와 C의 위치를 담기위한 class
	static class location{
		int y,x;	// 세로, 가로 좌표

		public location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static location start, c1, c2;
	static boolean isC1Empty;
	
	// BFS 를 위한 정보 class
	static class info{
		int y,x;	// 세로, 가로 좌표
		int time, dir, status;	// 현재까지 시간, 방향(동서남북 0~3), 배달상태
							    // 배달상태 : 0 방문 X / 1 C1만 방문 / 2 C2만 방문
		
		public info(int y, int x, int time, int dir, int status) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.dir = dir;
			this.status = status;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; 
		
		// N, A 배열 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 세로N, 가로M만큼 지도 선언 후 입력
		map = new char[N+1][M+1];
		isC1Empty = true;
		for(int i=1; i<=N; i++) {
			String str = br.readLine();
			for (int j=1; j<=M; j++) {
				map[i][j] = str.charAt(j-1);
				if(map[i][j]=='S') {
					start = new location(i,j);
					map[i][j] = '.';
				}
				else if (map[i][j]=='C') {
					// C1 입력 전이면
					if (isC1Empty) {
						c1 = new location(i,j);
						isC1Empty = false;
					}
					// C1 입력 됐으면 C2에 입력
					else {
						c2 = new location(i,j);
					}
					map[i][j] = '.';
				}
			}
		}
		
		// BFS 시작 전 불가능하다고 가정하고 시작
		ans = IMPOSSIBLE;
		// BFS 시작 전 visit 배열 초기화
		// [세로좌표][가로좌표][0~3 : 동서남북]
		// [0 방문 X / 1 C1만 방문 / 2 C2만 방문]
		visit = new boolean[N+1][M+1][4][3];
		
		// 출발점은 true 찍고 시작
		visit[start.y][start.x][0][0] = visit[start.y][start.x][1][0]
		= visit[start.y][start.x][2][0] = visit[start.y][start.x][3][0] = true;
		
		
		Queue<info> queue = new LinkedList<>();
		// 출발점 정보 넣기 (방향정보는 0~3과 중복을 막기 위해 임의로 -1을 넣어줌)
		queue.offer(new info(start.y, start.x, 0, -1, 0));

		while (!queue.isEmpty()) {
			info cur = queue.poll();

			// 1. 현재 좌표가 배달지역(C1 or C2) 일 경우 true로 바꿔줌
			// 배달상태(status) : 0 방문 X / 1 C1만 방문 / 2 C2만 방문
			
			// 1-1. 현재 좌표가 C1인 경우
			if (cur.y == c1.y && cur.x == c1.x) {
				// 1-1-1. status 가 0 또는 2일 경우 +1 (C1 방문 추가)
				if (cur.status != 1) cur.status++;
			}
			// 1-2. 현재 좌표가 C2인 경우
			else if (cur.y == c2.y && cur.x == c2.x) {
				// 1-2-1. status 가 0 또는 1일 경우 +2 (C2 방문 추가)
				if (cur.status <= 1) cur.status+=2;
			}
			
			// 2. BFS에서 답이 최초로 등장 = 정답
			if (cur.status == 3) {
				ans = cur.time;
				break;
			}
			
			// 3. 동서남북 돌면서 Queue에 넣기
			for (int i = 0; i < 4; i++) {
				// 연속으로 같은 방향 이동 불가
				if (cur.dir == i) continue;
				
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				// 1) 지도범위    2) 이동가능지역('.')    3) visit 여부 확인 후 queue에 넣기
				if (ny>=1 && ny<=N && nx>=1 && nx<=M && map[ny][nx] == '.'
						&& visit[ny][nx][i][cur.status] == false) {
					visit[ny][nx][i][cur.status] = true;
					queue.offer(new info(ny, nx, cur.time+1, i, cur.status));
				}
			}
		}
		
		// 답이 없을 경우 -1
		if (ans == IMPOSSIBLE) ans = -1;
		
		// 정답출력
		bw.write(String.valueOf(ans));
		
		bw.flush();
		bw.close();
		br.close();
	}
}