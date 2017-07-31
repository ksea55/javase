package com.ksea.coucurrent.zxx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mexican on 2017/7/31.
 */
public class TestDemo {
    public static void main(String[] args) {
        String inputParams = "AABBAaa";
         System.out.println(get(inputParams));
        //System.out.println(get_(inputParams));
    }

    //这种方式当某个连续的值一样的时候，或者说 key一样的时候 value可能会呗覆盖
    public static String get(String inputParams) {

        Map<String, Integer> resultMap = new LinkedHashMap<>();

        if (null == inputParams || inputParams.isEmpty())
            return "";


        if (inputParams.length() == 1)
            return inputParams;

        char[] chars = inputParams.toCharArray();


        int count = 1;

        String src = String.valueOf(chars[0]);

        for (int i = 1; i < chars.length; i++) {

            String next = String.valueOf(chars[i]);

            if (src.equals(next)) {
                count += 1;
                continue;
            }
            resultMap.put(src, count);
            count = 1;
            src = String.valueOf(chars[i]);
        }
        resultMap.put(src, count);


        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Integer>> entries = resultMap.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            if (next.getValue() > 1) {
                sb.append(next.getKey() + "" + next.getValue());
            } else {

                sb.append(next.getKey());
            }
        }


        return sb.toString();
    }


    public static String get_(String inputParams) {

        if (null == inputParams || inputParams.isEmpty())
            return "";

        if (inputParams.length() == 1)
            return inputParams;


        StringBuilder sb = new StringBuilder();
        char src = inputParams.charAt(0);
        int count = 1;

        for (int i = 1; i < inputParams.length(); i++) {

            char next = inputParams.charAt(i);

            if (src == next) {
                count += 1;
                continue;
            }
            sb.append(src).append(count>1?count:"");
            count = 1;
            src = next;


        }

        sb.append(src).append(count>1?count:"");

        return sb.toString();
    }
}
