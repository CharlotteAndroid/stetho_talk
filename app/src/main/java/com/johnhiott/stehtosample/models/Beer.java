package com.johnhiott.stehtosample.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

@Table(name = "beers")
public class Beer extends Model {

    @SerializedName("name") @Column(name = "name") String mName;
    @SerializedName("nameDisplay") String mNameDispaly;
    @SerializedName("labels") Labels mLabels;

    public Beer(){
        super();
    }

    public Beer(String name ){
        super();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNameDispaly() {
        return mNameDispaly;
    }

    public void setNameDispaly(String nameDispaly) {
        mNameDispaly = nameDispaly;
    }

    public Labels getLabels() {
        return mLabels;
    }

    public void setLabels(Labels labels) {
        mLabels = labels;
    }

    public class Labels {
        @SerializedName("icon") String mIcon;
        @SerializedName("medium") String mMedium;
        @SerializedName("large") String mLarge;

        public String getIcon() {
            return mIcon;
        }

        public void setIcon(String icon) {
            mIcon = icon;
        }

        public String getMedium() {
            return mMedium;
        }

        public void setMedium(String medium) {
            mMedium = medium;
        }

        public String getLarge() {
            return mLarge;
        }

        public void setLarge(String large) {
            mLarge = large;
        }
    }

}
