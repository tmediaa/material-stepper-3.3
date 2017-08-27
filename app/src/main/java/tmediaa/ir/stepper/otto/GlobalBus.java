package tmediaa.ir.stepper.otto;

import com.squareup.otto.Bus;

/**
 * Created by tmediaa on 8/20/2017.
 */

public class GlobalBus extends Bus {
    private static final GlobalBus bus = new GlobalBus();

    public static Bus getInstanse(){
        return bus;
    }

    private GlobalBus(){

    }
}

