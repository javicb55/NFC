package vcarmen.es.nfctutorial;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter != null && nfcAdapter.isEnabled()){
            Toast.makeText(this, "NFC AVAIABLE", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "NFC not avaiable", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this, "NFC Recivido", Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {

        Intent intent = new Intent (this, MainActivity.class);
        intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentFilters = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this,pendingIntent,intentFilters,null);
        super.onResume();
    }
}
