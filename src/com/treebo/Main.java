package com.treebo;

import com.google.flatbuffers.FlatBufferBuilder;
import com.treebo.UserData.Gender;
import com.treebo.UserData.User;
import com.treebo.UserData.UserList;

import java.nio.ByteBuffer;

/**
 * Created by aa on 19/08/16.
 */
public class Main {

    public static void main(String[] args) {

        //create sample data
        byte[] data = Utils.createSampleData();

        /* code to store buffer or send on network goes here... */
        Utils.writeBufferToFile(data);

        //print data
        Utils.printData(Utils.readBufferFromFile());

    }

}
