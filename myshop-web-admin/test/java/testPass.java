import org.junit.Test;
import org.springframework.util.DigestUtils;

public class testPass {
    @Test
    public void testpass() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
