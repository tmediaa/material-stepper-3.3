package tmediaa.ir.stepper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import tmediaa.ir.stepper.otto.AppEvents;
import tmediaa.ir.stepper.otto.GlobalBus;

/**
 * Created by tmediaa on 8/20/2017.
 */

public class StepFragmentSample2 extends Fragment implements BlockingStep {
    private int TAP_THRESHOLD = 2;

    private TextView showText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step_2, container, false);

        showText = (TextView) v.findViewById(R.id.showData);
        showText.setText("start called");
        //initialize your UI

        return v;
    }

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next step, create a new VerificationError instance otherwise
        return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }


    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        callback.getStepperLayout().showProgress("Operation in progress, please wait...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.goToNextStep();
                callback.getStepperLayout().hideProgress();
            }
        }, 2000L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
        Log.d("APP_LOG","on complete");

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
        Log.d("APP_LOG"," onBackClicked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("APP_LOG","attach fragment 2");
        GlobalBus.getInstanse().register(this);

    }

    @Override
    public void onDetach() {
        Log.d("APP_LOG","dettach fragment 2");
        GlobalBus.getInstanse().unregister(this);
        super.onDetach();
    }

    @Subscribe
    public void onAppEvent(AppEvents events){
        showText.setText("Recvived From: "+ events.section);
        Log.d("APPLOG","Recived from fragment " + events.section);
    }


}