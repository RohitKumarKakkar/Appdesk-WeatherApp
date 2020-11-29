package com.trojan.opyweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;
import com.trojan.opyweather.Adapters.DateConverter;
import com.trojan.opyweather.Adapters.WeatherReportAdapter;
import com.trojan.opyweather.Model.MyList;
import com.trojan.opyweather.Model.WeatherDetails;
import com.trojan.opyweather.Model.WeatherForcastDetails;
import com.trojan.opyweather.RetrofitEssentials.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText cityname;
    ImageView IVicon;
    TextView tvCity, tvWeather, tvTemp, tvHumidty, tvFeelslike, tvDate, tvPressure, tvWindSpeed, tvWindDegree, tvSwitch;
    String apikey = "1a6b7b62de93130237ad049ec2744be5";
    String unit = "metric";
    SwitchCompat unitSwitcher;
    String Latitude = "", Longitude = "", unitMetric = " °C", unitImperial = " °F", unitSpeedMetric = " mtr/sc.", unitSpeedImperial = " mil/hr.";
    String q = "", q2 = "";
    List<MyList> myList = new ArrayList<>();
    Button btnDelhi, btnNoida, btnMumbai, btnSearch;
    Dialog dialog;

    public static final int UPDATE_INTERVAL = 5000;

    FusedLocationProviderClient locationProviderClient;
    LocationCallback locationCallback;
    LocationRequest locationRequest;

    public static final String TAG = MainActivity.class.getSimpleName();
    private int LOCATION_PERMISSION = 100;

    private Location currentLocation;
    RecyclerView recyclerView;
    WeatherReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_box_intro);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        cityname = findViewById(R.id.edtCity);
        btnSearch = findViewById(R.id.btnSearch);
        tvCity = findViewById(R.id.tvCity);
        tvTemp = findViewById(R.id.tvTemp);
        tvWeather = findViewById(R.id.tvMain);
        tvHumidty = findViewById(R.id.tvHumidity);
        tvFeelslike = findViewById(R.id.tvfeelslike);
        tvPressure = findViewById(R.id.tvPressure);
        IVicon = findViewById(R.id.IVicon);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvWindDegree = findViewById(R.id.tvWindDegree);
        unitSwitcher = findViewById(R.id.unitSwitcher);
        tvSwitch = findViewById(R.id.tvSwitch);
        tvDate = findViewById(R.id.tvDate);
        btnDelhi = findViewById(R.id.btnDelhi);
        btnMumbai = findViewById(R.id.btnMumbai);
        btnNoida = findViewById(R.id.btnNoida);

        btnNoida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2 = "noida";
                getWeatherData();
                getForcastWeatherData();
            }
        });

        btnMumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2 = "mumbai";
                getWeatherData();
                getForcastWeatherData();
            }
        });

        btnDelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2 = "delhi";
                getWeatherData();
                getForcastWeatherData();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cityname.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter a City Name", Toast.LENGTH_SHORT).show();
                } else {
                    q2 = "";
                    getWeatherData();
                    getForcastWeatherData();
                }

            }
        });

        tvSwitch.setText("°C");
        unitSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    unit = "imperial";
                    tvSwitch.setText("°F");
                    getWeatherData();
                    getForcastWeatherData();
                } else {
                    unit = "metric";
                    tvSwitch.setText("°C");
                    getWeatherData();
                    getForcastWeatherData();
                }
            }
        });

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                if (locationAvailability.isLocationAvailable()) {
                    Log.i(TAG, "Location Is Available");
                } else {
                    Log.i(TAG, "Location Is Not Available");
                }

            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.i(TAG, "Location Result Is Available");
            }
        };

        locationchecker();

        Button doneBTN = dialog.findViewById(R.id.doneButton);
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2 = "";
                getWeatherData();
                getForcastWeatherData();
                dialog.hide();
            }
        });

        dialog.show();
    }

    private void locationchecker() {
        LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("GPS Not Enabled")  // GPS not found
                    .setMessage("GPS Not Found") // Want to enable?
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
        else{
            StartLocating();
        }
    }

    private void StartLocating() {
        //Asking For Location Permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //Accessing Location and Setting TextViews
            locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, MainActivity.this.getMainLooper());
            locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    currentLocation = location;
                    if (location != null) {
                        Latitude = String.valueOf(currentLocation.getLatitude());
                        Longitude = String.valueOf(currentLocation.getLongitude());
                        getWeatherData();
                        getForcastWeatherData();
                    }
                    // Toast.makeText(MainActivity.this, Longitude + " " + Longitude, Toast.LENGTH_SHORT).show();
                }
            });

            locationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(TAG, "Exception Occured While Getting The Location" + e.getMessage());
                }
            });

            //If Permission Is Not Already Been Granted
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Please Allow The Permissions", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}
                        , LOCATION_PERMISSION);
            }
        }
    }


    public void getWeatherData() {

        Call<WeatherDetails> call;
        if (!q2.equals("")) {
            call = ApiClient.getInstance().getApi().getWeather(q2, apikey, unit);
        } else if (!cityname.getText().toString().isEmpty()) {
            q = cityname.getText().toString().toLowerCase().trim();
            call = ApiClient.getInstance().getApi().getWeather(q, apikey, unit);
        } else {
            call = ApiClient.getInstance().getApi().getWeatherWithLocation(Latitude, Longitude, apikey, unit);
        }

        call.enqueue(new Callback<WeatherDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherDetails> call, Response<WeatherDetails> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (unit.equals("imperial")) {
                        tvTemp.setText(response.body().getMain().getTemp() + unitImperial);
                        tvFeelslike.setText(response.body().getMain().getFeels_like() + unitImperial);
                        tvWindSpeed.setText(response.body().getWind().getSpeed() + unitSpeedImperial);
                    } else if (unit.equals("metric")) {
                        tvTemp.setText(response.body().getMain().getTemp() + unitMetric);
                        tvFeelslike.setText(response.body().getMain().getFeels_like() + unitMetric);
                        tvWindSpeed.setText(response.body().getWind().getSpeed() + unitSpeedMetric);
                    }

                    tvWeather.setText(response.body().getWeather().get(0).getMain());
                    tvHumidty.setText(String.valueOf(response.body().getMain().getHumidity()) + " %");
                    tvPressure.setText(String.valueOf(response.body().getMain().getPressure()) + " hpa");
                    tvCity.setText(response.body().getName());
                    tvWindDegree.setText(String.valueOf(response.body().getWind().getDeg()));
                    tvDate.setText("Last Updated : " + DateConverter.CovertDate(response.body().getDt()));

                    Picasso.with(MainActivity.this)
                            .load("http://openweathermap.org/img/wn/" + response.body().getWeather().get(0).getIcon() + "@2x.png")
                            .into(IVicon);
                }
            }

            @Override
            public void onFailure(Call<WeatherDetails> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getForcastWeatherData() {
        Call<WeatherForcastDetails> call;
        if (!q2.equals("")) {
            call = ApiClient.getInstance().getApi().getForcast(q2, apikey, "metric");
        } else if (!cityname.getText().toString().isEmpty()) {
            q = cityname.getText().toString().toLowerCase().trim();
            call = ApiClient.getInstance().getApi().getForcast(q, apikey, "metric");
        } else {
            call = ApiClient.getInstance().getApi().getForcastWithLocation(Latitude, Longitude, apikey, "metric");
        }

        call.enqueue(new Callback<WeatherForcastDetails>() {
            @Override
            public void onResponse(Call<WeatherForcastDetails> call, Response<WeatherForcastDetails> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().list != null) {
                        myList = response.body().list;
                        adapter = new WeatherReportAdapter(MainActivity.this, myList);
                        recyclerView.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<WeatherForcastDetails> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}