package learningtreehouse.stormy_th.weather;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class DailyWeather {
    private Long mTime;
    private String mSummary;
    private double mTemperatureMax;
    private String mIcon;
    private String mTImezone;

    public DailyWeather(Long time, String summary, double temperatureMax, String icon, String TImezone) {
        mTime = time;
        mSummary = summary;
        mTemperatureMax = temperatureMax;
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

    public double getTemperatureMax() {
        return mTemperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
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
