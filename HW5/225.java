class MyStack {
    Queue<Integer> queue = new LinkedList<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        Queue<Integer> temp = new LinkedList<Integer>();
        while(!queue.isEmpty()){
            temp.add(queue.remove());
        }
        //temp.add(x);
        queue.add(x);
        while(!temp.isEmpty()){
            queue.add(temp.remove());
        }
        //queue.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        queue.remove();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        //if(queue.poll()==null) return true;
        return queue.isEmpty();
        //return false;
    }
}