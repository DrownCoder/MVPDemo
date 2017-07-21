package com.example.xuan.mvpdemo.bean;

/**
 * Author : xuan.
 * Data : 2017/7/21.
 * Description :api：http://www.sojson.com/open/api/weather/json.shtml?city=%E5%8C%97%E4%BA%AC
 */

public class WeatherInfo {
    /**
     * data : {"yesterday":{"date":"20日星期四","high":"高温 33℃","fx":"西南风","low":"低温 23℃","fl":"微风","type":"雷阵雨"},"city":"北京","aqi":"53","forecast":[{"date":"21日星期五","high":"高温 27℃","fengli":"微风级","low":"低温 24℃","fengxiang":"东北风","type":"阵雨"},{"date":"22日星期六","high":"高温 28℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"阴"},{"date":"23日星期天","high":"高温 29℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"雷阵雨"},{"date":"24日星期一","high":"高温 28℃","fengli":"微风级","low":"低温 23℃","fengxiang":"北风","type":"雷阵雨"},{"date":"25日星期二","high":"高温 28℃","fengli":"微风级","low":"低温 21℃","fengxiang":"南风","type":"多云"}],"ganmao":"相对于今天将会出现大幅度降温，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。","wendu":"24"}
     * status : 200
     * message : OK
     */

    private DataBean data;
    private int status;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
