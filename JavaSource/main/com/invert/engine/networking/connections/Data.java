package com.invert.engine.networking.connections;

import java.io.Serializable;

import static com.invert.engine.Constants.EMPTY_DATA;

/**
 * Created by Mathew on 7/12/2017.
 */
public class Data implements Serializable{

    public static final long serialVersionUID = 1L;
    private final int id;

    public Data(int id){
        this.id = id;
    }


    public int getId(){return id;}
    public int getType(){return EMPTY_DATA;}

}
