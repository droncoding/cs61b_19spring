public class OffByN implements CharacterComparator{
    int ob;

    public OffByN(int N){
        ob = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x-y;
        if (diff == ob| diff==-ob){
            return true;
        }
        return false;
    }
}
