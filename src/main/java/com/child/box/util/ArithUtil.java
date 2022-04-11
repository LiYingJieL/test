package com.child.box.util;

import java.math.BigDecimal;

/**
 * 说明:计算的公式
 *
 * @author 卢利如
 * @version 1.0
 * @since 2013-11-1
 */
public class ArithUtil {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    // 这个类不能实例化
    private ArithUtil() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后 10 位，以后的数字四舍五入。
     * 保留小数点后的3位
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由 scale 参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param fee 满一百减十块，一次类推
     */
    public static double getDiscount(double fee, double lines, double subValue) {
        double discount = 0d;
        if (fee > lines) {
            discount = ArithUtil.mul(ArithUtil.add(floor(ArithUtil.div(fee - lines, 100), 0), 1), subValue);
        }
        return discount;
//		return ArithUtils.mul(floor(ArithUtils.div(fee, lines),0),subValue);
    }


    /**
     * 提供精确的小数位舍弃。
     *
     * @param v     需要舍弃的数字
     * @param scale 小数点后保留几位
     * @return floor后的数
     */
    public static double floor(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_FLOOR).doubleValue();
    }

    /**
     * 比较大小(v1-v2)
     *
     * @param v1
     * @param v2
     * @return 1：大于，0等于，-1小于
     */
    public static int compare(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.compareTo(b2);
    }

    public static int compareb(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2);
    }

    /**
     * BigDecimal类型相加
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal addb(BigDecimal v1, BigDecimal v2){
        return v1.add(v2);
    }

    /**
     * BigDecimal类型相减
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subb(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }

    /**
     * BigDecimal类型相乘
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mulb(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }

    /**
     * BigDecimal类型相除
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divb(BigDecimal v1, BigDecimal v2) {
        return v1.divide(v2);
    }
}
