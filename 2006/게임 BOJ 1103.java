import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 게임 백준 BOJ 1103

public class Main {

	static int N, M, sol;
	static boolean lFlag;
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { -1, 1, 0, 0 };
	static int[][] map, dp;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		sol = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M]; // 지도
		dp = new int[N][M]; // dp 저장
		visit = new boolean[N][M]; // 방문확인 (무한 loop 확인용)
		for (int i = 0; i < N; i++) {
			String inputSt = br.readLine();
			for (int j = 0; j < M; j++) {
				// int로 맞춰서 map에 넣어줌
				if (inputSt.charAt(j) == 'H')
					map[i][j] = 10; // 구멍인 경우 10으로 처리
				else
					map[i][j] = inputSt.charAt(j) - 48;
			}
		}

		// DFS로 깊이우선탐색 + DP로 가지치기
		visit[0][0] = true; // (0,0) 무한loop 확인용 true 마킹
		lFlag = false; // 무한loop 안 빠진다고 가정하고 출발
		dfs(0, 0, 1); // (0,0)에서 출발

		if (lFlag) bw.write(String.valueOf(-1));
		else bw.write(String.valueOf(sol));

		br.close();
		bw.flush();
		bw.close();
	}

	static void dfs(int y, int x, int cnt) {
		// 답 최신화
		if (cnt > sol)
			sol = cnt;
		dp[y][x] = cnt; // 가지치기용 dp 배열에 현재 cnt 마킹
		for (int i = 0; i < 4; i++) {
			int num = map[y][x]; 
			int ny = dy[i] * num + y; // 현재 좌표에 숫자*방향 만큼의 숫자로 새로운 좌표
			int nx = dx[i] * num + x;

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 10)
				continue; // 지도 범위를 넘어서거나 구멍이면 continue
			if (visit[ny][nx]) { // 방문한 지점에 돌아왔으므로 무한loop 가능
				lFlag = true;
				return;
			}
			if (dp[ny][nx] > cnt) continue;
			visit[ny][nx] = true;
			dfs(ny, nx, cnt+1);
			visit[ny][nx] = false;
		}
	}
}