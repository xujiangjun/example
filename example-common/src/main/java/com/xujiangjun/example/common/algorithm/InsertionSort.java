package com.xujiangjun.example.common.algorithm;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author xujiangjun
 * @date 2018-04-09 15:31
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] randomArray = new int[]{7, 6, 8, 5, 9, 4};
        int temp;
        for (int i = 1; i < randomArray.length; i++) {
            temp = randomArray[i];
            int j = i - 1;
            while (j >= 0 && randomArray[j] > temp){
                randomArray[j + 1] = randomArray[j];
                j--;
            }
            randomArray[j + 1] = temp;
        }
        System.out.println(Arrays.toString(randomArray));
    }
}
