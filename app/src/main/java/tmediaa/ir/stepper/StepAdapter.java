package tmediaa.ir.stepper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by tmediaa on 8/20/2017.
 */

public class StepAdapter extends AbstractFragmentStepAdapter {
    public StepAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        Fragment step = null;
        switch (position) {
            case 0:
                step = new StepFragmentSample();
                Bundle b1 = new Bundle();
                step.setArguments(b1);
                break;
            case 1:
                step = new StepFragmentSample2();
                Bundle b2 = new Bundle();
                step.setArguments(b2);
                break;
            case 2:
                step = new StepFragmentSample();
                Bundle b3 = new Bundle();
                step.setArguments(b3);

                break;

        }
        return (Step) step;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        StepViewModel.Builder builder = new StepViewModel.Builder(context)
                .setTitle("tab tab 1");
        switch (position) {
            case  0:
                builder.setNextButtonLabel("This way")
                        .setBackButtonLabel("Cancel")
                        .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
            break;
            case 1:
                builder.setNextButtonLabel("go_to_summary")
                        .setBackButtonLabel("Go to first");
                break;
            case 2:
                builder
                    .setNextButtonLabel("step 3")
                    .setBackButtonLabel("asdad");

                break;

        }
        return builder.create();


    }
}
