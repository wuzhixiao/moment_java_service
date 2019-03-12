package com.nujnay.moment.server.weibo;

import java.util.ArrayList;
import java.util.List;

public class test {
    private static int num = 0;
    //    private static int num = 0;
    private static List<Integer> list;

    public static void main(String[] args) {
        int numer[] = {3, 5, 6, 7, 8, 11};

        list = new ArrayList();

        for (int i = 0; i < numer.length; i++) {
            int a = numer[i];
            callatz1005(a);
            for (int j = 0; j < list.size(); j++) {
                if (a == list.get(j)) {

                }
            }

        }

    }

    private static void callatz(int n) {
        ++num;
        if (n % 2 == 0) {
            n = n / 2;
            System.out.print(n + " ");
        } else {
            n = (3 * n + 1) / 2;
            System.out.print(n + " ");
        }
        if (n != 1) {
            callatz(n);
        } else {
            System.out.println(num);
        }
    }

    private static void callatz1005(int n) {

        if (n % 2 == 0) {
            n = n / 2;
            list.add(n);
        } else {
            n = (3 * n + 1) / 2;
            list.add(n);
        }
        if (n != 1) {
            callatz1005(n);
        } else {
            System.out.println(num);
        }

    }
}
