package com.example.sharepreference;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    String name;
    String email;
    int age;
    String phoneNumber;
    boolean Love;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getLove() {
        return Love;
    }

    public void setLove(boolean love) {
        Love = love;
    }

    public static Creator<UserModel> getCREATOR() {
        return CREATOR;
    }

    public UserModel(String name, String email, int age, String phoneNumber, boolean isLove) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.Love = isLove;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeInt(this.age);
        dest.writeString(this.phoneNumber);
        dest.writeByte(this.Love ? (byte) 1 : (byte) 0);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.age = in.readInt();
        this.phoneNumber = in.readString();
        this.Love = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
