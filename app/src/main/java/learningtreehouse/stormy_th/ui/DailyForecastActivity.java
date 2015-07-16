package learningtreehouse.stormy_th.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import learningtreehouse.stormy_th.R;
import learningtreehouse.stormy_th.adapters.DayAdapter;
import learningtreehouse.stormy_th.weather.DailyWeather;
import learningtreehouse.stormy_th.weather.Forecast;

public class DailyForecastActivity extends Activity {

    private DailyWeather[] mDailyWeathers;
    @Bind(android.R.id.list) ListView mListView;
    @Bind(android.R.id.empty) TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDailyWeathers = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);
        DayAdapter adapter = new DayAdapter(this, mDailyWeathers);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mEmptyTextView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = Forecast.FormatDay(mDailyWeathers[position].getTime());
                Integer temp = mDailyWeathers[position].getTemperatureMax();
                String summary = mDailyWeathers[position].getSummary();
                String msg = String.format("On %s, the high will be %d and it will be %s", day, temp, summary);
                Toast.makeText(DailyForecastActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
