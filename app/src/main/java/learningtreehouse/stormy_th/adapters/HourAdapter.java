package learningtreehouse.stormy_th.adapters;

import android.content.Context;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import learningtreehouse.stormy_th.R;
import learningtreehouse.stormy_th.weather.Forecast;
import learningtreehouse.stormy_th.weather.HourlyWeather;

/**
 * Created by OrcaZ on 2015/07/16.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder>{

    private Context mContext;
    private HourlyWeather[] mHourlyWeathers;

    public HourAdapter(Context context, HourlyWeather[] hourlyWeathers) {
        mContext = context;
        mHourlyWeathers = hourlyWeathers;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.hourly_list_item, viewGroup, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int i) {
        hourViewHolder.bindHourlyWeather(mHourlyWeathers[i]);
    }

    @Override
    public int getItemCount() {
        return mHourlyWeathers.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
/*
        @Bind(R.id.timeTextView) TextView mTimeTextView;
        @Bind(R.id.iconImageView) ImageView mIconImageView;
        @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
        @Bind(R.id.summaryTextView) TextView mSummaryTextView;
*/
        public TextView mTimeTextView;
        public ImageView mIconImageView;
        public TextView mTemperatureTextView;
        public TextView mSummaryTextView;

        public HourViewHolder(View itemView) {
            super(itemView);

//          ButterKnife.bind(this);
            mTimeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
            mTemperatureTextView = (TextView) itemView.findViewById(R.id.temperatureTextView);
            mSummaryTextView= (TextView) itemView.findViewById(R.id.summaryTextView);

            itemView.setOnClickListener(this);
        }

        public void bindHourlyWeather(HourlyWeather hourlyWeather){
            mTimeTextView.setText(Forecast.FormatHour(hourlyWeather.getTime())+":00");
            mIconImageView.setImageResource(Forecast.getIconID(hourlyWeather.getIcon()));
            mTemperatureTextView.setText(hourlyWeather.getTemperature() + "°C");
            mSummaryTextView.setText(hourlyWeather.getSummary());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeTextView.getText().toString();
            String temp = mTemperatureTextView.getText().toString();
            String summary = mSummaryTextView.getText().toString();
            String msg = String.format("At %s, it will be %s and %s", time, temp, summary);
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }
    }
}
