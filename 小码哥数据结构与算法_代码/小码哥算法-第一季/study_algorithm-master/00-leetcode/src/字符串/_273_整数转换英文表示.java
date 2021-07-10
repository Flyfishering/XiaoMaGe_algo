package 字符串;

/**
 * https://leetcode-cn.com/problems/integer-to-english-words/
 */
public class _273_整数转换英文表示 {
    private final static int billion = 1000000000;
    private final static int million = 1000000;
    private final static int thousand = 1000;
    private final static int zero = 0;

    public String numberToWords(int num) {
        if (num == zero) return "Zero";
        StringBuilder sb = new StringBuilder();
        numberToWords(num, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void numberToWords(int num, StringBuilder sb) {
        if (num == 0) return;
        if (num >= billion) {
            numberToWords(num / billion, sb);
            sb.append("Billion ");
            numberToWords(num % billion, sb);
        } else if (num >= million) {
            numberToWords(num / million, sb);
            sb.append("Million ");
            numberToWords(num % million, sb);
        } else if (num >= thousand) {
            numberToWords(num / thousand, sb);
            sb.append("Thousand ");
            numberToWords(num % thousand, sb);
        } else { // 1~999
            if (num / 100 > 0) {
                lessThan20(num / 100, sb);
                sb.append("Hundred ");
            }
            int ten = num % 100;
            if (ten < 20) {
                lessThan20(ten, sb);
            } else {
                ten(ten / 10, sb);
                lessThan20(ten % 10, sb);
            }
        }
    }

    private void lessThan20(int num, StringBuilder sb) {
        switch(num) {
            case 1 :sb.append("One "); break;
            case 2 :sb.append("Two "); break;
            case 3 :sb.append("Three "); break;
            case 4 :sb.append("Four "); break;
            case 5 :sb.append("Five "); break;
            case 6 :sb.append("Six "); break;
            case 7 :sb.append("Seven "); break;
            case 8 :sb.append("Eight "); break;
            case 9 :sb.append("Nine "); break;
            case 10 :sb.append("Ten "); break;
            case 11 :sb.append("Eleven "); break;
            case 12 :sb.append("Twelve "); break;
            case 13 :sb.append("Thirteen "); break;
            case 14 :sb.append("Fourteen "); break;
            case 15 :sb.append("Fifteen "); break;
            case 16 :sb.append("Sixteen "); break;
            case 17 :sb.append("Seventeen "); break;
            case 18 :sb.append("Eighteen "); break;
            case 19 :sb.append("Nineteen "); break;
        }
    }

    private void ten(int num, StringBuilder sb) {
        switch(num) {
            case 2 :sb.append("Twenty "); break;
            case 3 :sb.append("Thirty "); break;
            case 4 :sb.append("Forty "); break;
            case 5 :sb.append("Fifty "); break;
            case 6 :sb.append("Sixty "); break;
            case 7 :sb.append("Seventy "); break;
            case 8 :sb.append("Eighty "); break;
            case 9 :sb.append("Ninety "); break;
        }
    }

    public static void main(String[] args) {
        _273_整数转换英文表示 test = new _273_整数转换英文表示();
        System.out.println(test.numberToWords(101));
    }
}
