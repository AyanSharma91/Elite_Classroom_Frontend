package com.example.elite_classroom.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.elite_classroom.FeedExtraUtilsKotlin;
import com.example.elite_classroom.FileUtils;
import com.example.elite_classroom.R;

import java.io.File;
import java.util.Objects;

public class Student_Assignment_Fragment extends Fragment {


    String title, description, work_id, due_data, attachment_link, class_code,user_status;
    TextView due_date, title_field,description_field;
    ImageView file_symbol;
    TextView file_name;
    File file;
    Uri file_uri;
    RelativeLayout attachement_layout_second;
    ImageView file_symbol_second;
    TextView file_name_second;
    TextView attachemnt_button, submit_button;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_fragment_assignment, container, false);

        title=           getArguments().getString("title");
        description=      getArguments().getString("description");
        work_id=          getArguments().getString("work_id");
        due_data=           getArguments().getString("due_data");
        attachment_link=   getArguments().getString("attachment_link");
        class_code=        getArguments().getString("class_code");

        attachement_layout_second = view.findViewById(R.id.attachement_layout_second);
        file_symbol_second= view.findViewById(R.id.file_symbol_second);
        file_name_second= view.findViewById(R.id.file_name_second);
        attachemnt_button= view.findViewById(R.id.attachemnt_button);
        submit_button = view.findViewById(R.id.submit_button);



        due_date = view.findViewById(R.id.due_date);
        title_field    = view.findViewById(R.id.title);
        description_field = view.findViewById(R.id.description);
        file_symbol = view.findViewById(R.id.file_symbol);
        file_name= view.findViewById(R.id.file_name);

        attachemnt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_img_dialog(0 );
            }
        });





        title_field.setText(title);
        due_date.setText(due_data);
        description_field.setText(description);



        String mineType="";
        if(!attachment_link.isEmpty())
        {
            file_name.setText(attachment_link.substring(attachment_link.lastIndexOf('/')+1));
            mineType=attachment_link.substring(attachment_link.lastIndexOf('.')) ;
        }


        switch (mineType)
        {
            case ".pdf":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.pdf_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
            {

                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ms_word_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.powerpoint_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.exce_placeholder));
                break;
            }
            default:{

                if(mineType.contains("image"))
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.image_placeholder));
                }
                else if(mineType.contains("video"))
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.video_placeholder));
                }
                else
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_attachment));
                }
            }

        }




        return view;
    }


    private void show_img_dialog(int type) {
        if(ContextCompat.checkSelfPermission(Objects.requireNonNull((Activity)getContext()), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||  ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            // Log.e(TAG, "setxml: peremission prob");
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},112);



        } else {
            open_Intent(type);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==112)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                open_Intent(0);

            }
            else
            {
                Toast.makeText(getContext(),"Access Denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void open_Intent(int type) {

        browseDocuments();

    }

    private void browseDocuments(){

        String[] mimeTypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                        "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "text/plain",
                        "application/pdf",
                        "application/zip",
                        "video/*", "image/*"
                };

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent,"ChooseFile"), 0);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filePath = "";
        if(getPathFromUri(getContext(),data.getData())!=null)
        {

            filePath = getPathFromUri(getContext(),data.getData());
        }
        else if(FileUtils.getPath(getContext(),data.getData())!=null)
        {
            filePath = FileUtils.getPath(getContext(),data.getData());
        }

        file_uri= data.getData();
        file = new File(filePath);


        if(file!=null)
        {


            attachement_layout_second.setVisibility(View.VISIBLE);

            file_name_second.setText(file.getName());


            String mineType = getMimeType(data.getData());

            switch (mineType)
            {
                case "application/pdf":
                {
                    file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.pdf_placeholder));
                    break;
                }

                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                {

                    file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ms_word_placeholder));
                    break;
                }

                case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                {
                    file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.powerpoint_placeholder));
                    break;
                }

                case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                {
                    file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.exce_placeholder));
                    break;
                }
                default:{

                    if(mineType.contains("image"))
                    {
                        file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.image_placeholder));
                    }
                    else if(mineType.contains("video"))
                    {
                        file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.video_placeholder));
                    }
                    else
                    {
                        file_symbol_second.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_attachment));
                    }
                }

            }
        }



    }

    public String getPathFromUri(Context context, Uri fileUri) {
        // SDK >= 11 && SDK < 19
        if (Build.VERSION.SDK_INT < 19) {
            return FeedExtraUtilsKotlin.INSTANCE.getRealPathFromURIAPI11to18(context, fileUri);
        } else {
            return FeedExtraUtilsKotlin.INSTANCE.getRealPathFromURIAPI19(context, fileUri);
        }// SDK > 19 (Android 4.4) and up
    }

    public String getMimeType(Uri uri) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = getContext().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }
}
