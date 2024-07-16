package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 字符串最大长度
        int MAX_LENGTH = 200;
        // 随机数组每个值，在1~MAX_VALUE之间等概率随机
        int MAX_VALUE = 100000;
        // testTimes : 测试次数
        int testTimes = 100000;
        System.out.println("--测试开始--");
        for (int i = 0; i < testTimes; i++) {
            // 随机得到一个长度，长度在[0~N-1]
            int n = (int) (Math.random() * MAX_LENGTH);
            // 得到随机字符串
            String str = generateString(n, MAX_VALUE);

            //进行各自的操作，得到各自结果
            String result1 = getNumByString(str);
            String result2 = getNumByStringTrue(str);
            if (!result1.equals(result2)) {
                System.out.println("出错的字符串是:" + str);
                System.out.println("Expect: " + result1);
                System.out.println("But Actually: " + result2);
                break;
            }
            System.out.println(i + " Right " + str);
        }
        System.out.println("--测试结束--");
    }



    public static String getNumByString(String originalStr) {
        // 1.检查字符串是否为Null和空串
        if (originalStr == null || originalStr.isEmpty()) {
            return "String is null or empty !";
        }

        StringBuilder numString = new StringBuilder();
        // 默认没有小数点
        boolean hasDot = false;
        int dotPos = -1;
        int len = 0;
        for (char c : originalStr.toCharArray()) {
            if (Character.isDigit(c)) {
                len++;
                numString.append(c);
            } else if (c == '.' && !hasDot) {
                dotPos = len++;
                numString.append(c);
                hasDot = true;
            }

            if (hasDot && (len - dotPos > 2)) {
                break;
            }
        }

        if (!hasDot && numString.length() > 0) {
            numString.append(".00");
        } else if (numString.length() - numString.indexOf(".") <= 2) {
            numString.append("0");
        }
        return numString.toString();

    }

    private static String getNumByStringTrue(String str) {
        if (str == null || str.isEmpty()) {
            return "String is null or empty !";
        }

        String number = str.replaceAll("[^0-9.]", "");
        int dotIndex = number.indexOf(".");
        if (dotIndex != -1 && number.length() > dotIndex + 3) {
            number = number.substring(0, dotIndex + 3);
        } else if (dotIndex == -1) {
            number += ".00";
        } else if(number.length() < dotIndex + 3){
            number += "0";
        }
        return number;
    }

    public static String generateString(int n, int v) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // 首先，添加n个随机字母
        for (int i = 0; i < n; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        // 然后，添加一个随机的整数
        int num = random.nextInt(v);
        sb.append(num);
        // 最后，添加一个随机的小数部分
        sb.append(".");
        num = random.nextInt(10000);  // 假设小数部分最多有5位
        sb.append(num);
        // sb.append(String.format("%05d", num));
        return sb.toString();
    }

}
