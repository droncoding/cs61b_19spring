package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;

    private int fillCount;

    private T[] rb;


    public ArrayRingBuffer(int capacity) {

         rb= (T[]) new Object[capacity];
         first = 0;
         last =0;
         fillCount = 0;
    }

    private class ARBiterator implements Iterator<T>{
        private int Pos;
        private int count;

        public ARBiterator(){
            Pos = first;
            count =0;
        }

        @Override
        public boolean hasNext(){
            if(count<fillCount()){
                return true;
            }
            return false;
        }

        @Override
        public T next(){
            Pos += 1;
            if(Pos==capacity() ){
                Pos =0;
            }
            T returnv = rb[Pos];
            count += 1;
            return returnv;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ARBiterator();
    }


    @Override
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last +=1;
        fillCount += 1;
        if(last == capacity()){
            last =0;
        };
    }


    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T rv = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first += 1;
        if (first == capacity()){
            first = 0;
        }
        return rv;

    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }else {
            return rb[first];
        }
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o.getClass() != this.getClass()){
            return false;
        }

        ArrayRingBuffer<T> castedo = (ArrayRingBuffer<T>) o;

        if (castedo.fillCount() != this.fillCount()){
            return false;
        }

        Iterator<T> seer = this.iterator();
        Iterator<T> oseer = castedo.iterator();
        while (seer.hasNext()&& oseer.hasNext()){
            if(seer.next() != oseer.next()){
                return false;
            }
        }
        return true;
    }

}
