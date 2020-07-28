package ge.exen.DAO;

import ge.exen.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class PostSQLDAOTest {

    @Autowired
    private PostsSQLDao postsSQLDao;

    Post samplePost = null;

    @BeforeEach
    public void setUp(){
        samplePost = new Post();
        samplePost.setExamId(1);
        samplePost.setFromId(1);
        samplePost.setText("Dzaan dzerski gamocdaa, aqedan yvela chemtan!! --lekva");
        samplePost.setDate(new Timestamp(System.currentTimeMillis()));
    }

    


}
