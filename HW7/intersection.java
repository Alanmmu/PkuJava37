package collect;
import java.util.ArrayList;
//import java.util.LinkedHashSet;
import java.util.List;

public class intersection {
       public static void main(String[] arys){
    	   List<String> strlist=new ArrayList<String>();
           List<String> strlist1=new ArrayList<String>();
           /*for(int i=0;i<10;i++){
        	   strlist.add("a>>"+i);
        	   strlist.add("a>>"+(10-i));
           }*/
           strlist.add("ab");
           strlist.add("abc");
           strlist.add("wd");
           strlist1.add("ab");
           strlist1.add("cd");
           strlist1.retainAll(strlist);
           System.out.println("交集大小："+strlist1.size());
           for(int i=0;i<strlist1.size();i++){
        	   System.out.println(strlist1.get(i));
           }
       
       }
}
