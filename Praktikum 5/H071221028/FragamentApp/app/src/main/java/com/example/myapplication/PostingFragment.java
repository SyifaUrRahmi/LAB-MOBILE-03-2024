package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.model.PostPersonal;


public class PostingFragment extends Fragment {
    private ImageButton postingImageButton;
    private Button postingButton;
    private EditText postContentEditText;
    private ActivityResultLauncher<Intent> openGalleryIntentLauncher;

    private Uri selectedImageUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_posting,container,false);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postingImageButton = view.findViewById(R.id.post_image_btn);
        postingButton= view.findViewById(R.id.posting_button);
        postContentEditText = view.findViewById(R.id.post_konten_et);

        openGalleryIntentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result->{
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data != null){
                            this.selectedImageUri = data.getData();
                            if(selectedImageUri != null){
                                postingImageButton.setImageURI(selectedImageUri);
                            }

                        }
                    }
                }
        );
        postingImageButton.setOnClickListener(this::onPostImageButtonClick);
        postingButton.setOnClickListener(this::onPostingButtonClick);

    }

    public void onPostImageButtonClick(View view){
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        openGalleryIntentLauncher.launch(openGalleryIntent);
    }

    public void onPostingButtonClick(View view) {
        String content = postContentEditText.getText().toString();
        if (content.isEmpty()) {
            Toast.makeText(getActivity(), "isi kontennta", Toast.LENGTH_SHORT).show();
        } else if (selectedImageUri == null) {
            Toast.makeText(getActivity(),"isi gambar", Toast.LENGTH_SHORT).show();
        } else{
            DB.add(new PostPersonal("awa","amalia_awa", content,R.drawable.audi_logo,this.selectedImageUri ));
            postContentEditText.setText("");
            postingImageButton.setImageResource(R.drawable.add_photo);

            HomeFragment homeFragment = new HomeFragment();
            FragmentManager  fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_layout,homeFragment).addToBackStack(null).commit();
        }

    }}