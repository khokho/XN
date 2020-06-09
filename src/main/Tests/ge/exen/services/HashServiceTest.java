package ge.exen.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HashServiceTest {

    private static final String SALT = "SALT";


    @InjectMocks
    private HashService hashService;


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