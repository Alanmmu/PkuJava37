package collect;

 import java.io.File;
 import java.io.FileInputStream;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.List;
 import java.util.ArrayList;

public class collection {

	public static void readTxtFile(String filePath, List list){
		try{
			String encoding = "GBK";
			File file=new File(filePath);
			if(file.isFile()&&file.exists()){//判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while((lineTxt = bufferedReader.readLine()) != null){
				   //System.out.println(lineTxt);
				list.add(lineTxt);
			}
			read.close();
			}else{
				System.out.println("找不到指定文件");
			}
		}catch (Exception e){
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		//String filePath1 = filePath;
		//String filePath2 = filePath;
		
	}
	public static void union (String file1,String file2){
		List<String> strList1=new ArrayList<String>();
 	    List<String> strList2=new ArrayList<String>(); 
 	    //String strList1=readTxtFile(filePath1);
 	   readTxtFile(file1,strList1);
 	   readTxtFile(file2,strList2);
 	   
 	   //strList1.addAll(strList2);
 	   strList1.removeAll(strList2);
 	   strList1.addAll(strList2);   
 	   
 	   System.out.println("union"+strList1);//并集
	}
	
	public static void intersection(String file1,String file2){
		List<String> strList1=new ArrayList<String>();
 	    List<String> strList2=new ArrayList<String>(); 
 	    
 	   readTxtFile(file1,strList1);
 	   readTxtFile(file2,strList2);
 	   
 	   strList1.retainAll(strList2);
 	   System.out.println("intersection"+strList1);//交集
 	  }
	public static void complementary(String file1,String file2){
		List<String> strList1=new ArrayList<String>();
 	    List<String> strList2=new ArrayList<String>(); 
 	    List<String> strList=new ArrayList<String>(); 
 	    
 	    readTxtFile(file1,strList1);
 	    readTxtFile(file2,strList2);
 	    
 	    int size1 = strList1.size();
 	    int size2 = strList2.size();
 	    
 	    strList.clear();         
 	    strList.addAll(strList1);
 	    //strList.removeAll(strList2);
 	    //strList = strList1;
 	    strList.retainAll(strList2);
 	     //strList1.retainAll(strList2);
 	    int subsize1 = strList.size();
 	    double count1 = (double)(size1-subsize1)/size1;
 	    System.out.println("sub1:"+subsize1);      //补集1的概率
 	    System.out.println("probability1:"+count1);
 	    
 	    strList.clear();
 	    strList.addAll(strList2);
 	    //strList = strList2;
 	    //strList.removeAll(strList1);
 	    strList.retainAll(strList1);;
 	    int subsize2 = strList.size();
 	    double count2 = (double)(size2-subsize2)/size2;
 	    System.out.println("sub2:"+subsize2);    //补集2的概率
 	    System.out.println("probability2:"+count2);
	}
	
	public static void main (String [] args){
		String file1 = "D:\\work\\list1.txt";
		String file2 = "D:\\work\\list2.txt";
		union(file1,file2);
		intersection(file1,file2);
		complementary(file1,file2);
		
		
	}
	
}
