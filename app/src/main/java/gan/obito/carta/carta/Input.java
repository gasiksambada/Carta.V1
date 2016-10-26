package gan.obito.carta.carta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

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
    private Integer SelectedKelamin,SelectedMerokok,SelectedDiabetes;
    private RadioGroup GroupKelamin,GroupMerokok,GroupDiabetes;
    private RadioButton EditKelamin,EditMerokok,EditDiabetes;
    private String ValueKelamin,ValueMerokok,ValueDiabetes;

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
                //Intent intent_result = new Intent(this, Hasil.class);
                //startActivity(intent_result);
                break;
        }
    }

    private void calculateCarta() {
        showpDialog();

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
                Log.d("Testing Raw Data","Success, Hasil : "+pemeriksaan.getResult().getTingkat_risiko());
                hidepDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Testing Raw Data","Error get Hasil : "+t.toString());
                hidepDialog();
            }
        });
    }
}
