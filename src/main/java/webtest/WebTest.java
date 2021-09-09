package webtest;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.TestNGAntTask.Mode.junit;

import org.junit.runner.RunWith;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.*;
import com.meterware.httpunit.WebResponse;
import junit.framework.*;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;

public class WebTest {
    @Test
    public void mainContentTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://127.0.0.1:8080/learning_center/");
        assertEquals("Main page", resp.getTitle());
        WebLink[] links = resp.getLinks();
        assertEquals(links.length, 2);
        assertEquals(links[0].getText(), "Departments");
        assertEquals(links[1].getText(), "Staff Members");
        assertEquals(links[0].getURLString(), "departments");
        assertEquals(links[1].getURLString(), "staff");
    }
}
