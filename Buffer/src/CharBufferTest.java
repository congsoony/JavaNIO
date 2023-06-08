import java.nio.CharBuffer;

public class CharBufferTest {
    public static void main(String[] args) {
        CharBuffer c= CharBuffer.allocate(10);
        System.out.println("position : "+c.position());

        c.put("hello 지훈!");
        System.out.println("position : "+c.position());

    }
}
