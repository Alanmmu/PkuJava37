package collect;
import java.util.List;
import java.util.ArrayList;

public class bining {
       public static void main(String[] args){
    	   List<String> strlist=new ArrayList<String>();
    	   List<String> strlist1=new ArrayList<String>(); 
       /*for(int i=0;i<10;i++){
    	   strlist.add("a>>"+i);
    	   strlist1.add("a>>"+(10-i));
       }*/
       strlist.add("ab");
       strlist.add("abc");
       strlist.add("wd");
       strlist1.add("ab");
       strlist1.add("cd");
       strlist1.removeAll(strlist);
       strlist1.addAll(strlist);
       System.out.println("并集大小："+strlist1.size());
       for(int i=0;i<strlist1.size();i++){
    	     System.out.println(strlist1.get(i));
       }
    }
}
