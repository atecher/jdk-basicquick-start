package com.atecher.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpOnOutOfMemoryError 可以使虚拟机在出现堆内存溢出时Dump出当前的内存堆转储快照以便时候分析
 *
 */
public class JavaVMHeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<>();
        for(;;){
            list.add(new OOMObject());
        }
    }
}
