package learningtreehouse.stormy_th.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import learningtreehouse.stormy_th.R;
import learningtreehouse.stormy_th.adapters.HourAdapter;
import learningtreehouse.stormy_th.weather.HourlyWeather;

public class HourlyForecastActivity extends ActionBarActivity {

    private HourlyWeather[] mHourlyWeathers;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHourlyWeathers = Arrays.copyOf(parcelables, parcelables.length, HourlyWeather[].class);

        HourAdapter adapter = new HourAdapter(this, mHourlyWeathers);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }
}
