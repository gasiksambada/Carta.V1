package gan.obito.carta.carta;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gasik Sambada on 10/21/2016.
 */
public class Result extends AppCompatActivity implements View.OnClickListener {
    private String ResultNama,ResultKodeWarna,ResultTingkatResiko,ResultDeskripsi,ResultSaranSingkat,ResultSaranLanjutan;
    private Integer ResultResikoID;
    private TextView nama,tingkat_resiko,deskripsi,saran_singkat,lihat_lanjutan_saran;
    private ImageView bButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        setContentView(R.layout.result);

        bButton = (ImageView) findViewById(R.id.back_button);
        bButton.setVisibility(View.VISIBLE);
        bButton.setOnClickListener(this);

        Bundle bResult = getIntent().getExtras();
        if (bResult != null) {
            ResultNama = bResult.getString("nama");
            ResultResikoID = bResult.getInt("risiko_id");
            ResultKodeWarna = bResult.getString("kode_warna");
            ResultTingkatResiko = bResult.getString("tingkat_risiko");
            ResultDeskripsi = bResult.getString("deskripsi");
            ResultSaranSingkat = bResult.getString("saran_singkat");
            ResultSaranLanjutan = bResult.getString("saran_lanjutan");

            nama = (TextView) findViewById(R.id.nama);
            tingkat_resiko = (TextView) findViewById(R.id.tingkat_resiko);
            deskripsi = (TextView) findViewById(R.id.deskripsi);
            saran_singkat = (TextView) findViewById(R.id.saran_singkat);
            lihat_lanjutan_saran = (TextView) findViewById(R.id.lihat_lanjutan_saran);
            SpannableString content = new SpannableString("LIHAT SARAN LANJUTAN");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            lihat_lanjutan_saran.setText(content);
            lihat_lanjutan_saran.setOnClickListener(this);

            nama.setText(ResultNama);
            tingkat_resiko.setText(ResultTingkatResiko);
            deskripsi.setText(Html.fromHtml(ResultDeskripsi));
            saran_singkat.setText(Html.fromHtml(ResultSaranSingkat));

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
        switch(v.getId()){

            case R.id.lihat_lanjutan_saran:
                Intent intent_saran = new Intent(Result.this, Saran.class);
                Bundle bQuestion = new Bundle();
                bQuestion.putString("saran_lanjutan", ResultSaranLanjutan);
                bQuestion.putString("tingkat_risiko", ResultTingkatResiko);
                intent_saran.putExtras(bQuestion);
                startActivity(intent_saran);
                break;

            case R.id.back_button:
                finish();
                break;
        }
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
