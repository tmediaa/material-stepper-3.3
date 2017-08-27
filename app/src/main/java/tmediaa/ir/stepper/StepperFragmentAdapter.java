package tmediaa.ir.stepper;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

/**
 * Created by tmediaa on 8/19/2017.
 */

public class StepperFragmentAdapter extends AbstractFragmentStepAdapter {


    public StepperFragmentAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(@IntRange(from = 0) int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
