package com.dev.rest.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数字金额转汉字
 */
public class ChineseMoneyUtil {

    private static final String[] CHINESE_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static final String[] CHINESE_NUMBER_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};

    private static final String[] CHINESE_MONEY_UNIT = {"圆", "角", "分"};


    /**
     * @param sourceMoney 要转换的数值，最多支持到亿
     * @return 结果
     */
    public static String toChineseMoney(BigDecimal sourceMoney) {
        if (new BigDecimal("1000000000000").compareTo(sourceMoney) <= 0
                && BigDecimal.ZERO.compareTo(sourceMoney) >= 0) {
            throw new RuntimeException("支持转换的金额范围为0~1万亿");
        }
        StringBuilder sb = new StringBuilder();
        // 整数部分
        BigDecimal intPart = sourceMoney.setScale(0, RoundingMode.DOWN);
        // 小数部分
        BigDecimal decimalPart = sourceMoney.subtract(intPart).multiply(new BigDecimal(100)).setScale(0,
                RoundingMode.DOWN);

        // 处理整数部分圆
        if (intPart.compareTo(BigDecimal.ZERO) > 0) {
            String intPartNumberString = intPart.toPlainString();
            int length = intPartNumberString.length();
            // 统计末尾的零，末尾零不做处理
            int zeroCount = 0;
            for (int i = length - 1; i >= 0; i--) {
                int number = Integer.parseInt(String.valueOf(intPartNumberString.charAt(i)));
                if (number == 0) {
                    zeroCount++;
                } else {
                    break;
                }
            }
            for (int i = 0; i < length; i++) {
                // 如果转换到末尾0，则停止转换
                if (i + zeroCount == length) {
                    break;
                }
                int number = Integer.parseInt(String.valueOf(intPartNumberString.charAt(i)));
                // 获取中文数字
                String chineseNumber = CHINESE_NUMBER[number];
                // 获取中文数字单位
                String chineseNumberUnit = CHINESE_NUMBER_UNIT[length - i - 1];
                sb.append(chineseNumber).append(chineseNumberUnit);
            }
            // 统计完后加上金额单位
            sb.append(CHINESE_MONEY_UNIT[0]);
        } else {
            sb.append(CHINESE_NUMBER[0]).append(CHINESE_MONEY_UNIT[0]);
        }

        // 处理小数部分
        if (decimalPart.compareTo(new BigDecimal(10)) >= 0) {
            // 角
            String jiao = decimalPart.toPlainString();
            int number = Integer.parseInt(String.valueOf(jiao.charAt(0)));
            if (number != 0) {
                String chineseNumber = CHINESE_NUMBER[number];
                sb.append(chineseNumber).append(CHINESE_MONEY_UNIT[1]);
            }

            // 分
            String fen = decimalPart.toPlainString();
            number = Integer.parseInt(String.valueOf(fen.charAt(1)));
            if (number != 0) {
                String chineseNumber = CHINESE_NUMBER[number];
                sb.append(chineseNumber).append(CHINESE_MONEY_UNIT[2]);
            }
        } else if (decimalPart.compareTo(BigDecimal.ZERO) > 0) {
            // 分
            String fen = decimalPart.toPlainString();
            int number = Integer.parseInt(String.valueOf(fen.charAt(0)));
            if (number != 0) {
                String chineseNumber = CHINESE_NUMBER[number];
                sb.append(chineseNumber).append(CHINESE_MONEY_UNIT[2]);
            }
        } else {
            sb.append("整");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String chineseMoney = toChineseMoney(new BigDecimal("12345670.89"));
        System.err.println("chineseMoney = " + chineseMoney);
    }
}

