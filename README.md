스도쿠 문제를 내면 해답을 도출하는 프로그램.

문제 및 난이도의 설정은 'Andoku 2' 어플을 통해서 조절하였다.


사용 함수 정리

1. 기본 필요함수
// while문을 돌려서 답이 나올때까지 로직을 실행한다.
	public void operate()
// 행과 열을 대입하면 몇번째 칸인지 리턴
	public int labeling(int i, int j)
// working 시트에서 같은행,열에 존재하는 숫자를 삭제
	public void deleteRowCol()

// Working에 결과값이 하나 있으면 result에 넣는다.
	public void makeResult()
// 결과값을 출력하는 메소드
	public void printResult(int[][] result)
// Working을 출력하는 메소드
	public void printWorking()

2. 매우 쉬움 난이도를 위한 메소드
ㄴ 한줄 혹은 한블록(3x3)에 숫자가 있으면 경우의수를 삭제한다.

// 입력받은 row행에 num이라는 숫자가 있는지 확인한다.
	public boolean findRow(int row, int num)
// 입력받은 col행에 num이라는 숫자가 있는지 확인한다.
	public boolean findCol(int col, int num)
// working 시트에서 3x3블록에 존재하는 숫자 삭제
	public void deleteBlock()
// 입력받은 label 박스에 num이라는 숫자가 있는지 확인한다.
	public boolean findBlock(int label, int num)

3. 약간어려움 난이도를 위한 메소드
ㄴ 
