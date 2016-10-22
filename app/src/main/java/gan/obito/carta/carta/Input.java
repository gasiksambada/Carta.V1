package gan.obito.carta.carta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gasik Sambada on 10/21/2016.
 */
public class Input extends Activity implements View.OnClickListener {
    Button bKirim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.input);

        bKirim = (Button) findViewById(R.id.kirim);
        bKirim.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()){

            case R.id.kirim:
                Intent intent_result = new Intent(this, Result.class);
                startActivity(intent_result);
                break;
        }
    }
}
