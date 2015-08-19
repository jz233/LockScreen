package demo.zjj.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


public class UninstallActivity extends Activity {
    private DevicePolicyManager dpm;
    private ComponentName component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        component = new ComponentName(this, LockScreenReceiver.class);
        dpm.removeActiveAdmin(component);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:"+getPackageName()));
        startActivity(intent);
    }
}
