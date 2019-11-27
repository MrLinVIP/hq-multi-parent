package com.hq.multi.constant;
/**
 * @author: linliangkun
 * @date: 2019/11/25 0025
 * @description:共有常量
 */
public class Constant {
    /**
     * 请求头中 JWT 的key
     */
    public static final String AUTHOR_PARAM = "token";
    /**
     * session中 前台用户 的key
     */
    public static final String USER_PARAM = "userToken";

    /**
     * JWT 签名
     */
    public static final String JWT_SIGN_KEY = "hq.noa";

    /**
     * JWT token 用户名
     */
    public static final String JWT_TOKEN_USERNAME = "jtu";

    /**
     * JWT token 用户编号
     */
    public static final String JWT_TOKEN_USERID = "uid";

    /**
     * JWT_编号
     */
    public static final String JWT_ID = "jti";

    /**
     * 常量
     */
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    /**
     * 服务内部调用前缀
     */
    public static final String API_PATH = "/api/private/";
    public static final String API_PATH_ANON = API_PATH + "**";

    /**
     * 开放接口前缀
     */
    public static final String API_AUTH = "/api/auth/";
    public static final String API_OPEN_PATH = "/api/public/";
    public static final String API_OPEN_PATH_ANON = API_OPEN_PATH + "**";

    /**
     * 通用状态
     */
    public enum Status {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 禁用
         */
        DISABLE(1),
        /**
         * 删除
         */
        DELETE(2);

        private Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public boolean equals(Integer value) {
            return value.equals(this.value);
        }
    }

    /**
     * 数据类型
     * 0：字符串 1：整型  2：浮点型  3：布尔  4：json对象
     */
    public enum DataType {
        /**
         * 字符串
         */
        STRING(0),
        /**
         * 整型
         */
        INTEGER(1),
        /**
         * 浮点型
         */
        DOUBLE(2),
        /**
         * 布尔
         */
        BOOLEAN(3),
        /**
         * json对象
         */
        JSON(4);

        private Integer value;

        DataType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public boolean equals(Integer value) {
            return value.equals(this.value);
        }
    }

    /**
     * 角色
     */
    public enum Role {

        /**
         * 超级管理员
         */
        SUPER_ADMIN("SUPER_ADMIN"),

        /**
         * 管理员
         */
        ADMIN("ADMIN");

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }
    /**
     * 客户联系方式类型
     */
    public enum CustomerContactType {

        /**
         * 1：手机
         */
        PHONE(1),

        /**
         * 2：座机
         */
        LAND_LINE(2),
        /**
         * 3：微信
         */
        WE_CHAT(3),
        /**
         * 4：QQ
         */
        QQ(4),
        /**
         * 5：邮箱
         */
        EMAIL(5);


        private Integer value;


        CustomerContactType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public boolean equals(Integer value) {
            return value.equals(this.value);
        }
    }


    public enum CheckToken {
        /**
         * 验证成功
         */
        SUCCESS(0),
        /**
         * 其他地方登录
         */
        TOKEN_OUT(1),
        /**
         * 登录过期
         */
        TOKEN_OVERDUE(2),
        /**
         * 未登录
         */
        TOKEN_FAULT(3);
        private int value;

        CheckToken(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 性别枚举
     */
    public enum Gender {
        /**
         * 未知
         */
        UNKNOWN(0, "未知"),
        /**
         * 男
         */
        MAN(1, "男"),
        /**
         * 女
         */
        WOMEN(2, "女");

        private Integer value;
        private String code;

        Gender(Integer value, String code) {
            this.value = value;
            this.code = code;
        }

        public Integer getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }

    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
