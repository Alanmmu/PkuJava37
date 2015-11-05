import java.util.*;
class MinStack {
    private Stack<Integer> stack = new Stack<Integer>();  
    private int number=0;//记录栈内数字个数
    private int pos=0;//记录最小数位置
    private int minnum=2147483647;//记录最小数大小  
    public void push(int x) {
        stack.push(x);
        number++;
        if(x<minnum){
            minnum=x;
            pos=number;
        }
    }

    public void pop() {
        stack.pop();
        if(number==pos){
            minnum=-2147483648;
            pos=0;
        }
        number--;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if(pos!=0)return minnum;
        else {
            minnum=stack.peek();
            int []record=new int[number];
            int length=number;
            while(!stack.empty())
            {
                if(stack.peek()<minnum){
                    minnum=stack.peek();
                    pos=number;
                }
                record[number-1]=stack.peek();
                number--;
                stack.pop();
            }
            for(int i=0;i<length;i++)
            {
                stack.push(record[i]);
            }
            number=length;
            return minnum;
        }
    }

}
