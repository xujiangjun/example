package com.xujiangjun.example.web.model;

/**
 * 嵌套类：静态内部类、成员内部类、方法内部类、匿名内部类
 *
 * 内部类与包含它的外部类有比较密切的关系，而与其他类关系不大，
 * 定义在类内部，可以实现对外部完全隐藏，可以有更好的封装性，代码实现上也往往更为简洁。
 * https://mp.weixin.qq.com/s?__biz=MzIxOTI1NTk5Nw==&mid=2650047210&idx=1&sn=e5c0565f04b4c67241e180a2592281fb&scene=21#wechat_redirect
 *
 * @author xujiangjun
 * @date 2017-11-29 09:57
 */
public class NestedClass {

    private int a = 1;

    private static int staticA = 10;

    public static class StaticInner{
        private int b = 0;
        private static int staticB = 21;
        public void innerMethod(){
            System.out.println("inner " + staticA);
        }
    }

    public class Inner {
        private int b = 0;
        public void innerMethod(){
            System.out.println("outer a " +a);
            NestedClass.this.action();
        }
    }

    public void testStaticInner(){
        StaticInner staticInner = new StaticInner();
        staticInner.innerMethod();
    }

    private void action(){
        System.out.println("action");
    }

    public static void staticMethod(){

    }
}
