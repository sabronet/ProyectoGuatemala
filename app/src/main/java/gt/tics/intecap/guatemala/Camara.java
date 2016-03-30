package gt.tics.intecap.guatemala;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class Camara extends Activity implements View.OnClickListener {

    private static final int CAMARA_DATA = 0;
    private ImageView devuelve;
    private ImageButton toma;
    private Button asigna;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        iniciaVars();

        InputStream is = getResources().openRawResource(R.raw.android);
        bmp = (Bitmap) BitmapFactory.decodeStream(is);
        devuelve.setImageBitmap(bmp);
    }

    private void iniciaVars() {
        devuelve = (ImageView) findViewById(R.id.ivDevuelvePicture);
        toma = (ImageButton) findViewById(R.id.ibTomarPicture);
        asigna = (Button) findViewById(R.id.bSetWallpaper);
        toma.setOnClickListener(this);
        asigna.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibTomarPicture:
                Intent miIntento = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(miIntento,CAMARA_DATA);
                break;
            case R.id.bSetWallpaper:
                try {
                    WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
                    // ay que estudia Content y Context / el contenido y donde se hace la ejecucion // ej content=agua context=botella
                    // otro ejemplo: content es en donde vio y context en donde estoy ahorita
                    wm.setResource(R.raw.android);

                    // wm.setResource(bmp);
                    // para hacerlo bien con bmp: http://developer.android.com/reference/android/app/WallpaperManager.html

                    // pidio que vieramos video de youtube: https://www.youtube.com/watch?v=TSxL62AI6Wo

                } catch (IOException e) {
                    e.printStackTrace();

                }
                // ahora debo poner el permiso en el manifesto
                break;
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle cesta = data.getExtras();//Cats
            bmp = (Bitmap) cesta.get("data");
            devuelve.setImageBitmap(bmp);
        }

    }



}