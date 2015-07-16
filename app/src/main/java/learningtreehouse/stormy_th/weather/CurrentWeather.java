package learningtreehouse.stormy_th.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import learningtreehouse.stormy_th.R;

/**
 * Created by OrcaZ on 2015/07/15.
 */
public class CurrentWeather {

    private Long mTime;
    private String mIcon, mSummary;
    private Double mTemperature, mHumidity, mPrecipProbability;

    public CurrentWeather(Long time, String icon, Double temperature,
                          Double humidity, Double precipProbability,
                          String summary) {
        mTime = time;
        mIcon = icon;
        mSummary = summary;
        mTemperature = temperature;
        mHumidity = humidity;
        mPrecipProbability = precipProbability;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public int getPrecipProbability() {
        return (int) Math.round(mPrecipProbability*100);
    }

    public void setPrecipProbability(Double precipProbability) {
        mPrecipProbability = precipProbability;
    }
}
