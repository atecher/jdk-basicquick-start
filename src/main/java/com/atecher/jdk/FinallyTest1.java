package com.atecher.jdk;

/**
 * Created by hanhongwei on 2017/9/29.
 */
public class FinallyTest1 {

    public String returnTest(){
        System.out.println("tun return");

        return "finish";
    }

    public String finallyTest(){
        try{
            System.out.println("run finallTest");
            return returnTest();
        }catch (Exception e){
            System.out.println("run exception");
            return "exception";
        }finally {
            System.out.println("run finally");
        }
    }

    public static void main(String[] args) {
        FinallyTest1 test=new FinallyTest1();
        System.out.println(test.finallyTest());
    }
}
