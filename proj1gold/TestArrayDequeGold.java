import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testArray(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution();

        StringBuilder outpurStr = new StringBuilder();

        for (int i =0; i< 100; i++){
            int methodNumber = StdRandom.uniform(4);
            int itemNumber = StdRandom.uniform(10);

            if (sad.isEmpty()){
                methodNumber /=2;
            }

            switch (methodNumber){
                case 0:
                    sad.addFirst(itemNumber);
                    sol.addFirst(itemNumber);
                    outpurStr.append("addFirst(").append(itemNumber).append(")\n");
                    break;
                case 1:
                    sad.addLast(itemNumber);
                    sol.addLast(itemNumber);
                    outpurStr.append("addLast(").append(itemNumber).append(")\n");
                    break;
                case 2:
                    int sadf = sad.removeFirst();
                    int solf = sol.removeFirst();
                    outpurStr.append("removeFirst()\n");
                    assertEquals(outpurStr.toString(),sadf,solf);
                    break;
                case 3:
                    int sadl = sad.removeLast();
                    int soll = sol.removeLast();
                    outpurStr.append("removeLast()\n");
                    assertEquals(outpurStr.toString(),sadl,soll);
                    break;
            }

        }





    }

}


