package com.example.zijinchuantoupingtai.util;

import org.springframework.util.StringUtils;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.util
 * @date 2019/11/19 16:56
 * @Copyright
 */
public class Regex {

    /**
     * 校验 身份证
     */
    public boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
          "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        // 假设18位身份证号码:41000119910101123X  410001 19910101 123X
        // ^开头
        // [1-9] 第一位1-9中的一个      4
        // \\d{5} 五位数字           10001（前六位省市县地区）
        // (18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        // \\d{2}                    91（年份）
        // ((0[1-9])|(10|11|12))     01（月份）
        // (([0-2][1-9])|10|20|30|31)01（日期）
        // \\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        // [0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        // $结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
//                        System.out.println("身份证" + IDNumber + "最后一位:" + String.valueOf(idCardLast).toUpperCase() +
//                          "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        } else {
//            System.out.println(IDNumber + "  身份证不匹配正则");
        }
        return matches;
    }

    /**
     * 校验 统一社会信用代码 15位
     *
     * @param license
     * @return
     */
    public boolean isLicense15(String license) {

        if (StringUtils.isEmpty(license)) {
            return false;
        }
        if (license.length() != 15) {
            return false;
        }

        String businesslicensePrex14 = license.substring(0, 14);// 获取营业执照注册号前14位数字用来计算校验码
        String businesslicense15 = license.substring(14, license.length());// 获取营业执照号的校验码
        char[] chars = businesslicensePrex14.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        int checkCode = getCheckCode(ints);
        // System.out.println(license+"\t营业执照的最后一位验证码应该是: " + checkCode);
        String s = String.valueOf(checkCode);
        if (businesslicense15.equals(s)) {// 比较 填写的营业执照注册号的校验码和计算的校验码是否一致
            return true;
        }
        return false;

    }

    /**
     * 获取 15位 营业执照注册号的校验码
     *
     * @param ints
     * @return
     */
    private int getCheckCode(int[] ints) {
        if (null != ints && ints.length > 1) {
            int ti = 0;
            int si = 0;// pi|11+ti
            int cj = 0;// （si||10==0？10：si||10）*2
            int pj = 10;// pj=cj|11==0?10:cj|11
            for (int i = 0; i < ints.length; i++) {
                ti = ints[i];
                pj = (cj % 11) == 0 ? 10 : (cj % 11);
                si = pj + ti;
                cj = (0 == si % 10 ? 10 : si % 10) * 2;
                if (i == ints.length - 1) {
                    pj = (cj % 11) == 0 ? 10 : (cj % 11);
                    return pj == 1 ? 0 : 11 - pj;
                }
            }
        }// end if
        return -1;
    }

    /**
     * 校验 统一社会信用代码 18位
     * 当 MOD 函数值为1（即 C_{18}=30}）时，校验码应用符号Y表示；
     * 当 MOD 函数值为0（即 C_{18}=31}）时，校验码用0表示
     */
    public boolean isLicense18(String license) {
        if (StringUtils.isEmpty(license)) {
            return false;
        }
        if (license.length() != 18) {
            return false;
        }

        String regex = "^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9ABCDEFGHJKLMNPQRTUWXY])$";
        if (!license.matches(regex)) {
//            System.out.println(license + "\t统一社会信用代码不符合正则");
            return false;
        }
        String str = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int[] ws = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
        String[] codes = new String[2];
        codes[0] = license.substring(0, license.length() - 1); // 前 17位
        codes[1] = license.substring(license.length() - 1); // 最后一位 (校验码)
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += str.indexOf(codes[0].charAt(i)) * ws[i];
        }
        int c18 = 31 - (sum % 31);
        char s18;  // 计算得到的 末位 校验码

        // 当 MOD 函数值为1（即 C_{18}=30}）时，校验码应用符号Y表示；
        // 当 MOD 函数值为0（即 C_{18}=31}）时，校验码用0表示
        if (c18 == 31) {
            s18 = '0';
        } else if (c18 == 30) {
            s18 = 'Y';
        } else {
            s18 = str.charAt(c18);
        }
        // 如果 计算得到的 校验码 与输入的 社会统一信用代码 不符 则 输出 false
        // str.charAt(c18) != codes[1].charAt(0)
        if (s18 != codes[1].charAt(0)) {
//            System.out.println(license + "\t不是有效的统一社会信用代码," + "最后一位校验码应该是:" + s18);
            return false;
        }
        return true;
    }

    /**
     * 检验 统一社会信用代码 15位 和 18位
     */
    public boolean isLicense(String license) {
        if (StringUtils.isEmpty(license)) {
            return false;
        }

        if (license.length() == 15 && isLicense15(license)) {
            return true;
        } else if (license.length() == 18 && isLicense18(license)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 组织机构 校验
     */
    public boolean isZuZhi(String zuzhijigou) {

        if (StringUtils.isEmpty(zuzhijigou)) {
            return false;
        }

        if (zuzhijigou.length() != 9) {
            return false;
        }

        String regex = "[0-9ABCDEFGHJKLMNPQRTUWXY]{9}";
        if (!zuzhijigou.matches(regex)) {
//            System.out.println("组织机构代码不符合正则");
            return false;
        }
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] ws = {3, 7, 9, 10, 5, 8, 4, 2};
        String[] codes = new String[2];
        codes[0] = zuzhijigou.substring(0, zuzhijigou.length() - 1);
        codes[1] = zuzhijigou.substring(zuzhijigou.length() - 1, zuzhijigou.length());

        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int i1 = str.indexOf(codes[0].charAt(i));
            sum += i1 * ws[i];
        }

        // 计算后 正确的校验码 c18
        int c9 = 11 - (sum % 11);
        // 当C9的值为10时，校验码应用大写的拉丁字母X表示；当C9的值为11时校验码用0表示
        String s18 = "";
        if (c9 == 10) {
            s18 = "X";
        } else if (c9 == 11) {
            s18 = "0";
        } else {
            s18 = String.valueOf(c9);
        }
        // 正确的校验码 c18 与 codes[1] 比较
        if (!codes[1].equals(s18)) {
//            System.out.println(zuzhijigou + "\t--不对,正确的校验码应该是" + s18);
            return false;
        }


        return true;
    }
}
