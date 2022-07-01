package com.codingtest.exam.thisiscodingtest.ch12;

import java.util.*;

// 외벽 점검
// 원형으로 주어진 완전탐색 문제를 해결할 수 있는지 파악
// bit mask 나, permutation 등을 활용할 수 있는지 파악

public class Q13 {
    List<List<Integer>> cases = new ArrayList<>();

    public int solution(int n, int[] weak, int[] dist) {
        final int WEAK = weak.length;
        final int DIST = dist.length;
        int answer = DIST + 1;
        int[] circle = new int[WEAK + WEAK];

        for (int i = 0; i < WEAK; i++) {
            circle[i] = weak[i];
        }
        for (int i = WEAK; i < circle.length; i++) {
            circle[i] = weak[i - WEAK] + n;
        }

        // 친구를 선택해 나열하는 방법을 모두 고려
        permutation(DIST, 0, dist, new int[DIST], new boolean[DIST]);

        for (int i = 0; i < cases.size(); i++) {
            List<Integer> friends = cases.get(i);
            for (int j = 0; j < WEAK; j++) {
                int count = 1;
                int start = circle[j];
                for (int k = j; k < j + WEAK; k++) {
                    if (circle[k] - start > friends.get(count - 1)) {
                        System.out.println(circle[k] + ", " + start);
                        count += 1;
                        start = circle[k];
                        if (count > DIST) break;
                    }
                }
                answer = Math.min(answer, count);
            }
        }

        // 취약 지점을 전부 점검할 수 없는 경우
        if (answer > DIST) {
            return -1;
        }
        return answer;
    }

    // 순열
    public void permutation(int r, int k, int[] arr, int[] selected, boolean[] used) {
        if (k == r) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < selected.length; i++) {
                temp.add(selected[i]);
            }
            cases.add(temp);
        }
        for (int i = arr.length - 1; i > -1; i--) {
            if (used[i]) {
                continue;
            }
            selected[k] = arr[i];
            used[i] = true;
            permutation(r, k + 1, arr, selected, used);
            selected[k] = 0;
            used[i] = false;
        }
    }

    // 순열
    public void permutation(int[] arr, int r, int k) {
        if (k == r) {
            return;
        }

        for (int i = k; i < arr.length; i++) {
            swap(arr, i, k);
            permutation(arr, r, k + 1);
            swap(arr, i, k);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {
        Q13 q13 = new Q13();
        q13.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{1});
    }
}
