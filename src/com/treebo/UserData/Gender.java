// automatically generated by the FlatBuffers compiler, do not modify

package com.treebo.UserData;

public final class Gender {
  private Gender() { }
  public static final byte Male = 1;
  public static final byte Female = 2;
  public static final byte Other = 3;

  public static final String[] names = { "Male", "Female", "Other", };

  public static String name(int e) { return names[e - Male]; }
}

