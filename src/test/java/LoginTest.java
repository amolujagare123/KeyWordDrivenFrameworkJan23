import com.project.KeywordEngine.KeywordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    KeywordEngine keywordEngine;
    @Test
    public void loginTest() throws IOException {
         keywordEngine = new KeywordEngine();
        keywordEngine.startEngine("login");
    }
}
