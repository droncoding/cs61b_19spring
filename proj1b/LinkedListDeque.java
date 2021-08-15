public class LinkedListDeque<T> implements Deque<T>{

    private Node sentinel;
    private int size;

    public class Node{
        Node prev;
        T item;
        Node next;

        Node(Node prev,T item,Node next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size =0;
    }

    /*Creating a deep copy means that you create an entirely new LinkedListDeque,
    with the exact same items as other. However, they should be different objects,
    i.e. if you change other, the new LinkedListDeque you created should not change as well.
     */
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size =0;
        for (int i=0; i< other.size();i++){
            addLast((T)other.get(i));
        }
    }

    @Override
    public void addFirst(T item){
        Node newNode = new Node(sentinel,item,sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;

    }

    @Override
    public void addLast(T item){
        Node newLast = new Node(sentinel.prev,item,sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
        size+=1;
    }

    @Override
    public int size(){
        return size;
    }

    /*Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque(){
        Node p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node front = sentinel.next;
        front.next.prev = sentinel;
        T rv = front.item;
        sentinel.next = front.next;
        size -=1;
        return rv;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node last = sentinel.prev;
        T rv = last.item;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size -= 1;
        return rv;
    }

    @Override
    public T get(int index){
        if(index>size){
            return null;
        }else{
            Node p = sentinel.next;
            for (int i=0;i<index;i++){
                p = p.next;
            }
            return p.item;
        }
    }

    public T getRecursive(int index){
        if(index > size){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
    }

    private  T getRecursiveHelper(Node starter, int index){
        if(index ==0){
            return starter.item;
        }
        return getRecursiveHelper(starter.next,index-1);

    }


}
