package org.example.util;
import java.util.Random;
public class RandomIDGenerator {
    public static long generateId(){
        Random randomNumber =  new Random();
        long idRandomNumber = randomNumber.nextLong();
        if(idRandomNumber < 0){
            return idRandomNumber* (-1);
        }
        return idRandomNumber;
    }
}
