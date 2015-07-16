package learningtreehouse.stormy_th.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import learningtreehouse.stormy_th.R;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class Forecast {
    private CurrentWeather mCurrentWeather;
    private HourlyWeather[] mHourlyWeathers;
    private DailyWeather[] mDailyWeathers;
    private static String mTimezone;

        public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public HourlyWeather[] getHourlyWeathers() {
        return mHourlyWeathers;
    }

    public void setHourlyWeathers(HourlyWeather[] hourlyWeathers) {
        mHourlyWeathers = hourlyWeathers;
    }

    public DailyWeather[] getDailyWeathers() {
        return mDailyWeathers;
    }

    public void setDailyWeathers(DailyWeather[] dailyWeathers) {
        mDailyWeathers = dailyWeathers;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public static String FormatTime(Long time){
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        return formatter.format(new Date(time*1000));
    }

    public static String FormatDay(Long time){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        return formatter.format(new Date(time*1000));
    }

    public static String FormatHour(Long time){
        SimpleDateFormat formatter = new SimpleDateFormat("kk");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        return formatter.format(new Date(time*1000));
    }

    public static int getIconID(String icon){
        int iconID;
        switch (icon){
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
