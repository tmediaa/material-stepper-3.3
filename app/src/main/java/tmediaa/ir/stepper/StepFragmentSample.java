package tmediaa.ir.stepper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import tmediaa.ir.stepper.otto.AppEvents;
import tmediaa.ir.stepper.otto.GlobalBus;

/**
 * Created by tmediaa on 8/20/2017.
 */

public class StepFragmentSample extends Fragment implements BlockingStep{
    private int TAP_THRESHOLD = 2;

    private Button sendData;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step, container, false);
        sendData = (Button) v.findViewById(R.id.sendData);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalBus.getInstanse().post(new AppEvents("salam"));
            }
        });
        //initialize your UI

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("APP_LOG","attach fragment 1");
        GlobalBus.getInstanse().register(this);

    }

    @Override
    public void onDetach() {
        Log.d("APP_LOG","dettach fragment 1");
        GlobalBus.getInstanse().unregister(this);
        super.onDetach();
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
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
        Log.d("APP_LOG","next is clicked" );
    }

    @Override
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.complete();
                Log.d("APP_LOG","call finish");
            }
        }, 2000L);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
        Log.d("APP_LOG"," onBackClicked");

    }
}