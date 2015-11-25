/*HW 20151112
 *要求：
 * 编写一个程序,实现以下功能：
 *1. 读取两个不同的文本文件，输出两个文件总共的词汇表（即所有不重复的单词）
 *2. 进一步计算同时出现在两个文件中的交集单词词汇表。
 *3. 统计上述两个文件词汇表中各自包含单词总数。
 *作业条件时间：11月19日下周三晚前
 *提交方式：gitHub，每人提交
 */
import java.io.*;
import java.util.*;
public class Census{
	public static Map<String, Integer> text_to_Dict(){
		Map<String, Integer> Dict = new HashMap<String, Integer>();
		
		try{
		BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\AlanLIU\\Desktop\\pics\\java\\1.txt"));
		String lineStr = null;
		while((lineStr=br1.readLine())!= null){
			String[] splittedStr = lineStr.split("[\\s,!?.;。，；]");
			for(String str:splittedStr){
				if(!str.trim().equals("")){
					Dict.put(str, 1);
				}
			}
		br1.close();
		}
		
		}catch(Exception e){
			System.out.println("Wrong input");
		}
		try{
		BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\AlanLIU\\Desktop\\pics\\java\\2.txt"));
		String lineStr2 = null;
		while((lineStr2=br2.readLine())!= null){
			String[] splittedStr = lineStr2.split("[\\s,.!?;。，；]");
			for(String str:splittedStr){
				if(!str.trim().equals("")){
					if(Dict.containsKey(str) && Dict.get(str) == 1){
						Dict.put(str, 3);
					}else{
						Dict.put(str, 2);
					}
				}
			}
		br2.close();
		}
		}catch(Exception e){
			System.out.println("Wrong input");
		}
		return Dict;
		
	}
	// java遍历map: http://blog.csdn.net/tjcyjd/article/details/11111401
	public static void main(String args[]){
		Map<String, Integer> Dict = text_to_Dict();
		int vocAll  = Dict.size();
		List<String> both = new ArrayList <String>();
		List<String> all = new ArrayList <String>();
		int p = 0, q = 0;
		int voc1 = 0, voc2 = 0;//两个文件词汇表中各自包含单词总数
		for(String key: Dict.keySet()){
			int l = Dict.get(key);
			if(l==1){
				voc1++;
			}else if(l==2){
				voc2++;
			}else if(l==3){
				voc1++;
				voc2++;
				both.add(key);
			}
			all.add(key);
		}
		System.out.println(all);
		System.out.println(both);
	}
}