package cc.poh.first.ui.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatSpinner;

/**
* https://stackoverflow.com/questions/18447063/spinner-get-state-or-get-notified-when-opens/27692614
* https://stackoverflow.com/questions/34862125/dropdown-buttons-spinner-like-the-ones-on-the-design-specs-from-google
*/
@SuppressWarnings("unused")
public class MaterialSpinner extends AppCompatSpinner {
    private static final String TAG = "MaterialSpinner";
    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;

    public MaterialSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public MaterialSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialSpinner(Context context, int mode) {
        super(context, mode);
    }

    public MaterialSpinner(Context context) {
        super(context);
    }

    public interface OnSpinnerEventsListener {

        void onSpinnerOpened();

        void onSpinnerClosed();

    }

    @Override
    public boolean performClick() {
        // register that the Spinner was opened so we have a status
        // indicator for the activity(which may lose focus for some other
        // reasons)
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened();
        }
        return super.performClick();
    }

    public void setSpinnerEventsListener(OnSpinnerEventsListener onSpinnerEventsListener) {
        mListener = onSpinnerEventsListener;
    }

    /**
     * Propagate the closed Spinner event to the listener from outside.
     */
    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed();
        }
    }

    /**
     * A boolean flag indicating that the Spinner triggered an open event.
     *
     * @return true for opened Spinner
     */
    public boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        android.util.Log.d(TAG, "onWindowFocusChanged");
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasBeenOpened() && hasWindowFocus) {
            android.util.Log.i(TAG, "closing popup");
            performClosedEvent();
        }
    }
}
