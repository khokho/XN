package ge.exen.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class HashServiceTest {


    @Autowired
    private PasswordEncoder hashService;


    /**
     * generates n hashes for each:
     * checks that it matches with itself
     * checks that it does not match with hashes already generated
     */
    @Test
    public void basicHashing(){
        Random rnd = new Random();
        List<String> lastStrings = new ArrayList<>();
        List<String> lastHashes = new ArrayList<>();
        for(int i=0; i<10; i++){
            StringBuilder s = new StringBuilder();
            for(int l=0; l<rnd.nextInt(10); l++){
                s.append(rnd.nextInt(26) + 'a');
            }
            String curHash = hashService.encode(s);
            System.out.println(curHash);
            assertTrue(hashService.matches(s, curHash));
            for(int j=0; j<lastStrings.size(); j++){
                assertEquals(s.toString().equals(lastStrings.get(j))
                                ,hashService.matches(s, lastHashes.get(j)));
            }
            lastHashes.add(curHash);
            lastStrings.add(s.toString());
        }
    }


}