package learningtreehouse.stormy_th;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather mCurrentWeather;

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
                        if (response.isSuccessful()){
                            mCurrentWeather=parseWeatherData(rawWeatherData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        }
                        else errorAlert();
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
        mTemperatureTextView.setText(mCurrentWeather.getTemperature()+"");
         mTimeTextView.setText("At "+mCurrentWeather.getFormattedTime()+" it will be");
        mHumidityValueTextView.setText(mCurrentWeather.getHumidity()+"");
        mPrecipValueTextView.setText(mCurrentWeather.getPrecipProbability()+"%");
        mSummaryTextView.setText(mCurrentWeather.getSummary());
        mIconImageView.setImageDrawable(getResources().getDrawable(mCurrentWeather.getIconID()));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager netMgr = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = netMgr.getActiveNetworkInfo();
          if(netInfo == null || !netInfo.isConnected()) return false;
        return true;
    }

    private CurrentWeather parseWeatherData(String rawWeatherData) throws JSONException {
        JSONObject weatherData = new JSONObject(rawWeatherData);
        JSONObject currentWeatherData = weatherData.getJSONObject("currently");
        CurrentWeather currentWeather = new CurrentWeather(currentWeatherData.getLong("time")
                , currentWeatherData.getString("icon")
                , currentWeatherData.getDouble("temperature")
                , currentWeatherData.getDouble("humidity")
                , currentWeatherData.getDouble("precipProbability")
                , currentWeatherData.getString("summary")
                , weatherData.getString("timezone"));

//        Log.d(TAG, currentWeather.getFormattedTime());
//        Log.d(TAG, currentWeather.getTemperature()+"");
        return currentWeather;
    }


    private void errorAlert() {
        AlertDialogFragment alert = new AlertDialogFragment();
        alert.show(getFragmentManager(), null);
    }
}
