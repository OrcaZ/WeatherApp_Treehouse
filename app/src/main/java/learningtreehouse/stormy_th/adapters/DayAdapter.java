package learningtreehouse.stormy_th.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import learningtreehouse.stormy_th.R;
import learningtreehouse.stormy_th.weather.DailyWeather;
import learningtreehouse.stormy_th.weather.Forecast;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class DayAdapter extends BaseAdapter {

    private Context mContext;
    private DailyWeather[] mDailyWeathers;

    public DayAdapter(Context context, DailyWeather[] dailyWeathers) {
        mContext = context;
        mDailyWeathers = dailyWeathers;
    }

    @Override
    public int getCount() {
        return mDailyWeathers.length;
    }

    @Override
    public Object getItem(int position) {
        return mDailyWeathers[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // Item tagging not used;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            //brand new
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureTextView = (TextView) convertView.findViewById(R.id.temperatureTextView);
            holder.dayNameTextView = (TextView) convertView.findViewById(R.id.dayNameTextView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        DailyWeather dailyWeather = mDailyWeathers[position];
        holder.iconImageView.setImageResource(Forecast.getIconID(dailyWeather.getIcon()));
        holder.temperatureTextView.setText(dailyWeather.getTemperatureMax() + "");
        if(position == 0) holder.dayNameTextView.setText("Today");
        else holder.dayNameTextView.setText(Forecast.FormatDay(dailyWeather.getTime()));

        return convertView;
    }

    private static class ViewHolder{
        ImageView iconImageView;
        TextView temperatureTextView;
        TextView dayNameTextView;
    }
}
