package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.ExamMaterialDao;
import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import ge.exen.models.ExamMaterial;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
@Transactional()
public class ExamServiceTest {
    @Autowired
    IExamService testObject;
    @Autowired
    ExamDao examDao;
    @Autowired
    ExamMaterialDao examMaterialDao;

    @Value("classpath:tests/foo.txt")
    Resource fooFile;

    @Test
    @DirtiesContext
    public void testExamService(){
        ExamDTO dto = new ExamDTO();
        dto.setStartDate("2000/01/01 15:30");
        dto.setFullName("foo");
        dto.setHours(1);
        dto.setMinutes(30);
        dto.setVariants(3);
        long id = testObject.process(dto);
        assertTrue(id > 0);

        Exam got = examDao.get(id);

        assertNotEquals(null, got);
        assertEquals(got.getDurationInMinutes(), (Integer)90);
        assertEquals(got.getFullName(), "foo");
        assertEquals(got.getStartDate(), "2000/01/01 15:30");
        assertEquals(got.getVariants(), (Integer)3);
    }


    @Test
    @DirtiesContext
    public void testFileStoring(){
        long id = 1;



        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        Map<String, MultipartFile> mp = new HashMap<>();
        mp.put("2", result);

        testObject.setFiles(mp, id);
        ExamMaterial got = examMaterialDao.get(id, 2);

        assertNotNull(got);
        assertEquals(2, got.getVar());
        assertEquals(id, got.getExamId());
        assertEquals(ExamMaterialService.directory.replace("%id%", "" + id) + "/foo.txt", got.getMaterialLink());
    }

/*
    @Test
    @DirtiesContext
    @Transactional
    public void lolTestFileStoring(){
        ExamDTO dto = new ExamDTO();
        dto.setStartDate("2000/01/01 15:30");
        dto.setFullName("foo");
        dto.setHours(1);
        dto.setMinutes(30);
        dto.setVariants(3);
        long id = testObject.process(dto);
        assertTrue(id > 0);



        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        Map<String, MultipartFile> mp = new HashMap<>();
        mp.put("1", result);

        testObject.setFiles(mp, id);
        ExamMaterial got = examMaterialDao.get(id, 1);

        assertNotEquals(null, got);
        assertEquals(1, got.getVar());
        assertEquals(id, got.getExamId());
        assertEquals(ExamMaterialService.directory.replace("%id%", ""+id) + "/foo.txt", got.getMaterialLink());
    }*/



}