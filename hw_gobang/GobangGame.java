import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}
	//�˲��ִ���Ӧ����д���������� ���û��ʵ��
	public boolean five(int posX,int posY,String ico)// �Ƿ��5
	{
		String[][] board = chessboard.getBoard();
		board[posX][posY]=ico;
		if(isWon(posX,posY,ico)){board[posX][posY]="ʮ";return true;}
		board[posX][posY]="ʮ";
		return false;
	}
	public boolean live_four(int posX,int posY,String ico)// �жϻ�4
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int nor=4;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
				}
				if(begin==0||end==0)
				{
					result=0;
					begin=0;
					end=0;
					for(int lp=0;lp<nor;lp++)
						flag[lp]=0;
				}
				if(result==1){board[posX][posY]="ʮ";return true;}
			}
		}
		board[posX][posY]="ʮ";
		return false;
	}
	public int dead_four(int posX,int posY,String ico)//������4�ĸ���
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_four=0;
		int nor=4;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
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
		board[posX][posY]="ʮ";
		return num_of_dead_four;
	}
	public int live_three(int posX,int posY,String ico)//�����3�ĸ���
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_live_three=0;
		int nor=3;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
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
		board[posX][posY]="ʮ";
		return num_of_live_three;
	}
	public int dead_three(int posX,int posY,String ico)//������3�ĸ���
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_three=0;
		int nor=3;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
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
		board[posX][posY]="ʮ";
		return num_of_dead_three;
	}
	public int live_two(int posX,int posY,String ico)//�����2�ĸ���*******
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_live_two=0;
		int nor=2;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
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
		board[posX][posY]="ʮ";
		return num_of_live_two;
	}
	public int dead_two(int posX,int posY,String ico)//������2�ĸ���
	{
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int num_of_dead_two=0;
		int nor=2;//��¼��������
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
						if(board[posX-(nor-j)*direct[i][0]][posY-(nor-j)*direct[i][1]]=="ʮ")begin=1;
					if(posX-(-1-j)*direct[i][0]>=0&&posX-(-1-j)*direct[i][0]<=Chessboard.BOARD_SIZE-1&&
							posY-(-1-j)*direct[i][1]>=0&&posY-(-1-j)*direct[i][1]<=Chessboard.BOARD_SIZE-1)
						if(board[posX-(-1-j)*direct[i][0]][posY-(-1-j)*direct[i][1]]=="ʮ")end=1;
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
		board[posX][posY]="ʮ";
		return num_of_dead_two;
	}
	/**
	 * ������������
	 */
	public int[] computerDo() {
		String[][] board = chessboard.getBoard();
		
		int posX=0;
		int posY=0;
		int [][]value=new int[15][15];
		for(int i=0;i<Chessboard.BOARD_SIZE;i++)
			for(int j=0;j<Chessboard.BOARD_SIZE;j++)
			{
				if(board[i][j]=="��"||board[i][j]=="��")value[i][j]=-1000000;
				else {//Ϊ�������͸�Ȩֵ  Ȩֵ���Խ�һ������
					value[i][j]=0;
					if(five(i,j,"��"))value[i][j]+=100000;//��5
					if(five(i,j,"��"))value[i][j]+=90000;
					if(live_four(i,j,"��"))value[i][j]+=10000;//��4
					if(live_four(i,j,"��"))value[i][j]+=9000;
					if(dead_four(i,j,"��")>=2)value[i][j]+=10000;//˫��4
					if(dead_four(i,j,"��")>=2)value[i][j]+=9000;
					if(dead_four(i,j,"��")==1&&live_three(i,j,"��")>=1)value[i][j]+=10000;//��4��3
					if(dead_four(i,j,"��")==1&&live_three(i,j,"��")>=1)value[i][j]+=9000;
					if(live_three(i,j,"��")>=2)value[i][j]+=5000;//˫��3
					if(live_three(i,j,"��")>=2)value[i][j]+=4500;
					if(live_three(i,j,"��")==1&&dead_three(i,j,"��")>=1)value[i][j]+=1000;//��3��3
					if(live_three(i,j,"��")==1&&dead_three(i,j,"��")>=1)value[i][j]+=900;
					if(dead_four(i,j,"��")==1)value[i][j]+=500;//��4
					if(dead_four(i,j,"��")==1)value[i][j]+=450;
					if(live_three(i,j,"��")==1)value[i][j]+=200;//��3
					if(live_three(i,j,"��")==1)value[i][j]+=180;
					if(live_two(i,j,"��")>=2)value[i][j]+=100;//˫��2
					if(live_two(i,j,"��")>=2)value[i][j]+=90;
					if(dead_three(i,j,"��")>=1)value[i][j]+=50;//��3
					if(dead_three(i,j,"��")>=1)value[i][j]+=45;
					if(live_two(i,j,"��")==1)value[i][j]+=10;//��2
					if(live_two(i,j,"��")==1)value[i][j]+=9;
					if(dead_two(i,j,"��")>=1)value[i][j]+=5;//��2
					if(dead_two(i,j,"��")>=1)value[i][j]+=4;
					
				}
			}
		//���ÿ��λ�õ�Ȩֵ    ������
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
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		int [][]direct={{0,1},{1,1},{1,-1},{1,0}};
		int nor=WIN_COUNT;//��¼��������
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
