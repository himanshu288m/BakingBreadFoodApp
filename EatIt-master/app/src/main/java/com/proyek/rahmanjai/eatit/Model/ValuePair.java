package com.proyek.rahmanjai.eatit.Model;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */

import org.parceler.Parcel;


@Parcel
public class ValuePair {
    String text;
    int value;

    public ValuePair(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public ValuePair() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
