package gan.obito.carta.carta;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Gasik Sambada on 10/21/2016.
 */
public class Result extends Activity implements View.OnClickListener {
    private String ResultNama,ResultKodeWarna,ResultTingkatResiko,ResultDeskripsi;
    private Integer ResultResikoID;
    private TextView nama,tingkat_resiko,deskripsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.result);

        Bundle bResult = getIntent().getExtras();
        if (bResult != null) {
            ResultNama = bResult.getString("nama");
            ResultResikoID = bResult.getInt("risiko_id");
            ResultKodeWarna = bResult.getString("kode_warna");
            ResultTingkatResiko = bResult.getString("tingkat_risiko");
            ResultDeskripsi = bResult.getString("deskripsi");

            nama = (TextView) findViewById(R.id.nama);
            tingkat_resiko = (TextView) findViewById(R.id.tingkat_resiko);
            deskripsi = (TextView) findViewById(R.id.deskripsi);

            nama.setText(ResultNama);
            tingkat_resiko.setText(ResultTingkatResiko);
            deskripsi.setText(Html.fromHtml(ResultDeskripsi));

            switch(ResultResikoID) {
                case 1:
                    tingkat_resiko.setBackgroundResource(R.drawable.resiko_1);
                    break;
                case 2:
                    tingkat_resiko.setBackgroundResource(R.drawable.resiko_2);
                    break;
                case 3:
                    tingkat_resiko.setBackgroundResource(R.drawable.resiko_3);
                    break;
                case 4:
                    tingkat_resiko.setBackgroundResource(R.drawable.resiko_4);
                    break;
                case 5:
                    tingkat_resiko.setBackgroundResource(R.drawable.resiko_5);
                    break;
            }

        }else{
            alertBox("Error get calculate");
        }
    }

    public void onClick(View v) {

    }

    public void alertBox(String message) {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
        alertBox.setMessage(message);
        alertBox.setCancelable(true);

        alertBox.setNegativeButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = alertBox.create();
        alert.show();
    }
}
