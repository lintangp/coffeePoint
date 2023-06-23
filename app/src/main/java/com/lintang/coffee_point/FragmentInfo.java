package com.lintang.coffee_point;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentInfo extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private TextView info, nama, tanggal;
    private RequestQueue requestQueue;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);
    
            mapView = rootView.findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
            info = rootView.findViewById(R.id.textinfo);
            nama = rootView.findViewById(R.id.textJudul);
            tanggal = rootView.findViewById(R.id.texttanggal);

            requestQueue = Volley.newRequestQueue(requireContext());

            //IP nya isi ip lokal kalian sendiri yak, kalo misal mau coba jalanin
            String url = "http://10.200.227.179/info_api/";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                // Assuming the response is a JSON array containing a single object
                                JSONObject jsonObject = response.getJSONObject(0);
                                String namacafe = jsonObject.getString("nama");
                                String tanggalBerdiri = jsonObject.getString("tanggalBerdiri");
                                String deskripsi = jsonObject.getString("deskripsi");

                                nama.setText(namacafe);
                                tanggal.setText(tanggalBerdiri);
                                info.setText(deskripsi);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String errorMessage = error.getMessage();
                            Toast.makeText(requireContext(), "Failed to retrieve data from API: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
            requestQueue.add(jsonArrayRequest);
            return rootView;
        }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(-7.9547176, 112.6123228);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title("Lokasi")
                .snippet("cafe point filkom ub");
        Marker marker = map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}