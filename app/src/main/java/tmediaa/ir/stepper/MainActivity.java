package tmediaa.ir.stepper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Subscribe;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import tmediaa.ir.stepper.otto.AppEvents;
import tmediaa.ir.stepper.otto.GlobalBus;

public class MainActivity extends AppCompatActivity  implements StepperLayout.StepperListener{
    private StepperLayout stepperLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(new StepAdapter(getSupportFragmentManager(), this),0);

        stepperLayout.setShowErrorStateEnabled(true);
        stepperLayout.setShowErrorStateOnBackEnabled(true);
        stepperLayout.setListener(this);


    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        GlobalBus.getInstanse().register(this);
    }

    @Override
    protected void onStop() {
        GlobalBus.getInstanse().unregister(this);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        int currentStepPosition = stepperLayout.getCurrentStepPosition();
        Log.d("LOOOG","pos: "+ currentStepPosition);
        if (currentStepPosition > 0) {
            stepperLayout.onBackClicked();
        } else {
            finish();
        }
    }

    @Subscribe
    public void onAppEvent(AppEvents events){
        Log.d("APPLOG","Otto Send: " + events.section);
    }

}
