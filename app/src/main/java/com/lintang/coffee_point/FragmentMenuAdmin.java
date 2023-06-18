package com.lintang.coffee_point;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lintang.coffee_point.Model.MenuAdminItem;
import com.lintang.coffee_point.adapter.MenuAdminAdapter;
import com.lintang.coffee_point.adapter.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenuAdmin extends Fragment implements RecyclerViewInterface {

    private View lhMenuAdmin;
    private RecyclerView recyclerViewMenuAdmin;
    private MenuAdminAdapter menuAdminAdapter;
    private List<MenuAdminItem> menuAdminItems;
    private Button btn_add;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        lhMenuAdmin = inflater.inflate(R.layout.activity_menu_admin, container, false);

        progressDialog = new ProgressDialog(requireContext());

        recyclerViewMenuAdmin = lhMenuAdmin.findViewById(R.id.recyclerViewMenu);
        recyclerViewMenuAdmin.setLayoutManager(new LinearLayoutManager(requireContext()));
        menuAdminItems = new ArrayList<>();
        menuAdminAdapter = new MenuAdminAdapter(requireContext(), menuAdminItems, this);
        recyclerViewMenuAdmin.setAdapter(menuAdminAdapter);
        btn_add = lhMenuAdmin.findViewById(R.id.btn_add);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

        showData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddItem.class);
                startActivity(intent);
            }
        });

        return lhMenuAdmin;
    }

    public void showData() {
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.show();
        db.collection("restaurant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        menuAdminItems.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MenuAdminItem menuAdmin = new MenuAdminItem(document.getId(), document.getString("name"), document.getString("harga"), document.getString("desc"), document.getString("gambar"));
                                menuAdminItems.add(menuAdmin);
                            }
                            menuAdminAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(requireContext(), "Data gagal diambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(int position) {

    }

    public void onDelete(int position) {
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menghapus data...");
        progressDialog.show();
        db.collection("restaurant").document(menuAdminItems.get(position).getDocId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        showData();
                        Toast.makeText(requireContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(requireContext(), "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onEdit(int position) {

    }
}
