package gan.obito.carta.carta;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Gasik Sambada on 11/10/2016.
 */
public class Saran extends Activity {
    private String ResultSaranLanjutan;
    private TextView saran_lanjutan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.saran);

        Bundle bResult = getIntent().getExtras();
        if (bResult != null) {
            ResultSaranLanjutan = bResult.getString("saran_lanjutan");
            saran_lanjutan = (TextView) findViewById(R.id.saran_lanjutan);
            saran_lanjutan.setText(Html.fromHtml(ResultSaranLanjutan));
        }
    }
}
