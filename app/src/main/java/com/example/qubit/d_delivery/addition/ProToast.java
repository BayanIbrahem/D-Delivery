package com.example.qubit.d_delivery.addition;

import android.content.Context;
import android.widget.Toast;

/** like the ordinary toast but with more features to make showing toasts easier
 *  default values: the toast is short, kill previous and the toast is killable by the next**/
public class ProToast {

    private Toast toast;/*the toast is null before calling showToast*/
    private Context context;
    private boolean previousIsKillable;

    public ProToast(Context context){
        this.context = context;
        previousIsKillable = true;
    }

    //public methods A-Z:
    public void cancel(){
        if(toast != null) {
            toast.cancel();
        }
    }
    /*if the we select if the toast killable, so the result of last one is stored and
    * with condition (killPre && previousIsKillable) the toast is canceled(destroyed permanently)
    * this mean the current toast kills the previous and the previous allow killing by this*/
    public void show(String message, boolean isLong, boolean killPre, boolean killable){
        if(killPre && previousIsKillable) {
            cancel();
        }
        previousIsKillable = killable;
        toast = Toast.makeText(context, message, isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT);
        toast.show();
    }
    public void show(String message, boolean isLong, boolean killPre){
        show(message, isLong, killPre, true);
    }
    public void show(String message, boolean isLong){
        show(message, isLong, true, true);
    }
    public void show(String message){
        show(message, false, true, true);
    }
    //TODO: Allow formatting toast text.
}
