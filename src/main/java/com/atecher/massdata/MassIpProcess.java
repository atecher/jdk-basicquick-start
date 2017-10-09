package com.atecher.massdata;

import java.io.*;
import java.util.*;

/**
 * Created by hanhongwei on 2017/9/29.
 */
public class MassIpProcess {

    private static int MAX_NUM=100000000;
    private static final int HASH_NUM=100;
    private static String FILE_NAME="d:/ips.txt";
    private static String FOLDER="d:/ips";
    private static Map<String, Integer> map=new HashMap<>();
    private static Map<String, Integer> finalMap=new HashMap<>();

    private static String generateIp() {
        return "192.168." + (int) (Math.random() * 255) + "."
                + (int) (Math.random() * 255) + "\n";
    }
    private static void generateIpsFile() {
        File file = new File(FOLDER);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < MAX_NUM; i++) {
                fileWriter.write(generateIp());
            }
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String hash(String ip) {
        long ipToLong = ipToLong(ip);
        return String.valueOf(ipToLong % HASH_NUM);
    }

    private static long ipToLong(String strIp) {
        long[] ip = new long[4];
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);

        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static void divideIpsFile() {
        File file = new File(FILE_NAME);
        Map<String, StringBuilder> map  = new HashMap<>();
        int count = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String ip;
            while ((ip = br.readLine()) != null) {
                String hashIp = hash(ip);
                if(map.containsKey(hashIp)){
                    StringBuilder sb = map.get(hashIp);
                    sb.append(ip).append("\n");
                    map.put(hashIp, sb);
                }else{
                    StringBuilder sb = new StringBuilder(ip);
                    sb.append("\n");
                    map.put(hashIp, sb);
                }
                count++;
                if(count == 4000000){
                    Iterator<String> it = map.keySet().iterator();
                    while(it.hasNext()){
                        String fileName = it.next();
                        File ipFile = new File(FOLDER + "/" + fileName + ".txt");
                        System.out.println(ipFile.getAbsolutePath());
                        if(!ipFile.exists()){
                            ipFile.createNewFile();
                        }
                        FileWriter fileWriter = new FileWriter(ipFile, true);
                        StringBuilder sb = map.get(fileName);
                        fileWriter.write(sb.toString());;
                        fileWriter.close();
                    }
                    count = 0;
                    map.clear();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void calculate() {
        File folder = new File(FOLDER);
        File[] files = folder.listFiles();
        FileReader fileReader;
        BufferedReader br;
        for (File file : files) {
            try {
                fileReader = new FileReader(file);
                br = new BufferedReader(fileReader);
                String ip;
                Map<String, Integer> tmpMap = new HashMap<String, Integer>();
                while ((ip = br.readLine()) != null) {
                    if (tmpMap.containsKey(ip)) {
                        int count = tmpMap.get(ip);
                        tmpMap.put(ip, count + 1);
                    } else {
                        tmpMap.put(ip, 0);
                    }
                }
                fileReader.close();
                br.close();
                count(tmpMap,map);
                tmpMap.clear();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(int i=0,limit=100;i<limit;i++){
            Map.Entry<String, Integer> item=  list.get(i);
            System.out.println("result IP : " + item.getKey() + " | count = " + item.getValue());
        }

    }

    private static void count(Map<String, Integer> pMap, Map<String, Integer> resultMap) {
        Iterator<Map.Entry<String, Integer>> it = pMap.entrySet().iterator();
        int max = 0;
        String resultIp = "";
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() > max) {
                max = entry.getValue();
                resultIp = entry.getKey();
            }
        }
        resultMap.put(resultIp,max);
    }

    public static void main(String[] args) {
//        step 1
//        generateIpsFile();
//        step 2
//        divideIpsFile();
//        step 3
        calculate();
    }

}
