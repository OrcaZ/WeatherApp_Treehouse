package learningtreehouse.stormy_th.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learningtreehouse.stormy_th.R;
import learningtreehouse.stormy_th.weather.CurrentWeather;
import learningtreehouse.stormy_th.weather.DailyWeather;
import learningtreehouse.stormy_th.weather.Forecast;
import learningtreehouse.stormy_th.weather.HourlyWeather;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";

    private Forecast mForecast;

    @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.humidityValueTextView) TextView mHumidityValueTextView;
    @Bind(R.id.precipValueTextView) TextView mPrecipValueTextView;
    @Bind(R.id.summaryTextView) TextView mSummaryTextView;
    @Bind(R.id.iconImageView) ImageView mIconImageView;
    @Bind(R.id.refreshImageView) ImageView mRefreshImageView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final double latitude =35.555238;
        final double longitude = 139.655183;
        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(latitude, longitude);
            }
        });
        getForecast(latitude, longitude);
    }

    private void getForecast(double latitude, double longitude) {
        String apiKey = "1c6235f8d832db6e5da5bd0329ed88fa";
        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey +
                "/"+latitude+","+longitude+"?units=si";

        if(isNetworkAvailable()) {
            toggleRefresh();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String rawWeatherData = response.body().string();
                        if (response.isSuccessful()) {
                            mForecast = parseWeatherData(rawWeatherData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else errorAlert();
                    } catch (IOException | JSONException e) {
                        Log.e(TAG, "Exception caught:", e);
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Network unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if(mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }else{
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {
        CurrentWeather currentWeather = mForecast.getCurrentWeather();
        mTemperatureTextView.setText(currentWeather.getTemperature()+"");
        mTimeTextView.setText("At "+Forecast.FormatTime(currentWeather.getTime())+" it will be");
        mHumidityValueTextView.setText(currentWeather.getHumidity()+"");
        mPrecipValueTextView.setText(currentWeather.getPrecipProbability()+"%");
        mSummaryTextView.setText(currentWeather.getSummary());
        mIconImageView.setImageDrawable(getResources().getDrawable(Forecast.getIconID(currentWeather.getIcon())));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager netMgr = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = netMgr.getActiveNetworkInfo();
          if(netInfo == null || !netInfo.isConnected()) return false;
        return true;
    }

    private Forecast parseWeatherData(String rawWeatherData) throws JSONException {
        JSONObject weatherData = new JSONObject(rawWeatherData);
        JSONObject currentWeatherData = weatherData.getJSONObject("currently");
        JSONObject hourlyWeatherData = weatherData.getJSONObject("hourly");
        JSONObject dailyWeatherData = weatherData.getJSONObject("daily");

        Forecast forecast = new Forecast();
        forecast.setTimezone(weatherData.getString("timezone"));
        forecast.setCurrentWeather(parseCurrentWeatherData(currentWeatherData));
        forecast.setHourlyWeathers(parseHourlyWeatherData(hourlyWeatherData));
        forecast.setDailyWeathers(parseDailyWeatherData(dailyWeatherData));
        return forecast;
    }

    private DailyWeather[] parseDailyWeatherData(JSONObject dailyWeatherData) throws JSONException {
        JSONArray dailyWeatherDataArray = dailyWeatherData.getJSONArray("data");
        int itemsNumber = dailyWeatherDataArray.length();
        DailyWeather[] dailyWeathers = new DailyWeather[itemsNumber];
        for(int i = 0; i < itemsNumber; i++){
            JSONObject dailyData = dailyWeatherDataArray.getJSONObject(i);
            dailyWeathers[i] = new DailyWeather(dailyData.getLong("time")
                    , dailyData.getString("summary")
                    , dailyData.getDouble("temperatureMax")
                    , dailyData.getString("icon"));
        }
        return dailyWeathers;
    }

    private HourlyWeather[] parseHourlyWeatherData(JSONObject hourlyWeatherData) throws JSONException {
        JSONArray hourlyWeatherDataArray = hourlyWeatherData.getJSONArray("data");
        int itemsNumber = hourlyWeatherDataArray.length();
        HourlyWeather[] hourlyWeathers = new HourlyWeather[itemsNumber];
        for(int i = 0; i < itemsNumber; i++){
            JSONObject hourlyData = hourlyWeatherDataArray.getJSONObject(i);
            hourlyWeathers[i] = new HourlyWeather(hourlyData.getLong("time")
                    , hourlyData.getString("summary")
                    , hourlyData.getDouble("temperature")
                    , hourlyData.getString("icon"));
        }
        return hourlyWeathers;
    }

    private CurrentWeather parseCurrentWeatherData(JSONObject currentWeatherData) throws JSONException {
        return new CurrentWeather(currentWeatherData.getLong("time")
                , currentWeatherData.getString("icon")
                , currentWeatherData.getDouble("temperature")
                , currentWeatherData.getDouble("humidity")
                , currentWeatherData.getDouble("precipProbability")
                , currentWeatherData.getString("summary"));
    }

    private void errorAlert() {
        AlertDialogFragment alert = new AlertDialogFragment();
        alert.show(getFragmentManager(), null);
    }

    @OnClick (R.id.dailyButton)
    public void startDailyActivity(View v){
        Intent intent = new Intent(this, DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST, mForecast.getDailyWeathers());
        startActivity(intent);
    }

    @OnClick (R.id.hourlyButton)
    public void startHourlyActivity(View v){
        Intent intent = new Intent(this, HourlyForecastActivity.class);
        intent.putExtra(HOURLY_FORECAST, mForecast.getHourlyWeathers());
        startActivity(intent);
    }
}
