import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator ob5 = new OffByN(5);

    @Test
    public void testEqualChars(){
        assertTrue(ob5.equalChars('a','f'));
    }


}
