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
    private String mIcon, mSummary, mTimezone;
    private Double mTemperature, mHumidity, mPrecipProbability;

    public CurrentWeather(Long time, String icon, Double temperature,
                          Double humidity, Double precipProbability,
                          String summary, String timezone) {
        mTime = time;
        mIcon = icon;
        mSummary = summary;
        mTemperature = temperature;
        mHumidity = humidity;
        mPrecipProbability = precipProbability;
        mTimezone = timezone;

    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
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

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        return formatter.format(new Date(mTime*1000));
    }

    public int getIconID(){
        int iconID;
        switch (mIcon){
            case "clear-day":
                iconID = R.mipmap.clear_day;
                break;
            case "clear-night":
                iconID = R.mipmap.clear_night;
                break;
            case "rain":
                iconID = R.mipmap.rain;
                break;
            case "snow":
                iconID = R.mipmap.snow;
                break;
            case "sleet":
                iconID = R.mipmap.sleet;
                break;
            case "wind":
                iconID = R.mipmap.wind;
                break;
            case "fog":
                iconID = R.mipmap.fog;
                break;
            case "cloudy":
                iconID = R.mipmap.cloudy;
                break;
            case "partly-cloudy-day":
                iconID = R.mipmap.partly_cloudy;
                break;
            case "partly-cloudy-nigh":
                iconID = R.mipmap.cloudy_night;
                break;
            default:
                iconID = R.mipmap.clear_day;
                break;
        }
        return iconID;
    }
}
