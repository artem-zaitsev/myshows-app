package com.nikart.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Artem on 01.05.2017.
 */
@DatabaseTable(tableName = com.nikart.model.dto.ShowTmp.TABLE_NAME)
public class ShowTmp {

    public final static String TABLE_NAME = "show_tmp";
    public final static String FIELD_NAME_ID = "id";


    @DatabaseField(columnName = FIELD_NAME_ID, id = true)
    @SerializedName("id")
    private int id;

    @SerializedName("ruTitle")
    private String title;

    @SerializedName("title")
    private String titleOriginal;

    @SerializedName("image")
    private String imageUrl;

    public ShowTmp() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public int getId() {
        return id;
    }
}


