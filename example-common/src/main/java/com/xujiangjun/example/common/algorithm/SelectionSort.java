package com.xujiangjun.example.common.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 找到数组中的最大元素，与数组最后一位元素交换。
 * https://juejin.im/post/5ab9ae9cf265da23830ae617
 * 
 * @author xujiangjun
 * @date 2018-04-09 15:13
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] randomArray = new int[]{7, 6, 8, 5, 9, 4};
        for (int i = 0; i < randomArray.length - 1; i++) {
            // 新的趟数、将角标重新赋值为0
            int pos = 0;
            for (int j = 0; j < randomArray.length - 1 - i; j++) {
                if(randomArray[j] > randomArray[pos]){
                    pos = j;
                }
            }
            int temp = randomArray[randomArray.length - 1 - i];
            randomArray[randomArray.length - 1 - i] = randomArray[pos];
            randomArray[pos] = temp;
        }
        System.out.println(Arrays.toString(randomArray));
    }
}
