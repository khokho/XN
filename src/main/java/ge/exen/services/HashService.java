package ge.exen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService implements PasswordEncoder {

    BCryptPasswordEncoder encoder;

    /**
     * Salt makes hashes not googlable and more secure
     */
    private final String salt = "kho";

    public HashService() {
        encoder = new BCryptPasswordEncoder(11);
    }

    /**
     * @param charSequence String to hash
     * @return hashed string+salt
     */
    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encode(charSequence + salt);
    }

    /**
     * @param pass inputed string
     * @param hash hash to match
     * @return if charSequence match s then true
     */
    @Override
    public boolean matches(CharSequence pass, String hash) {
        return encoder.matches(pass + salt, hash);
    }

}
