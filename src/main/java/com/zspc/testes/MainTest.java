package com.zspc.testes;

import java.util.Random;
import java.util.UUID;

/**
 * @author zhuansunpengcheng
 * @create 2019-01-31 6:18 PM
 **/
public class MainTest {


    public static void main(String[] args) {

        Random random = new Random();


        for (int i = 0; i<20; i++) {

            int a = random.nextInt(4)+1;
            System.out.println(a);

        }

//
//        String a = UUID.randomUUID().toString().replace("-", "").substring(0, 11);
//
//        System.out.println(a);

    }

}
