package learningtreehouse.stormy_th.weather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class HourlyWeather implements Parcelable {
    private Long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;

    public HourlyWeather(Long time, String summary, double temperature, String icon) {
        mTime = time;
        mSummary = summary;
        mTemperature = temperature;
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

    public int getTemperature() {
        return (int)Math.round(mTemperature);
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

    @Override
    public int describeContents() {
        return 0;//not used
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
    }

    public static final Parcelable.Creator<HourlyWeather> CREATOR
            = new Creator<HourlyWeather>() {
        @Override
        public HourlyWeather createFromParcel(Parcel source) {
            return new HourlyWeather(source);
        }

        @Override
        public HourlyWeather[] newArray(int size) {
            return new HourlyWeather[size];
        }
    };

    public HourlyWeather(Parcel in){
        mTime=in.readLong();
        mSummary=in.readString();
        mTemperature=in.readDouble();
        mIcon=in.readString();
    }
}
