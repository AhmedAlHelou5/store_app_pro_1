package com.ahmed.store_app_pro_1.ui.models.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataAboutModel implements Serializable {

    @SerializedName("about_us")
    @Expose
    private String aboutUs;
    @SerializedName("privacy_policy")
    @Expose
    private String privacyPolicy;
    @SerializedName("terms_conditions")
    @Expose
    private String termsConditions;

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }



}
