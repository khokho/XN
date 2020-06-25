package ge.exen.services;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class FileWorkerTest {
    @Autowired
    FileWorkerInterface worker;

    @Value("classpath:tests/foo.txt")
    Resource fooFile;

    @Test
    public void testUnnamed(){
        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile testFile = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        String res = worker.storeMultiPartFile("./files/testFolder", testFile, null);
        assertEquals("./files/testFolder/foo.txt", res);

    }

    @Test
    public void testNamed(){



        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile testFile = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        String res = worker.storeMultiPartFile("./files/testFolder", testFile, "bar");
        assertEquals("./files/testFolder/bar.txt", res);

    }
}
