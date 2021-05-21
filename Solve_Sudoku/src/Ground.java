class Sudoku {
	private int done=1;
	private int[][] origin;
	public int[][] result;
	private int[][][] working = new int[10][10][10];
	// 1 �ʱ�ȭ �޼ҵ�
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
	// 1 while���� ������ ���� ���ö����� ������ �����Ѵ�.
	public void operate() {
		int b = 0;// ���ѷ����� �������� �ּ����� ���
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
	// 2 ��� ���� �����ϸ� ���° ĭ���� ����
	public int labeling(int i, int j) {
		int row = i/3;
		int col = j/3;
		return 3*row+col+1;
	}
	
	// 2 working ��Ʈ���� ������,���� �����ϴ� ���ڸ� ����
	public void deleteRowCol() {
		// working ��Ʈ�� �ϳ� ��Ƽ� �� �࿡ �ִ� ���ڸ� ����. -> ����ð��� �ٿ��� �� ����
		for(int i=0;i<9;i++) for(int j=0;j<9;j++)
			if(result[i][j]==0) // result ĭ�� ���������
				for(int k=1;k<10;k++) {
					if(working[i][j][k]!=0 && findRow(i,k)) {
						working[i][j][k]=0;
					}
					if(working[i][j][k]!=0 && findCol(j,k)) {
						working[i][j][k]=0;
					}
				}
	}
	// 2 �Է¹��� row�� num�̶�� ���ڰ� �ִ��� Ȯ���Ѵ�.
	public boolean findRow(int row, int num) {
		for (int j=0;j<9;j++) {
			if(result[row][j]==num) {
				return true;
			}
		}
		return false;
	}
	// 2 �Է¹��� col�� num�̶�� ���ڰ� �ִ��� Ȯ���Ѵ�.
	public boolean findCol(int col, int num) {
		for (int i=0;i<9;i++) {
			if(result[i][col]==num) {
				return true;
			}
		}
		return false;
	}
	// 2 working ��Ʈ���� 3x3��Ͽ� �����ϴ� ���� ����
	public void deleteBlock() {
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
			if(result[i][j]==0)
				for(int k=1;k<10;k++) {
					if(working[i][j][k]!=0 && findBlock(labeling(i,j),k)) {
						working[i][j][k]=0;
					}
				}
	}
	// 2 �Է¹��� label �ڽ��� num�̶�� ���ڰ� �ִ��� Ȯ���Ѵ�.
	public boolean findBlock(int label, int num) {
		label--;
		int row = (label/3)*3;
		int col = (label%3)*3;
		for(int i=0;i<3;i++)for(int j=0;j<3;j++) {
			if(result[row+i][col+j]==num) return true;
		}
		return false;
	}
	

	// 3 �ϳ��� block�� �ϳ��� �� �Ǵ� ���� �ʿ�� �Ҷ�, �ش� line�� working�� �����Ѵ�.
	public void oneBlocktoline() {
		
	}
	// 3 working�� ���ڰ� ������ ����������, �ش� �� �Ǵ� ĭ���� ���ڰ� �� �ϳ��� �����ϴ� ���
	public void onlyOne() {
		
	}

	
	// 4 ���̳� ĭ�� n���� ���ڰ� ���θ� �ʿ�� �Ҷ�, ������ ĭ�� �ش� ���ڸ� ����
	public void nNumberToall() {
		
	}
	// 4 �ΰ��� block���� �ΰ��� �� �Ǵ� ���� �ʿ�� �Ҷ�, ������ block�� �ش� line�� ����
	public void twoBlockToBlock() {
		
	}
	
	// 1 Working�� ������� �ϳ� ������ result�� �ִ´�.
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
	// 1 ������� ����ϴ� �޼ҵ�
	public void printResult(int[][] result) {
		System.out.println("******���********");
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
	// 1 Working�� ����ϴ� �޼ҵ�
	public void printWorking() {
		System.out.println("******��Ʈ********");
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
		int [][]set1 = // ���̵� : �ſ� ����
			{{2,0,0,6,3,0,0,1,0},
			{0,5,1,0,2,0,7,9,3},
			{4,0,3,1,9,7,5,0,0},
			{0,0,0,0,0,9,0,3,2},
			{0,6,5,0,7,0,1,4,0},
			{1,3,0,8,0,0,0,0,0},
			{0,0,9,3,6,2,4,0,7},
			{3,7,6,0,8,0,2,5,0},
			{0,2,0,0,5,1,0,0,9}};
		int [][]set2 = // ���̵� :  �ణ�����
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
