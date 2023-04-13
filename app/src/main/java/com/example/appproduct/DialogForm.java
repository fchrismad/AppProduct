package com.example.appproduct;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    EditText edNama, edAlamat, edJumlah;
    Button btn_simpan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_form, container, false);
        edNama = view.findViewById(R.id.edNama);
        edAlamat = view.findViewById(R.id.edAlamat);
        edJumlah = view.findViewById(R.id.edJumlah);
        btn_simpan = view.findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = edNama.getText().toString();
                String getAlamat = edAlamat.getText().toString();
                String getJumlah = edJumlah.getText().toString();

                if (getNama.isEmpty()) {
                    edNama.setError("Masukan Judul!");
                } else if (getAlamat.isEmpty()) {
                    edAlamat.setError("Masukan Isi Laporan!");
                } else if (getJumlah.isEmpty()) {
                    edJumlah.setError("Masukan Tanggal Kejadian!");
                } else {
                    database.child("Pembeli").push().setValue(new ModelKirim(getNama, getAlamat, getJumlah)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), R.string.berhasil_kirim, Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), R.string.gagal_kirim, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
