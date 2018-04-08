package me.frank.manager.server.util;


import java.util.List;
import java.util.regex.Pattern;


public class ValidateUtil {


    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof List) {
            return isEmpty((List) obj);
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(List obj) {
        if (obj == null || obj.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 正则表达式：验证用户名(2到8个字的汉字,或者2到16个字的英文)
     */
    public static final String REGEX_USERNAME = "^(([\\u4e00-\\u9fa5]{2,8})|([a-zA-Z]{2,16}))$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{2,24}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((00[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    /**
     * 正则表达式：验证数字
     */
    public static final String REGEX_NUM = "^[0-9]*$";

    /**
     * 正则表达式：验证社保账户
     */
    public static final String REGEX_SOCIALSECURITY_ACCOUNT = "^.{2,}$";
    /**
     * 正则表达式：验证金额
     */
    public static final String REGEX_AMOUNT = "^[0-9]{1,10}$";
    /**
     * 正则表达式：验证公积金账户
     */
    public static final String REGEX_FUND_ACCOUNT = "^.{2,}$";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：正整数
     */
    private static final String REGEX_POSTIVE_INTEGER = "^[1-9]\\d*$";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return true;
        //return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验社保账户
     *
     * @param socialSecurityAccount
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isSocialSecurityAccount(String socialSecurityAccount) {
        return Pattern.matches(REGEX_SOCIALSECURITY_ACCOUNT, socialSecurityAccount);
    }

    /**
     * 校验金额
     *
     * @param amount
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isAmount(String amount) {
        return Pattern.matches(REGEX_AMOUNT, amount);
    }

    /**
     * 校验社保账户
     *
     * @param fundAccount
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isFundAccount(String fundAccount) {
        return Pattern.matches(REGEX_FUND_ACCOUNT, fundAccount);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }


    /**
     * 校验正整数
     *
     * @param postiveInteger
     * @return
     */
    public static boolean isPostiveInteger(String postiveInteger) {
        return Pattern.matches(REGEX_POSTIVE_INTEGER, postiveInteger);
    }


    public static void main(String[] args) {
        System.out.println(isPostiveInteger(null));
    }

}
