import com.project.KeywordEngine.KeywordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @Test
    public void loginTest() throws IOException {
        KeywordEngine keywordEngine = new KeywordEngine();
        keywordEngine.startEngine("login");
    }
}
