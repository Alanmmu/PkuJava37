/*HW 20151112
 *Ҫ��
 * ��дһ������,ʵ�����¹��ܣ�
 *1. ��ȡ������ͬ���ı��ļ�����������ļ��ܹ��Ĵʻ�������в��ظ��ĵ��ʣ�
 *2. ��һ������ͬʱ�����������ļ��еĽ������ʴʻ��
 *3. ͳ�����������ļ��ʻ���и��԰�������������
 *��ҵ����ʱ�䣺11��19����������ǰ
 *�ύ��ʽ��gitHub��ÿ���ύ
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
			String[] splittedStr = lineStr.split("[\\s,!?.;������]");
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
			String[] splittedStr = lineStr2.split("[\\s,.!?;������]");
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
	// java����map: http://blog.csdn.net/tjcyjd/article/details/11111401
	public static void main(String args[]){
		Map<String, Integer> Dict = text_to_Dict();
		int vocAll  = Dict.size();
		List<String> both = new ArrayList <String>();
		List<String> all = new ArrayList <String>();
		int p = 0, q = 0;
		int voc1 = 0, voc2 = 0;//�����ļ��ʻ���и��԰�����������
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