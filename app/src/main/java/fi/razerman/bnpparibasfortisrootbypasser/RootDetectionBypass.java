package fi.razerman.bnpparibasfortisrootbypasser;

/**
 * Created by Razerman on 14.10.2016.
 */


import android.util.Log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XC_MethodReplacement.returnConstant;

public class RootDetectionBypass implements IXposedHookLoadPackage {
    private static final String TAG = RootDetectionBypass.class.getSimpleName();

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("be.bnpparibasfortis.helloprepaid") || lpparam.packageName.equals("be.bnpparibasfortis.bnppfprepaid")){
            Log.d(TAG, "app detected, starting to bypass root detection!");

            // Check for test-keys
            // Check for the following files
            /* /system/app/Superuser.apk
            * /sbin/su
            * /system/bin/su
            * /system/xbin/su
            * /data/local/xbin/su
            * /data/local/bin/su
            * /system/sd/xbin/su
            * /system/bin/failsafe/su
            * /data/local/su */
            // Check if can execute su
            // External call RootShell.isAccessGiven()
            findAndHookMethod("be.bnpparibasfortis.bnppfprepaid.e.h", lpparam.classLoader, "a", returnConstant(false));           // Check 1

            Log.d(TAG, "Bypassed " + TAG + "'s root detection!");
        }
    }
}
