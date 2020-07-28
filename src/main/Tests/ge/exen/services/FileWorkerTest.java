package ge.exen.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class FileWorkerTest {
    @Autowired
    IFileWorker worker;

    @Value("classpath:tests/foo.txt")
    Resource fooFile;

    @Test
    @DirtiesContext
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
    @DirtiesContext
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
