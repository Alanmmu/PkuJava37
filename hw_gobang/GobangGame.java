import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
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
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
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
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],chessman);
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
	//此部分代码应可以写成两个函数 这次没能实现
	public boolean five(int posX,int posY,String ico)// 是否成5
	{
		String[][] board = chessboard.getBoard();
		board[posX][posY]=ico;
		if(isWon(posX,posY,ico)){board[posX][posY]="十";return true;}
		board[posX][posY]="十";
		return false;
	}
	public boolean live_four(int posX,int posY,String ico)// 判断活4
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int nor=4;//记录连续个数
		int []flag={0,0,0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin==0||end==0)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1){board[posX][posY]="十";return true;}
			}
		}
		board[posX][posY]="十";
		return false;
	}
	public int dead_four(int posX,int posY,String ico)//计算死4的个数
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_four=0;
		int nor=4;//记录连续个数
		int []flag={0,0,0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin+end!=1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
					num_of_dead_four++;
				}
			}
		}
		board[posX][posY]="十";
		return num_of_dead_four;
	}
	public int live_three(int posX,int posY,String ico)//计算活3的个数
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_live_three=0;
		int nor=3;//记录连续个数
		int []flag={0,0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin==0||end==0)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
					num_of_live_three++;
				}
			}
		}
		board[posX][posY]="十";
		return num_of_live_three;
	}
	public int dead_three(int posX,int posY,String ico)//计算死3的个数
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_three=0;
		int nor=3;//记录连续个数
		int []flag={0,0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin+end!=1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
					num_of_dead_three++;
				}
			}
		}
		board[posX][posY]="十";
		return num_of_dead_three;
	}
	public int live_two(int posX,int posY,String ico)//计算活2的个数*******
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_live_two=0;
		int nor=2;//记录连续个数
		int []flag={0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin==0||end==0)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
					num_of_live_two++;
				}
			}
		}
		board[posX][posY]="十";
		return num_of_live_two;
	}
	public int dead_two(int posX,int posY,String ico)//计算死2的个数
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_two=0;
		int nor=2;//记录连续个数
		int []flag={0,0};
		int begin=0;
		int end=0;
		int result=0;
		board[posX][posY]=ico;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)
				{
					if(posX-(nor-j)*direct[i][0]>=0&&posX-(nor-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-j)*direct[i][1]>=0&&posY-(nor-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="十")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="十")end=1;
				}
				if(begin+end!=1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
					num_of_dead_two++;
				}
			}
		}
		board[posX][posY]="十";
		return num_of_dead_two;
	}
	/**
	 * 计算机随机下棋
	 */
	public int[] computerDo() {
		String[][] board = chessboard.getBoard();
		
		int posX=0;
		int posY=0;
		int [][]value=new int[15][15];
		for(int i=0;i<Chessboard.BOARD_SIZE;i++)
			for(int j=0;j<Chessboard.BOARD_SIZE;j++)
			{
				if(board[i][j]=="●"||board[i][j]=="○")value[i][j]=-1000000;
				else {//为各种棋型赋权值  权值可以进一步调整
					value[i][j]=0;
					if(five(i,j,"○"))value[i][j]+=100000;//成5
					if(five(i,j,"●"))value[i][j]+=90000;
					if(live_four(i,j,"○"))value[i][j]+=10000;//活4
					if(live_four(i,j,"●"))value[i][j]+=9000;
					if(dead_four(i,j,"○")>=2)value[i][j]+=10000;//双死4
					if(dead_four(i,j,"●")>=2)value[i][j]+=9000;
					if(dead_four(i,j,"○")==1&&live_three(i,j,"○")>=1)value[i][j]+=10000;//死4活3
					if(dead_four(i,j,"●")==1&&live_three(i,j,"●")>=1)value[i][j]+=9000;
					if(live_three(i,j,"○")>=2)value[i][j]+=5000;//双活3
					if(live_three(i,j,"●")>=2)value[i][j]+=4500;
					if(live_three(i,j,"○")==1&&dead_three(i,j,"○")>=1)value[i][j]+=1000;//活3死3
					if(live_three(i,j,"●")==1&&dead_three(i,j,"●")>=1)value[i][j]+=900;
					if(dead_four(i,j,"○")==1)value[i][j]+=500;//死4
					if(dead_four(i,j,"●")==1)value[i][j]+=450;
					if(live_three(i,j,"○")==1)value[i][j]+=200;//活3
					if(live_three(i,j,"●")==1)value[i][j]+=180;
					if(live_two(i,j,"○")>=2)value[i][j]+=100;//双活2
					if(live_two(i,j,"●")>=2)value[i][j]+=90;
					if(dead_three(i,j,"○")>=1)value[i][j]+=50;//死3
					if(dead_three(i,j,"●")>=1)value[i][j]+=45;
					if(live_two(i,j,"○")==1)value[i][j]+=10;//活2
					if(live_two(i,j,"●")==1)value[i][j]+=9;
					if(dead_two(i,j,"○")>=1)value[i][j]+=5;//死2
					if(dead_two(i,j,"●")>=1)value[i][j]+=4;
					
				}
			}
		//输出每个位置的权值    测试用
		/*for(int i=0;i<Chessboard.BOARD_SIZE;i++)
		{
			for(int j=0;j<Chessboard.BOARD_SIZE;j++)
				System.out.printf("%d ",value[i][j]);
			System.out.print("\n");
		}*/
		int best_value=-1000000;
		for(int i=0;i<Chessboard.BOARD_SIZE;i++)
			for(int j=0;j<Chessboard.BOARD_SIZE;j++)
				if(value[i][j]>best_value)
				{
					best_value=value[i][j];
					posX=i;
					posY=j;
					//System.out.printf("%d %d",i,j);
				}
		int[] result = { posX, posY };
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
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int nor=WIN_COUNT;//记录连续个数
		int []flag={0,0,0,0,0};
		int result=0;
		for(int i=0;i<4;i++){
			for(int j=0;j<nor;j++)
			{
				for(int k=0;k<nor;k++)
					if(posX-(nor-1-j-k)*direct[i][0]>=0&&posX-(nor-1-j-k)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(nor-1-j-k)*direct[i][1]>=0&&posY-(nor-1-j-k)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(nor-1-j-k)*direct[i][0]][posY-(nor-1-j-k)*direct[i][1]]==ico)
							flag[k]=1;
				result=1;
				for(int l=0;l<nor;l++)
					if(flag[l]==0)
					{
						result=0;
						for(int lp=0;lp<nor;lp++)
							flag[lp]=0;
					}
				if(result==1)return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
