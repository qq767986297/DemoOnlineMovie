package com.bw.movie.bean;

/**
 * Time: 2020/5/10
 * Author: 王冠华
 * Description:
 */
public class AliPayBean {

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018080760951276&biz_content=%7B%22out_trade_no%22%3A%2220200510203448752%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiProd%2FaliPay%2FmovieNotification&sign=BoM0h5z9e0ONrexgsYKxE%2BAiUhKB1OKdAjRNwe%2FEVd3LNYdERHSR3ArneneaWuoYlhKQrWsihcF4dSgWp5AHQby2MjsbI7%2BT%2FDf8JihopNiR79w5KqX1ZrBLg2BKsKAfdrXHOqfdbOEXypL7Vp6FivjsapznoOTB9%2B7KcQZAqgpcigQRKRyzRGNlBs8sAsgephgWCMacmYU7Qk3FIQH05GEptD9vPJ%2Bmjtm1iByf10RyojsUTs%2ByLbt4DzsZmSpOq4aXUlc2sky36UsXjuY8Wfr5GkOaohMfHY40icw5l%2BGDJS2qhMYXA8ox07RkDLYLIO%2BebOKOTTOB0rmT83ozIA%3D%3D&sign_type=RSA2&timestamp=2020-05-10+20%3A37%3A05&version=1.0
     * message : ok
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
