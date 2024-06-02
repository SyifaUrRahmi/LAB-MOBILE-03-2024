package com.example.aplikasi4.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasi4.Instagram;
import com.example.aplikasi4.R;

// Deklarasi kelas PostinganFragment yang meng-extends Fragment
public class PostinganFragment extends Fragment {

    // Deklarasi variabel selectedImageUri untuk menyimpan URI gambar yang dipilih
    private Uri selectedImageUri;

    // Override method onCreateView untuk mengatur tampilan Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Meng-inflate layout XML fragment_postingan ke dalam tampilan (View) Fragment
        return inflater.inflate(R.layout.fragment_postingan, container, false);
    }

    // Override method onViewCreated yang dipanggil setelah tampilan Fragment selesai dibuat
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Memanggil implementasi method onViewCreated dari superclass
        super.onViewCreated(view, savedInstanceState);

        // Menginisialisasi ImageView iv_home dengan ID IV_Home dari layout view
        ImageView iv_home = view.findViewById(R.id.IV_Home);
        // Menginisialisasi ImageView iv_profile dengan ID IV_Profile dari layout view
        ImageView iv_profile = view.findViewById(R.id.IV_Profile);
        // Menginisialisasi ImageView iv_foto dengan ID iv_gambar dari layout view
        ImageView iv_foto = view.findViewById(R.id.iv_gambar);
        // Menginisialisasi EditText ET_konten dengan ID et_konten dari layout view
        EditText ET_konten = view.findViewById(R.id.et_konten);
        // Menginisialisasi Button btn_posting dengan ID btn_posting dari layout view
        Button btn_posting = view.findViewById(R.id.btn_posting);

        // Mengatur ActivityResultLauncher untuk memilih gambar dari galeri
        ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    // Mengecek apakah hasil aktivitas adalah RESULT_OK
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // Mengecek apakah data tidak null
                        if (data != null) {
                            // Mendapatkan URI gambar yang dipilih
                            selectedImageUri = data.getData();
                            // Mengecek apakah URI gambar tidak null
                            if (selectedImageUri != null) {
                                // Menampilkan gambar pada ImageView iv_foto
                                iv_foto.setImageURI(selectedImageUri);
                            }
                        }
                    }
                }
        );


        // Mengatur OnClickListener untuk ImageView iv_foto
        iv_foto.setOnClickListener(v -> {
            // Membuat Intent untuk membuka galeri dengan aksi ACTION_PICK
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            // Menentukan tipe konten yang dapat dipilih, dalam hal ini adalah gambar
            openGallery.setType("image/*");
            // Meluncurkan intent galeri dengan launcherIntentGallery yang telah diinisialisasi sebelumnya
            launcherIntentGallery.launch(openGallery);
        });

        // Mengatur OnClickListener untuk Button btn_posting
        btn_posting.setOnClickListener(btn -> {
            // Mengambil teks dari EditText ET_konten dan mengonversinya menjadi String
            String konten = ET_konten.getText().toString();

            // Mengecek apakah selectedImageUri tidak null
            if (selectedImageUri != null) {
                // Membuat objek Instagram dengan data yang diberikan
                Instagram instagram = new Instagram("tia Subianto", "Rahmatia", konten, R.drawable.tia, selectedImageUri);

                // Membuat Bundle untuk mengirim data Instagram ke Fragment lain
                Bundle bundle = new Bundle();
                bundle.putParcelable(HomeFragment.EXTRA_INSTAGRAM, instagram);

                // Membuat instance baru dari HomeFragment
                HomeFragment homeFragment = new HomeFragment();
                // Menetapkan data Instagram ke HomeFragment
                homeFragment.setArguments(bundle);

                // Mendapatkan FragmentManager dari parent Fragment atau Activity
                FragmentManager fragmentManager = getParentFragmentManager();
                // Memulai transaksi Fragment untuk mengganti tampilan dalam frame_container dengan HomeFragment yang baru
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, homeFragment)
                        // Menambahkan transaksi ke back stack untuk manajemen navigasi
                        .addToBackStack(null)
                        // Melakukan commit untuk menyelesaikan transaksi
                        .commit();

            } else {
                // Menampilkan pesan Toast jika gambar belum dipilih
                Toast.makeText(getActivity(), "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        // Mengatur OnClickListener untuk ImageView iv_home
        iv_home.setOnClickListener(v -> {
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Menghapus Fragment teratas dari back stack (jika ada)
            fragmentManager.popBackStack();
        });


        // Mengatur OnClickListener untuk ImageView iv_profile
        iv_profile.setOnClickListener(v -> {
            // Membuat instance baru dari ProfileFragment
            ProfileFragment profileFragment = new ProfileFragment();
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Memulai transaksi Fragment untuk mengganti tampilan dalam frame_container dengan profileFragment yang baru
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    // Menambahkan transaksi ke back stack untuk manajemen navigasi
                    .addToBackStack(null)
                    // Melakukan commit untuk menyelesaikan transaksi
                    .commit();
        });

    }
}
