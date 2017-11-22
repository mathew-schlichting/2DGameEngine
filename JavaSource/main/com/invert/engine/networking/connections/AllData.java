package com.invert.engine.networking.connections;

import static com.invert.engine.Constants.ALL_DATA;

/**
 * Created by Mathew on 7/16/2017.
 */
public class AllData extends Data {

    private Data[] data;

    public AllData(Data[] data){
        super(0);
        this.data = data;
    }

    public Data[] getData(){return data;}

    public int getType(){return ALL_DATA;}
}
