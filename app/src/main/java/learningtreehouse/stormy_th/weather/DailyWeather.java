package learningtreehouse.stormy_th.weather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class DailyWeather implements Parcelable {
    private Long mTime;
    private String mSummary;
    private double mTemperatureMax;
    private String mIcon;

    public DailyWeather(Long time, String summary, double temperatureMax, String icon) {
        mTime = time;
        mSummary = summary;
        mTemperatureMax = temperatureMax;
        mIcon = icon;
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

    public int getTemperatureMax() {
        return (int)Math.round(mTemperatureMax);
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

    @Override
    public int describeContents() {
        return 0; //not used;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperatureMax);
        dest.writeString(mIcon);
    }

    private DailyWeather(Parcel in){
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperatureMax = in.readDouble();
        mIcon = in.readString();
    }

    public static final Creator<DailyWeather> CREATOR = new Creator<DailyWeather>() {
        @Override
        public DailyWeather createFromParcel(Parcel source) {
            return new DailyWeather(source);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };
}
