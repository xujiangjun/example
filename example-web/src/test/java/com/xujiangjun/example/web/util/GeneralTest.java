package com.xujiangjun.example.web.util;

import com.google.common.collect.Lists;
import com.xujiangjun.example.web.model.NestedClass;
import org.junit.Test;

import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xujiangjun
 * @date 2017-09-22 14:21
 */
public class GeneralTest {

    @Test
    public void testProviders(){
        for (Provider provider : Security.getProviders()){
            System.out.println(provider);
            for (Map.Entry<Object, Object> entry : provider.entrySet()){
                System.out.println("\t" + entry.getKey() + ":" + entry.getValue());
            }
        }
    }

    @Test
    public void testKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        System.out.println(keyPair.getPrivate() + "\n\n" + keyPair.getPublic());
    }

    @Test
    public void test(){
        int number = 5;
        int max = Integer.highestOneBit((number - 1) << 1);
        System.out.println(max);
        int[] a = new int[5];
        a[0] = 2;
        System.out.println("length:" + a.length);
        System.out.println("Math.min:" + (int)Math.min(1.65f, 3));
        System.out.println("Math.max:" + (int)Math.max(1.25f, 3.6f));
    }

    public static void main(String[] args) {
        NestedClass.StaticInner staticInner = new NestedClass.StaticInner();
        staticInner.innerMethod();

        NestedClass.staticMethod();
        GeneralTest generalTest = new GeneralTest();
        Class clazz = generalTest.getClass();
        System.out.println(clazz.getName() + ":" + clazz.getSimpleName());
    }

}
