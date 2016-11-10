package gan.obito.carta.carta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.xml.sax.XMLReader;

/**
 * Created by Gasik Sambada on 11/10/2016.
 */
public class Saran extends AppCompatActivity implements View.OnClickListener {
    private String ResultSaranLanjutan,ResultTingkatResiko;
    private TextView saran_lanjutan,judul;
    private ImageView bButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        setContentView(R.layout.saran);

        bButton = (ImageView) findViewById(R.id.back_button);
        bButton.setVisibility(View.VISIBLE);
        bButton.setOnClickListener(this);

        Bundle bResult = getIntent().getExtras();
        if (bResult != null) {
            ResultSaranLanjutan = bResult.getString("saran_lanjutan");
            ResultTingkatResiko = bResult.getString("tingkat_risiko");
            judul = (TextView) findViewById(R.id.judul);
            judul.setText("Tingkat Resiko "+ResultTingkatResiko);
            saran_lanjutan = (TextView) findViewById(R.id.saran_lanjutan);
            saran_lanjutan.setText(Html.fromHtml(ResultSaranLanjutan));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_button:
                finish();
                break;
        }
    }
}
