package gan.obito.carta.carta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import gan.obito.carta.carta.model.Hasil;
import gan.obito.carta.carta.model.Pemeriksaan;
import gan.obito.carta.carta.service.APIService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Gasik Sambada on 10/21/2016.
 */
public class Input extends Activity implements View.OnClickListener {
    private Button bKirim;
    private ProgressDialog pDialog;
    private EditText EditNama,EditUsia,EditKolestrol,EditTekananDarah;
    private Integer SelectedKelamin,SelectedMerokok,SelectedDiabetes,ResultResikoID;
    private RadioGroup GroupKelamin,GroupMerokok,GroupDiabetes;
    private RadioButton EditKelamin,EditMerokok,EditDiabetes;
    private String ValueKelamin,ValueMerokok,ValueDiabetes,ResultNama,ResultKodeWarna,ResultTingkatResiko,ResultDeskripsi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.input);



        EditNama = (EditText) findViewById(R.id.nama);
        EditUsia = (EditText) findViewById(R.id.usia);
        EditKolestrol = (EditText) findViewById(R.id.kolesterol);
        EditTekananDarah = (EditText) findViewById(R.id.tekanandarah);

        GroupKelamin =  (RadioGroup) findViewById(R.id.kelamin);
        GroupMerokok =  (RadioGroup) findViewById(R.id.merokok);
        GroupDiabetes =  (RadioGroup) findViewById(R.id.diabetes);

        bKirim = (Button) findViewById(R.id.kirim);
        bKirim.setOnClickListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Mohon tunggu sebentar..");
        pDialog.setCancelable(false);
    }

    public void showpDialog() {
        pDialog.show();
    }

    public void hidepDialog() {
        pDialog.dismiss();
    }

    public void onClick(View v) {
        switch(v.getId()){

            case R.id.kirim:
                calculateCarta();
                break;
        }
    }

    private boolean calculateCarta() {
        showpDialog();
        ResultNama = "";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://stage.edutech.co.id/ptm-api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        SelectedKelamin = GroupKelamin.getCheckedRadioButtonId();
        SelectedDiabetes = GroupDiabetes.getCheckedRadioButtonId();
        SelectedMerokok = GroupMerokok.getCheckedRadioButtonId();

        EditKelamin = (RadioButton) findViewById(SelectedKelamin);
        EditDiabetes = (RadioButton) findViewById(SelectedDiabetes);
        EditMerokok = (RadioButton) findViewById(SelectedMerokok);

        if(SelectedKelamin > -1){
            if(EditKelamin.getText().toString().equals("Laki-laki")){
                ValueKelamin = "L";
            }
            if(EditKelamin.getText().toString().equals("Perempuan")){
                ValueKelamin = "P";
            }
        }else{

        }

        if(SelectedDiabetes > -1){
            if(EditDiabetes.getText().toString().equals("Ya")){
                ValueDiabetes = "ya";
            }
            if(EditDiabetes.getText().toString().equals("Tidak")){
                ValueDiabetes = "tidak";
            }
        }else{

        }

        if(SelectedMerokok > -1){
            if(EditMerokok.getText().toString().equals("Ya")){
                ValueMerokok = "ya";
            }
            if(EditMerokok.getText().toString().equals("Tidak")){
                ValueMerokok = "tidak";
            }
        }else{

        }

        if(EditNama.getText().toString().equals("")){
            alertBox("Tolong isi nama anda");
            hidepDialog();
            return false;
        }
        if(EditUsia.getText().toString().equals("")){
            alertBox("Tolong isi usia anda");
            hidepDialog();
            return false;
        }
        if(EditKolestrol.getText().toString().equals("")){
            alertBox("Tolong isi tingkat kolestrol anda");
            hidepDialog();
            return false;
        }
        if(EditTekananDarah.getText().toString().equals("")){
            alertBox("Tolong isi tingkat tekanan darah anda");
            hidepDialog();
            return false;
        }

        Pemeriksaan Carta = new Pemeriksaan();
        Carta.setNama(EditNama.getText().toString());
        Carta.setUsia(Integer.parseInt(EditUsia.getText().toString()));
        Carta.setKolesterol(Float.parseFloat(EditKolestrol.getText().toString()));
        Carta.setTekanan_darah(Integer.parseInt(EditTekananDarah.getText().toString()));
        Carta.setGender(ValueKelamin);
        Carta.setDiabetes(ValueDiabetes);
        Carta.setMerokok(ValueMerokok);

        String strRequestBody = "{" +
                "\"nama\":\""+ Carta.getNama()+"\"," +
                "\"usia\":\""+ Carta.getUsia()+"\"," +
                "\"gender\":\""+ Carta.getGender()+"\"," +
                "\"kolesterol\":\""+ Carta.getKolesterol()+"\"," +
                "\"tekanan_darah\":\""+ Carta.getTekanan_darah()+"\"," +
                "\"merokok\":\""+ Carta.getMerokok()+"\"," +
                "\"diabetes\":\""+ Carta.getDiabetes()+"\"" +
                "}";
        Log.d("Testing Raw Data",strRequestBody);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),strRequestBody);
        Call<Pemeriksaan> call = service.calculateCarta(requestBody);

        call.enqueue(new Callback<Pemeriksaan>() {
            @Override
            public void onResponse(Response<Pemeriksaan> response, Retrofit retrofit) {
                Pemeriksaan pemeriksaan = response.body();
//                Log.d("Testing Raw Data","Success, Hasil : "+pemeriksaan.getStatus());
                if(pemeriksaan.getStatus().equals("Error")){
                    alertBox("Gagal : "+pemeriksaan.getError_description());
                }else {
                    ResultNama = pemeriksaan.getResult().getNama();
                    ResultTingkatResiko = pemeriksaan.getResult().getTingkat_risiko();
                    ResultKodeWarna = pemeriksaan.getResult().getKode_warna();
                    ResultResikoID = pemeriksaan.getResult().getRisiko_id();
                    ResultDeskripsi = pemeriksaan.getResult().getDeskripsi();

                    Intent intent_result = new Intent(Input.this, Result.class);
                    Bundle bQuestion = new Bundle();
                    bQuestion.putString("nama", ResultNama);
                    bQuestion.putInt("risiko_id", ResultResikoID);
                    bQuestion.putString("kode_warna", ResultKodeWarna);
                    bQuestion.putString("tingkat_risiko", ResultTingkatResiko);
                    bQuestion.putString("deskripsi", ResultDeskripsi);
                    intent_result.putExtras(bQuestion);
                    startActivity(intent_result);
                }
                hidepDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
                alertBox("Error get Hasil : "+t.toString());
            }
        });

        return true;
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
