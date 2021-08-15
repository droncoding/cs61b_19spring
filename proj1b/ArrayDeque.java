public class ArrayDeque<T> implements Deque<T>{


    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private double MIN_USAGE_RATIO;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        nextLast = 3;
        MIN_USAGE_RATIO = 0.25;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        nextLast = 3;
        MIN_USAGE_RATIO = 0.25;
        for (int i=0; i< other.size();i++){
            addLast((T)other.get(i));
        }
    }

    private int plusOne(int index){
        return (index+1)%items.length;
    }

    private int minusOne(int index){
        return (index - 1+ items.length) % items.length;
    }

    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int startp = plusOne(nextFirst);
        for (int i=0; i<size; i++){
            newArray[i] = items[startp];
            startp = plusOne(startp);
        }
        items = newArray;
        nextFirst = capacity -1;
        nextLast = size;
    }


    @Override
    public void addFirst(T item){
        if (size == items.length){
            resize(size*2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    @Override
    public void addLast(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast]=item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if(index < 0||index>=size){
            return null;
        }
        int actualindex = (plusOne(nextFirst)+ index)%items.length;
        return items[actualindex];
    }
    /*Prints the items in the deque from first to last, separated by a space.
        Once all the items have been printed, print out a new line.
         */
    @Override
    public void printDeque(){
        int startp = plusOne(nextFirst);
        for(int i =0; i< size; i++ ){
            System.out.print(items[startp]+" ");
            startp = plusOne(startp);
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst(){
        if(size ==0){
            return null;
        }
        int first = plusOne(nextFirst);
        T removedItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;

        if(size<items.length*MIN_USAGE_RATIO){
            resize(items.length/2);
        }

        return  removedItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast(){
        if(size ==0){
            return null;
        }
        int last = minusOne(nextLast);
        T removedItem = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;

        if(size<items.length*MIN_USAGE_RATIO){
            resize(items.length/2);
        }

        return removedItem;
    }


}
