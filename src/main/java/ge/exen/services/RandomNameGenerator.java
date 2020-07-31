package ge.exen.services;

import java.util.Random;

public class RandomNameGenerator {
    public static String generate(int size){
        String ret = new String();
        for(int i = 0; i < size; i ++){
            ret = ret + (char)('A' + new Random().nextInt(26));
        }
        return ret;
    }
}
