package com.atecher.jdk;

/**
 * Created by hanhongwei on 2017/9/29.
 */
public class FinallyTest3 {

    int returnValue=0;

    public int finallyTest(){
        try{
            System.out.println("run finallTest");
            return returnValue+=10;
        }catch (Exception e){
            System.out.println("run exception");
        }finally {
            System.out.println("run finally");
            returnValue+=20;
        }
        return returnValue;
    }

    public static void main(String[] args) {
        FinallyTest3 test=new FinallyTest3();
        System.out.println(test.finallyTest());
    }

}
