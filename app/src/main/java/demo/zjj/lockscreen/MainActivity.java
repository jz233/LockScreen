package demo.zjj.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DevicePolicyManager dpm;
    private ComponentName component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //Admin component
        component = new ComponentName(this,LockScreenReceiver.class);
        if(dpm.isAdminActive(component)){   //one-touch lock screen
            dpm.lockNow();
            finish();
        }else{  //View to enable admin
            setContentView(R.layout.activity_main);
        }

    }

    public void lock(View view){
        if(dpm.isAdminActive(component)){
            dpm.lockNow();
        }else{
            Toast.makeText(this,"Plz enable admin",Toast.LENGTH_SHORT).show();
        }

    }

    public void activate(View view){
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
               "EXTRA_ADD_EXPLANATION");
        startActivity(intent);

    }


}
