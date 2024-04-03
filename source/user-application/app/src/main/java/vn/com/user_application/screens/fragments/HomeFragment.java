package vn.com.user_application.screens.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import vn.com.user_application.Application;
import vn.com.user_application.R;
import vn.com.user_application.adapters.HomeRvDentistAdapter;
import vn.com.user_application.core.models.Dentist;
import vn.com.user_application.core.models.Response;
import vn.com.user_application.core.network.ApiService;
import vn.com.user_application.screens.customs.VerticalSpaceItemDecoration;


public class HomeFragment extends Fragment {

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    callSupport();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(requireActivity(), "This function is not available due to permission denied", Toast.LENGTH_SHORT).show();
                }
            });
    private CardView cardViewContactSP;
    private TextView tvViewAllDoctor,tvCustomerName;
    private final List<Dentist> dentistList = new ArrayList<>();
    HomeRvDentistAdapter adapter = new HomeRvDentistAdapter(dentistList, Application.getSimpleDateFormat());

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        fetchDentistData();
        listeningEvents();
    }

    private void fetchDentistData() {
        ApiService.apiService.fetchAllDentists().enqueue(
                new Callback<Response<List<Dentist>>>() {
                    @Override
                    public void onResponse(Call<Response<List<Dentist>>> call, retrofit2.Response<Response<List<Dentist>>> response) {
                        if(response.isSuccessful()){
                            if (response.body() != null && response.body().getMessage().equals("OK")){
                                dentistList.clear();
                                dentistList.addAll(response.body().getData());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<List<Dentist>>> call, Throwable throwable) {
                        Toast.makeText(requireContext(),"Failed To Get Data, switch to local data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    private void listeningEvents() {
        cardViewContactSP.setOnClickListener(v ->{
            if (ContextCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                // You can use the API that requires the permission.
                callSupport();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(), Manifest.permission.CALL_PHONE)) {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showInContextUI();
            } else {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                        Manifest.permission.CALL_PHONE);
            }
        });
    }

    private void showInContextUI() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("This function need your permission!! \n If you deny, this function will be not available to used");
        builder.setTitle("Alert!!");
        builder.setNegativeButton("Refuse",(dialog, which) -> {
            dialog.dismiss();
            Toast.makeText(requireActivity(), "This function is not available due to permission denied", Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("OK!", ((dialog, which) -> requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)));
        builder.show();
    }

    private void callSupport() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "+1 555-123-4567"));
        startActivity(intent);
    }

    private void initView(View view) {
        // Mapping with View
        cardViewContactSP = view.findViewById(R.id.cvContactSP);
        tvCustomerName = view.findViewById(R.id.tvCustomerName);
        tvCustomerName.setText(Application.currentUser.getFullName());
        RecyclerView dentistsRv = view.findViewById(R.id.rvDoctors);

        // Config Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        dentistsRv.setLayoutManager(layoutManager);
        // Add Space between elements
        dentistsRv.addItemDecoration(new VerticalSpaceItemDecoration(20));
        dentistsRv.setAdapter(adapter);
    }
}