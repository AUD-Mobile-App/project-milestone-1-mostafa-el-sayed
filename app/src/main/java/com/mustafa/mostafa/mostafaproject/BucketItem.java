package com.mustafa.mostafa.mostafaproject;

import java.util.Date;
import java.util.List;

/**
 * Created by Mostafa on 3/22/2018.
 */

public class BucketItem
{
    public String itemName; // title of the bucket item
    public String itemDesc; // description of the bucket item
    public Date dueDate; // date of the bucket item
    public boolean isDone; // whether the item is done or not
    public double longitude, latitude; // item location

    public BucketItem()
    {

    }
    public BucketItem(String itemName, String itemDesc, Date dueDate, double longitude, double latitude, boolean isDone)
    {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.dueDate = dueDate;
        this.isDone = isDone;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
