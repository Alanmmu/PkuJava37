package wzcontrol;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class wzq {
	
	    // 定义达到赢条件的棋子数目
		private final int WIN_COUNT = 5;
		// 定义用户输入的X坐标
		private int posX = 0;
		// 定义用户输入的X坐标
		private int posY = 0;
		// 定义棋盘
		private Chessboard chessboard;

		/**
		 * 空构造器
		 */
		public wzq() {
		}

		/**
		 * 构造器，初始化棋盘和棋子属性
		 * 
		 * @param chessboard
		 *            棋盘类
		 */
		public wzq(Chessboard chessboard) {
			this.chessboard = chessboard;
		}

		/**
		 * 检查输入是否合法。
		 * 
		 * @param inputStr
		 *            由控制台输入的字符串。
		 * @return 字符串合法返回true,反则返回false。
		 */
		public boolean isValid(String inputStr) {
			// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
			String[] posStrArr = inputStr.split(",");
			try {
				posX = Integer.parseInt(posStrArr[0]) - 1;
				posY = Integer.parseInt(posStrArr[1]) - 1;
			} catch (NumberFormatException e) {
				chessboard.printBoard();
				System.out.println("请以(数字,数字)的格式输入：");
				return false;
			}
			// 检查输入数值是否在范围之内
			if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
					|| posY >= Chessboard.BOARD_SIZE) {
				chessboard.printBoard();
				System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
						+ ",请重新输入：");
				return false;
			}
			// 检查输入的位置是否已经有棋子
			String[][] board = chessboard.getBoard();
			if (board[posX][posY] != "十") {
				chessboard.printBoard();
				System.out.println("此位置已经有棋子，请重新输入：");
				return false;
			}
			return true;
		}

		/**
		 * 开始下棋
		 */
		public void start() throws Exception {
			// true为游戏结束
			boolean isOver = false;
			chessboard.initBoard();
			chessboard.printBoard();
			// 获取键盘的输入
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String inputStr = null;
			// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
			while ((inputStr = br.readLine()) != null) {
				isOver = false;
				if (!isValid(inputStr)) {
					// 如果不合法，要求重新输入，再继续
					continue;
				}
				// 把对应的数组元素赋为"●"
				String chessman = Chessman.BLACK.getChessman();
				chessboard.setBoard(posX, posY, chessman);
				// 判断用户是否赢了
				if (isWon(posX, posY, chessman)) {
					isOver = true;

				} else {
					// 计算机随机选择位置坐标
					int[] computerPosArr = computerDo();
					chessman = Chessman.WHITE.getChessman();
					chessboard.setBoard(computerPosArr[0], computerPosArr[1],
							chessman);
					// 判断计算机是否赢了
					if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
						isOver = true;
					}
				}
				// 如果产生胜者，询问用户是否继续游戏
				if (isOver) {
					// 如果继续，重新初始化棋盘，继续游戏
					if (isReplay(chessman)) {
						chessboard.initBoard();
						chessboard.printBoard();
						continue;
					}
					// 如果不继续，退出程序
					break;
				}
				chessboard.printBoard();
				System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
			}
		}

		/**
		 * 是否重新开始下棋。
		 * 
		 * @param chessman
		 *            "●"为用户，"○"为计算机。
		 * @return 开始返回true，反则返回false。
		 */
		public boolean isReplay(String chessman) throws Exception {
			chessboard.printBoard();
			String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
					: "很遗憾，您输了，";
			System.out.println(message + "再下一局？(y/n)");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if (br.readLine().equals("y")) {
				// 开始新一局
				return true;
			}
			return false;

		}
		
		class Pos{
			private int posX;
			private int posY;
			private int val;
		}
	//获取位置的权值	
		public Pos getPos1(int arg[][]){
			Pos m = new Pos();
			m.posX = 0;
			m.posY = 0;
			m.val = 0;
			for(int i = 0; i < Chessboard.BOARD_SIZE; i++){
				for(int j = 0; j < Chessboard.BOARD_SIZE; j++){
					if(arg[i][j] > m.val){
						m.posX = i;
						m.posY = j;
						m.val = arg[i][j];
					}
				}
			}
			return m;
		}
		
		public int gVal(int posX, int posY, String ico){
			int val = 0;
			for(int i = WIN_COUNT - 1; i > 0; i--){
				if(isnum(posX, posY, ico, i + 1)){
					return i;
				}
			}
			return val;
		}
		
		public boolean isnum (int posX, int posY, String ico, int num){//计算得出连子数
			String[][] board = chessboard.getBoard();
			/*System.out.println("board:");
			for(int i = 0; i < Chessboard.BOARD_SIZE; i++){
				for(int j = 0; j < Chessboard.BOARD_SIZE; j++){
					System.out.print(board[i][j]+" ");
				}
				System.out.print("\n");}
			*/
			//检查该棋子所在行
			int countX = 0;//记录该棋子所在行相同棋子数
			int y = posY - num;
			if(y < 0)  
				y = 0;
			while(y < Chessboard.BOARD_SIZE && y < posY + num ){//开始检测计算周边棋子
				if(board[posX][y] == ico) countX++;
				else if(y == posY) countX++;
				else{//有间断置为0
					countX = 0;
				}
				if(countX == num) 
					return true;
				y++;
			}
			//检查该棋子所在列
			int countY = 0;//记录该棋子所在列相同棋子数
			int x = posX - num;
			if(x < 0)
				x = 0;
			while(x < Chessboard.BOARD_SIZE && x < posX + num   ){//开始检测计算周边棋子
				if(board[x][posY] == ico)
					countY++;
				//if(board[x][posY] == ico || x == posX) countY++;
				else{//有间断置为0
					countY = 0;
				}
				if(countY == num)
					return true;
				x++;
			}
			//检查该棋子所在正对角线
			int count1 = 0;
			int x1 = posX - num , y1 = posY - num ;
			//行越界
			if(x1 < 0){
				x1 = 0;
				y1 = posY - posX;
			}
			//列越界
			if(y1 < 0){
				y1 = 0;
				x1 = posX - posY;
			}
			while(x1 < posX + num && y1 < posY + num && x1 < Chessboard.BOARD_SIZE && y1 < Chessboard.BOARD_SIZE){
				if(board[x1][y1] == ico) count1++;
				else count1 = 0;
				if(count1 == num) return true;
				x1++;
				y1++;
			}
			//检查该棋子所在反对角线
			int count2 = 0;
			int x2 = posX + num , y2 = posY - num ;
			//行越界
			if(x2 >= Chessboard.BOARD_SIZE){
				x2 = Chessboard.BOARD_SIZE - 1;
				y2 = posY - Math.abs(posX - x2);
			}
			//列越界
			if(y2 < 0){
				x2 = posX + Math.abs(posY - y2);
				y2 = 0;
			}
			while(x2 >= 0 && x2 > posX - num && y2 < Chessboard.BOARD_SIZE && y2 < posY + num){
				if(board[x2][y2] == ico) count2++;
				else count2 = 0;
				if(count2 == num ) return true;
				x2--;
				y2++;
			}
			return false;
		}

		/**
		 * 计算机随机下棋
		 */
		public int[] computerDo() {
			
			//int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			//int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			String[][] board = chessboard.getBoard();
			//while (board[posX][posY] != "十") {
				//posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				//posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			//}
			int computer[][] = new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE];
			//用于存储棋盘上，电脑下棋的每个位置的权值 
			int people[][] = new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE];
			//用于存储棋盘上，人下棋的每个位置的权值
			int i=0;
			int j=0;
			while(board[i][j]=="+"&&i<Chessboard.BOARD_SIZE&&j<Chessboard.BOARD_SIZE){
				//for (i = 0; i < Chessboard.BOARD_SIZE;i++ ){
					//for(j = 0; i < Chessboard.BOARD_SIZE; j++){
						computer[i][j] = gVal(i, j, Chessman.BLACK.getChessman());
						people[i][j] = gVal(i, j, Chessman.WHITE.getChessman());
						i++;
						j++;
			}
			
			for( i = 0; i < Chessboard.BOARD_SIZE; i++){
				for( j = 0; j < Chessboard.BOARD_SIZE; j++){
					computer[i][j] = 0;
					people[i][j] = 0;
				}
			}
			Pos cp = getPos1(computer);//获得电脑下棋时优势位置
			Pos pp = getPos1(people);//获得人下棋时的优势位置
			Pos m;
			if(cp.val < pp.val){
				m=pp;
			}else
				m=cp;	
		    int[] result = { m.posX, m.posY };
			return result;
		}
		
		
		/**
		 * 判断输赢
		 * 
		 * @param posX
		 *            棋子的X坐标。
		 * @param posY
		 *            棋子的Y坐标
		 * @param ico
		 *            棋子类型
		 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
		 */
		public boolean isWon(int posX, int posY, String ico) {
			String[][] board = chessboard.getBoard();
			//检查该棋子所在行
			int countX = 0;//记录该棋子所在行相同棋子数
			int y = posY - WIN_COUNT;
			if(y < 0) y = 0;
			while( y < Chessboard.BOARD_SIZE && y < posY + WIN_COUNT  ){//仅排查该棋子前后4个棋子，最大至边界
				if(board[posX][y] == ico) countX++;
				else{//有间断置为0
					countX = 0;
				}
				if(countX == WIN_COUNT) return true;
				y++;
			}
			//检查该棋子所在列
			int countY = 0;//记录该棋子所在列相同棋子数
			int x = posX - WIN_COUNT ;
			if(x < 0) x = 0;
			while(x < Chessboard.BOARD_SIZE &&  x < posX + WIN_COUNT  ){//仅排查该棋子前后4个棋子，最大至边界
				if(board[x][posY] == ico) countY++;
				else{//有间断置为0
					countY = 0;
				}
				if(countY == WIN_COUNT) return true;
				x++;
			}
			//检查该棋子所在正对角线
			int count1 = 0;
			int x1 = posX - WIN_COUNT, y1 = posY - WIN_COUNT ;
			//行越界
			if(x1 < 0){
				x1 = 0;
				y1 = posY - posX;
			}
			//列越界
			if(y1 < 0){
				y1 = 0;
				x1 = posX - posY;
			}
			while(x1 < posX + WIN_COUNT && y1 < posY + WIN_COUNT && x1 < Chessboard.BOARD_SIZE && y1 < Chessboard.BOARD_SIZE){
				if(board[x1][y1] == ico) count1++;
				else count1 = 0;
				if(count1 == WIN_COUNT) return true;
				x1++;
				y1++;
			}
			//检查该棋子所在反对角线
			int count2 = 0;
			int x2 = posX + WIN_COUNT , y2 = posY - WIN_COUNT ;
			//行越界
			if(x2 >= Chessboard.BOARD_SIZE){
				x2 = Chessboard.BOARD_SIZE - 1;
				y2 = posY - Math.abs(posX - x2);
			}
			//列越界
			if(y2 < 0){
				x2 = posX + Math.abs(posY - y2);
				y2 = 0;
			}
			while( x2 >= 0 && x2 > posX - WIN_COUNT && y2 < Chessboard.BOARD_SIZE && y2 < posY + WIN_COUNT  ){
				if(board[x2][y2] == ico) count2++;
				else count2 = 0;
				if(count2 == WIN_COUNT) return true;
				x2--;
				y2++;
			}
			return false;
		}

		public static void main(String[] args) throws Exception {

			wzq gb = new wzq(new Chessboard());
			gb.start();
		}
	}


