package com.xujiangjun.example.common.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * 俩俩交换，大的放在后面，第一次排序后最大值已在数组末尾。
 * 优化：如果一趟排序后也没有交换位置，那么该数组已有序～
 * https://juejin.im/post/5ab9ae9cf265da23830ae617
 *
 * 自然排序可以考虑Arrays.sort(randomArray);
 *
 * @author xujiangjun
 * @date 2018-04-09 14:46
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] randomArray = new int[]{7, 6, 8, 5, 9, 4};
        for (int i = 0; i < randomArray.length - 1; i++) {
            boolean isChange = false;
            for (int j = 0; j < randomArray.length - 1 - i; j++) {
                if(randomArray[j] > randomArray[j + 1]){
                    int temp = randomArray[j + 1];
                    randomArray[j + 1] = randomArray[j];
                    randomArray[j] = temp;
                    isChange = true;
                }
            }
            if(!isChange){
                break;
            }
        }
        System.out.println(Arrays.toString(randomArray));
    }
}
