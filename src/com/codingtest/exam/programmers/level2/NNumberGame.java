package com.codingtest.exam.programmers.level2;

import java.util.HashMap;

public class NNumberGame {

    // 진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p 가 주어진다.
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        // 1. 모든 인원이 필요한 숫자만큼 n 진수로 변환
        // String number = convertN(n, t * m);

        int num = 0;
        String number = "";
        while (number.length() < t * m) {
            number += Integer.toString(num, n);
            num++;
        }
        System.out.println(number);

        // 2. 튜브가 필요한 숫자만 반환
        while (answer.length() < t) {
            answer += number.substring(p - 1, p);
            p += m;
        }
        System.out.println(answer);
        return answer;
    }
    public String convertN(int n, int len) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(10, "A");
        hashMap.put(11, "B");
        hashMap.put(12, "C");
        hashMap.put(13, "D");
        hashMap.put(14, "E");
        hashMap.put(15, "F");

        String target = "0";
        int number = 1;

        while (target.length() < len) {
            int q = number, r = 0;
            StringBuilder str = new StringBuilder();
            // number 의 n 진수 구하기
            while (q > 0) {
                r = q % n;
                q /= n;
                if (r > 9) {
                    str.append(hashMap.get(r));
                } else {
                    str.append(r);
                }
            }
            target += str.reverse();
            number++;
        }
        return target;
    }

    public static void main(String[] args) {
        NNumberGame nNumberGame = new NNumberGame();
        nNumberGame.solution(16, 16, 2, 2);
    }
}
