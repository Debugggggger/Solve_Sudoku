class Sudoku {
	private int done=1;
	private int[][] origin;
	public int[][] result;
	private int[][][] working = new int[10][10][10];
	// 1 초기화 메소드
	public Sudoku(int origin[][]) {
		this.origin = origin;
		this.result = origin;
		for(int i=0;i<9;i++)for(int j=0;j<9;j++) {
				if(this.origin[i][j]==0)
					for(int k=0;k<10;k++)
						if(k==0) working[i][j][0]=0;
						else this.working[i][j][k]=k;
				else working[i][j][0]=1;
			}
	}
	// 1 while문을 돌려서 답이 나올때까지 로직을 실행한다.
	public void operate() {
		int b = 0;// 무한루프를 막기위한 최소한의 노력
		while(this.done!=0) {
			if(b>100) {
				break;
			}
			/*
			deleteRowCol();
			deleteBlock();
			makeResult();
			 */
			b++;
		}
		
		printResult(result);
		printWorking();
	}
	// 2 행과 열을 대입하면 몇번째 칸인지 리턴
	public int labeling(int i, int j) {
		int row = i/3;
		int col = j/3;
		return 3*row+col+1;
	}
	
	// 2 working 시트에서 같은행,열에 존재하는 숫자를 삭제
	public void deleteRowCol() {
		// working 시트를 하나 잡아서 그 행에 있는 숫자를 뺀다. -> 실행시간을 줄여줄 수 있음
		for(int i=0;i<9;i++) for(int j=0;j<9;j++)
			if(result[i][j]==0) // result 칸이 비어있을때
				for(int k=1;k<10;k++) {
					if(working[i][j][k]!=0 && findRow(i,k)) {
						working[i][j][k]=0;
					}
					if(working[i][j][k]!=0 && findCol(j,k)) {
						working[i][j][k]=0;
					}
				}
	}
	// 2 입력받은 row에 num이라는 숫자가 있는지 확인한다.
	public boolean findRow(int row, int num) {
		for (int j=0;j<9;j++) {
			if(result[row][j]==num) {
				return true;
			}
		}
		return false;
	}
	// 2 입력받은 col에 num이라는 숫자가 있는지 확인한다.
	public boolean findCol(int col, int num) {
		for (int i=0;i<9;i++) {
			if(result[i][col]==num) {
				return true;
			}
		}
		return false;
	}
	// 2 working 시트에서 3x3블록에 존재하는 숫자 삭제
	public void deleteBlock() {
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
			if(result[i][j]==0)
				for(int k=1;k<10;k++) {
					if(working[i][j][k]!=0 && findBlock(labeling(i,j),k)) {
						working[i][j][k]=0;
					}
				}
	}
	// 2 입력받은 label 박스에 num이라는 숫자가 있는지 확인한다.
	public boolean findBlock(int label, int num) {
		label--;
		int row = (label/3)*3;
		int col = (label%3)*3;
		for(int i=0;i<3;i++)for(int j=0;j<3;j++) {
			if(result[row+i][col+j]==num) return true;
		}
		return false;
	}
	

	// 3 하나의 block이 하나의 행 또는 열을 필요로 할때, 해당 line의 working을 삭제한다.
	public void oneBlocktoline() {
		
	}
	// 3 working에 숫자가 여러개 남아있지만, 해당 줄 또는 칸에서 숫자가 딱 하나만 존재하는 경우
	public void onlyOne() {
		
	}

	
	// 4 줄이나 칸에 n개의 숫자가 서로를 필요로 할때, 나머지 칸은 해당 숫자를 포기
	public void nNumberToall() {
		
	}
	// 4 두개의 block에서 두개의 행 또는 열을 필요로 할때, 나머지 block은 해당 line을 포기
	public void twoBlockToBlock() {
		
	}
	
	// 1 Working에 결과값이 하나 있으면 result에 넣는다.
	public void makeResult() {
		this.done=0;
		for(int i=0;i<9;i++) for(int j=0;j<9;j++) {
			int count=0;
			int num=0;
			for(int k=1;k<10;k++)
				if(working[i][j][k]!=0) {
					this.done++;
					count++;
					num=k;
				}
			if(count==1) {
				result[i][j]=num;
				working[i][j][0]=2;
				for(int k=1;k<10;k++) {
					working[i][j][k]=0;
				}
			}
		}
	}
	// 1 결과값을 출력하는 메소드
	public void printResult(int[][] result) {
		System.out.println("******결과********");
		System.out.print("-------------");
		System.out.println("");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(j==0) {
					System.out.print("|");
				}
				if(result[i][j]==0) {
					System.out.print(" ");
				}else {
					System.out.print(result[i][j]);
				}
				if(j%3==2) {
					System.out.print("|");
				}
			}
			System.out.println("");
			if(i%3==2) {
				System.out.print("-------------");
				System.out.println("");
			}
		}
	}
	// 1 Working을 출력하는 메소드
	public void printWorking() {
		System.out.println("******시트********");
		System.out.print("-------------------------------------------------------------------------------------------------------");
		System.out.println("");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(j==0) {
					System.out.print("|");
				}
				for (int k=0;k<10;k++) System.out.print(working[i][j][k]);
				System.out.print("|");
				if(j%3==2) {
					System.out.print("|");
				}
			}
			System.out.println("");
			if(i%3==2) {
				System.out.print("-------------------------------------------------------------------------------------------------------");
				System.out.println("");
			}
		}
	}
}
public class Ground {
	public static void main(String[] args) {
		int [][]set1 = // 난이도 : 매우 쉬움
			{{2,0,0,6,3,0,0,1,0},
			{0,5,1,0,2,0,7,9,3},
			{4,0,3,1,9,7,5,0,0},
			{0,0,0,0,0,9,0,3,2},
			{0,6,5,0,7,0,1,4,0},
			{1,3,0,8,0,0,0,0,0},
			{0,0,9,3,6,2,4,0,7},
			{3,7,6,0,8,0,2,5,0},
			{0,2,0,0,5,1,0,0,9}};
		int [][]set2 = // 난이도 :  약간어려움
			{{6,7,0,0,0,0,0,0,8},
			{0,4,0,0,0,0,7,9,0},
			{0,0,8,0,0,0,0,6,1},
			{2,0,0,4,5,0,0,7,0},
			{0,1,0,7,8,6,0,5,0},
			{0,3,0,0,1,9,0,0,4},
			{7,5,0,0,0,0,8,0,0},
			{0,6,3,0,0,0,0,4,0},
			{4,0,0,0,0,0,0,1,7}};
		Sudoku s = new Sudoku(set2);

		s.operate();
		s.printResult(s.result);
	}
}
