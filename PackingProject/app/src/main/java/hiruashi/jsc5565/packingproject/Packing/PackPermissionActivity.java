package hiruashi.jsc5565.packingproject.Packing;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 정수찬 (jung suchan) on 2016-11-14.
 */

/**
 * Use inheritance instead of AppCompatAcitivity
 *
 * example
 * setPermissions(new String[]{Manifest.permission.CAMERA});
 * runPermission();
 */

public class PackPermissionActivity extends AppCompatActivity{

    private String[] permission;
    private boolean Activity_finish = true;
    private int request_num = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    /**
     * run permission
     */
    public void runPermission(){

        //Minimum running version is M.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            boolean denied = false;

            for(int i=0; i<permission.length; i++) {
                    //check permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), permission[i]) == PackageManager.PERMISSION_DENIED) {
                    denied = true;
                    break;
                }
            }

            //If permission denied, request permissions
            if(denied){
                ActivityCompat.requestPermissions(this, permission, request_num);
            }

        }
    }



    /**
     * put permission
     * @param permission
     */
    public void setPermissions(String[] permission){
        this.permission = permission;
    }



    /**
     * set finish (default : true)
     * @param Activity_finish
     */
    public void setFinish(boolean Activity_finish){
        this.Activity_finish = Activity_finish;
    }



    /**
     * Setting result of request permission
     * If denied and activity_finish is true, activity finish.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int i=0; i<grantResults.length; i++){
            if(grantResults[i] == PackageManager.PERMISSION_DENIED && Activity_finish){
                finish();
            }
        }
    }
}
