package com.trojan.opyweather.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.trojan.opyweather.MainActivity;
import com.trojan.opyweather.Model.MyList;
import com.trojan.opyweather.Model.WeatherForcastDetails;
import com.trojan.opyweather.R;

import java.util.List;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.MyViewHolder> {

    Context context;
    List<MyList> myList;

    public WeatherReportAdapter(Context context, List<MyList> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.itemrv, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final MyList m = myList.get(position);
        Picasso.with(context)
                .load("http://openweathermap.org/img/wn/" + m.weather.get(0).getIcon() + "@2x.png")
                .into(holder.iVicon);
        holder.tvDay.setText("Forecast : " + DateConverter.CovertDate(m.dt));
        holder.tvweatehrCondition.setText(String.valueOf(m.weather.get(0).getMain()));
        holder.tvTempWF.setText(m.main.getTemp() + " °C");
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvTempWF, tvweatehrCondition;
        ImageView iVicon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iVicon = itemView.findViewById(R.id.iVicon);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvTempWF = itemView.findViewById(R.id.TVtempWF);
            tvweatehrCondition = itemView.findViewById(R.id.tvweatherCondition);
        }
    }
}
