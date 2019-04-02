package com.proyek.rahmanjai.eatit.Model;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */

import org.parceler.Parcel;

import java.util.ArrayList;


@Parcel
public class ElementsArray {
    ArrayList<DistanceDuration> elements;

    public ElementsArray() {
    }

    public ArrayList<DistanceDuration> getElements() {
        return elements;
    }

    public void setElements(ArrayList<DistanceDuration> elements) {
        this.elements = elements;
    }
}
