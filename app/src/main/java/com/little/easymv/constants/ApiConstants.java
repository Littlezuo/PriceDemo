package com.little.easymv.constants;

/**
 * Created by Administrator on 2017/3/23.
 */
public class ApiConstants {

    public static final int DEFULT_PAGESIZE = 10;
    public static final String USERID = "userid";
    public static final String GO_TO_ORDER = "go2order";
    public static final String SET_ALIAS_SUCCES = "set_alias_success";
    public static final int PRODUCT_CIGARATTE = 1;
    public static final int PRODUCT_ALCOHOL = 2;
    public static final int PRODUCT_AROUND = 0;


    public static final int TYPE_PRODUCT = 1;
    public static final int TYPE_RETAILER = 2;

    public static final String TYPE = "type";
    public static final String PAGE_LEN = "page_len";
    public static final String PAGE_INDEX = "page_index";

    public static final String SEARCH_HISTORY = "search_history";

    //评论
    public static final int ALL_COMMENT = 0;
    public static final int POSITIVE_COMMENT = 1;
    public static final int NEUTRAL_COMMENT = 2;
    public static final int NEGATIVE_COMMENT = 3;
    public static final int ORDER_UP = 1;
    public static final int ORDER_DOWN = 2;
    public static final String ORDER_BY_PEICE = "price";
    public static final String ORDER_BY_SALES = "sales";
    public static final int DEFULT_ORDER = 0;
    public static final String ORDER_BY_DEFAULT = "";
    public static final int COLLECT_PRODUCT = 1;
    public static final int COLLECT_RETAILER = 2;
    public static final String NEAR2FAR = "1";
    public static final String FAR2NEAR = "2";
    public static final int DEFULT_NET = -1;
    public static final int ENTER_NORMAL = 2;
    public static final int ENTER_ACT = 1;
    public static final int ENTER_NONE = 0;
    public static final String  TYPE_SMOKE = "1";

    public static int SPLASH_SEC = 0;
    public static String isFirst = "isfrist";
    public static final String Secret = "ec9552ee944d4e689fc9bada41431ed1";

    public static final String Secret_BOUNS = "71eeb8ffce1035bb2e73d7f3e9efdd82";
    public static final String PUBLIC_KEY_BOUNS = "844b41f99abc311f";

    //优惠券
    public static final int ALL_COUPON = 0;
    public static final int NOT_USE_COUPON = 1;
    public static final int HAS_BEEN_USED_COUPON = 2;
    public static final int EXPIRED_COUPON = 3;

    public static final int MSG_TYPE_ORDER = 1;
    public static final int MSG_TYPE_SYSTEM = 2;


    //配送方式
    public static final String  GE_RI_DA = "1";
    public static final String  SHAN_DIAN_SONG = "2";
    public static final String  ZI_TI = "3";
    public static final String NONE_SEND_TYPE = "-1";

    public static final String PAY_SUCCESS = "2";
    public static final String PAY_FAIL = "-1";
    public static final String PAY_NULL = "0";
}
