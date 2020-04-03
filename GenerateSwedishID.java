import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SwedishID {

    public static String generateSwedishID() {
        Random rnd = new Random();
        long   ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String natregno = simpleDateFormat.format(date);
        return addSerialNumber(natregno);
    }

    private static String addSerialNumber(String natregno) {
        return addLast4(natregno);
    }

    private static  String addLast4(String natregno) {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 999 + 1);
        String last3 = String.valueOf(randomNum);
        return addControlDigit(natregno + last3);
    }

    private static  String addControlDigit(String natRegNo) {
        char[] withoutCenturyAndControlDigit = natRegNo.substring(2, 11).toCharArray();
        List<Integer> natRegNoIntegerList = new ArrayList<>();
        for (char c : withoutCenturyAndControlDigit) {
            natRegNoIntegerList.add(Character.getNumericValue(c));
        }
        int check = checkSum(natRegNoIntegerList);
        return natRegNo + check;
    }

    private static int checkSum(List<Integer> natRegNo) {
        int sum = 0;
        for (int i = 0; i < natRegNo.size(); i++) {
            if (i % 2 == 0) {
                sum += digitSum(natRegNo.get(i) * 2);
            } else {
                sum += natRegNo.get(i);
            }
        }
        return (10 - sum % 10) % 10;
    }

    private static int digitSum(int inNum) {
        int sum = 0;
        int num = inNum;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;  // Integer division, throws away any decimals
        }
        return sum;
    }

}
