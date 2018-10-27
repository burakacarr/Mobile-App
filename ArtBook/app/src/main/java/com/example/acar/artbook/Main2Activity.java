package com.example.acar.artbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    ImageView ımageView;
    EditText editText;
    Button button;
    Bitmap selectedImages;
    static SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ımageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        Intent ıntent = getIntent();
        if(info.equalsIgnoreCase("new")){
            Bitmap background = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.select);
            ımageView.setImageBitmap(background);
            button.setVisibility(View.VISIBLE);
            editText.setText("");
        }
        else{
            String artName = ıntent.getStringExtra("artName");
            editText.setText(artName);
            int position = ıntent.getIntExtra("position",0);
            ımageView.setImageBitmap(MainActivity.artImage.get(position));
            button.setVisibility(View.INVISIBLE);
        }
    }

 //resim üzerine tıklanması durumu
    public void SelectImages(View view){

        // izin var mı galeriye erişim izni yoksa al
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);

        }else{
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1);
        }
    }
    // ilk kez izin aldğında bu fonksiyon çalışacak
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==2){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    // galerinden resmi okuyup çekme
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            Uri image = data.getData();

            try{
                selectedImages = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
                ımageView.setImageBitmap(selectedImages);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Save(View view){
        String ArtName = editText.getText().toString();
       // kullanıcıdan aldığımız resmi byte array halinde db ye kaydedeceğiz
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        selectedImages.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] bytes = outputStream.toByteArray();


        try{
            // DB OLUŞTURUP TABLO AÇTIK
            database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS  arts (NAME VARCHAR, IMAGE BLOB)");

            // database.execSQL("INSERT INTO arts (NAME,IMAGE ) VALUES(?,?)"); şeklinde yazdığımızda verileri soru işareti olan alana geçiremiyoruz bu yüzden alttaki şekilde yazdık

            // veri kaydetme
            String sqlString = "INSERT INTO arts (NAME,IMAGE ) VALUES(?,?)";
            SQLiteStatement statement =database.compileStatement(sqlString);
            statement.bindString(1,ArtName);
            statement.bindBlob(2,bytes);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        Intent ıntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ıntent);
    }

}
