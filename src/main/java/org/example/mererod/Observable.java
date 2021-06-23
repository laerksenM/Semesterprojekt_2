package org.example.mererod;

public interface Observable  extends Runnable{
//nedarv Notify metoden fra Runnable interfacet
    //Giv dette her til noget, som I gerne vil kunne lytte/observere på

    public void registerObservers(SensorObserver sensorObserver);

}

