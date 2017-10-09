package com.atecher.jdk;

/**
 * 验证：finally中的return会覆盖try中的return
 */
public class FinallyTest2 {

    public String finallyTest(){
        try{
            System.out.println("run finallTest");
            return "finish";
        }catch (Exception e){
            System.out.println("run exception");
            return "exception";
        }finally {
            System.out.println("run finally");
            return "finally";
        }
    }

    public static void main(String[] args) {
        FinallyTest2 test=new FinallyTest2();
        System.out.println(test.finallyTest());
    }
}
