package com.example.xuan.mvpdemo.bean;

import java.util.List;

public class DataBean {
    /**
     * yesterday : {"date":"20日星期四","high":"高温 33℃","fx":"西南风","low":"低温 23℃","fl":"微风","type":"雷阵雨"}
     * city : 北京
     * aqi : 53
     * forecast : [{"date":"21日星期五","high":"高温 27℃","fengli":"微风级","low":"低温 24℃","fengxiang":"东北风","type":"阵雨"},{"date":"22日星期六","high":"高温 28℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"阴"},{"date":"23日星期天","high":"高温 29℃","fengli":"微风级","low":"低温 24℃","fengxiang":"南风","type":"雷阵雨"},{"date":"24日星期一","high":"高温 28℃","fengli":"微风级","low":"低温 23℃","fengxiang":"北风","type":"雷阵雨"},{"date":"25日星期二","high":"高温 28℃","fengli":"微风级","low":"低温 21℃","fengxiang":"南风","type":"多云"}]
     * ganmao : 相对于今天将会出现大幅度降温，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。
     * wendu : 24
     */

    private YesterdayBean yesterday;
    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private List<ForecastBean> forecast;

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

}