package com.treebo;

import com.google.flatbuffers.FlatBufferBuilder;
import com.treebo.UserData.Gender;
import com.treebo.UserData.User;
import com.treebo.UserData.UserList;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by aa on 19/08/16.
 */
final class Utils {

    static byte[] createSampleData() {
        //create a FlatBufferBuilder
        FlatBufferBuilder builder = new FlatBufferBuilder(0);

        //create user's list
        int[] users = new int[2];

        //add first user
        int user1Name = builder.createString("Ankit");
        int user1Company = builder.createString("Treebo Hotels");
        users[0] = User.createUser(builder, (short) 0, user1Name, (byte) 24, Gender.Male, user1Company);

        //add second user
        int user2Name = builder.createString("K");
        int user2Company = builder.createString("Treebo Hotels");
        users[1] = User.createUser(builder, (short) 1, user2Name, (byte) 24, Gender.Female, user2Company);

        //create user's list vector
        int userList = UserList.createUserListVector(builder, users);

        //start creating user list object
        UserList.startUserList(builder);
        //add user's list vector
        UserList.addUserList(builder, userList);
        //end creating user list object
        int userListObject = UserList.endUserList(builder);
        //finish building buffer
        UserList.finishUserListBuffer(builder, userListObject);

        //get bytes buffer from flat buffer builder
        return builder.sizedByteArray();
    }

    static void printData(byte[] data) {
        //reading from ByteBuffer
        UserList testUserList = UserList.getRootAsUserList(ByteBuffer.wrap(data));

        //print data
        for (int i = 0, size = testUserList.userListLength(); i < size; i++) {
            System.out.println(testUserList.userList(i).id() + " : " +
                    testUserList.userList(i).name() + " : " +
                    testUserList.userList(i).age() + " : " +
                    testUserList.userList(i).gender() + " : " +
                    testUserList.userList(i).company()
            );
        }
    }

    static boolean writeBufferToFile(byte[] data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("UserData"), false);
            fileOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;

    }

    static byte[] readBufferFromFile() {
        byte[] data = null;
        try {
            RandomAccessFile f = new RandomAccessFile("UserData", "r");
            data = new byte[(int) f.length()];
            f.readFully(data);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
