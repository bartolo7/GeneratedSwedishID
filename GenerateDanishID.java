package se.nordnet.identity.assurance.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DenmarkNatRegGenerator {

    private static int[][] CPRPossibilities = {{1, 2, 3, 0}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 3, 5, 7, 9}};
    private static List<String> CPRList = new ArrayList<String>();
    private static String birthDay = createBirthDay();


    public static String danishCPRId() {
        return createCandidateDanishID().get(0);
    }

    public static List<String> danishCPRIdList() {
        return createCandidateDanishID();
    }

    private static List<String> createCandidateDanishID() {
        int depth = 0;
        String partialCPR = "";
        return createCandidateDanishID(depth, partialCPR);
    }

    private static List<String> createCandidateDanishID(int depth, String partialCPR) {
        String validID = null;
        for (int set : CPRPossibilities[depth]) {
            if (depth < 3) {
                String nextPartialCPR = partialCPR + "" + CPRPossibilities[depth][set];
                int nextDepth = depth + 1;
                createCandidateDanishID(nextDepth, nextPartialCPR);
            } else {
                String candidateCPR = partialCPR + "" + CPRPossibilities[2][set];
                validID = birthDay + candidateCPR;
                if (isCPRValid(validID)) {
                    CPRList.add(validID);
                }
            }
        }
        return CPRList;
    }

    private static boolean isCPRValid(String cpr) {
        int[] fullCPR = cpr.chars().map(c -> c - '0').toArray();
        int[] factors = {4, 3, 2, 7, 6, 5, 4, 3, 2, 1};
        int sum = IntStream.range(0, factors.length)
                .map(i -> fullCPR[i] * factors[i])
                .sum();
        return sum % 11 == 0;
    }

    private static String createBirthDay() {
        Random rnd = new Random();
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
        return simpleDateFormat.format(date);
    }
}
