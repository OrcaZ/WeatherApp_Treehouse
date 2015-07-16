package learningtreehouse.stormy_th.weather;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class HourlyWeather {
    private Long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTImezone;

    public HourlyWeather(Long time, String summary, double temperature, String icon, String TImezone) {
        mTime = time;
        mSummary = summary;
        mTemperature = temperature;
        mIcon = icon;
        mTImezone = TImezone;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTImezone() {
        return mTImezone;
    }

    public void setTImezone(String TImezone) {
        mTImezone = TImezone;
    }
}
