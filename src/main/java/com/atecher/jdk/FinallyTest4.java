package com.atecher.jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanhongwei on 2017/9/29.
 */
public class FinallyTest4 {


    public Map<String,String> finallyTest(){
        Map<String,String> result=new HashMap<>();
        try{
            System.out.println("run finallTest");
            result.put("KEY","finish");
            return result;
        }catch (Exception e){
            System.out.println("run exception");
        }finally {
            System.out.println("run finally");
            result.put("KEY","finally");
            result=null;
        }
        return result;
    }

    public static void main(String[] args) {
        FinallyTest4 test=new FinallyTest4();
        Map<String,String> result = test.finallyTest();
        System.out.println(result.get("KEY"));
    }
}
