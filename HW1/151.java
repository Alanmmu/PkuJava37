public class Solution {
    public String reverseWords(String s) {
        StringBuffer str = new StringBuffer();
        if(s.length() == 0) return s;
        if(s==null) return s;
        String[] st = s.split("\\s{1,}");
        for(int i = st.length-1;i>=0;--i){
            str.append(st[i]+" ");
        }
        //str.append(st[0]);
        //if(st.length!=0) str.append(st[0]);
        //s = str.toString();
        s = str.toString().trim();//֮ǰ��ȫɾ������trim()��ȥ��λ�ո�
        return s;
    }
}