package com.ahmed.store_app_pro_1;

import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;
import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;

import java.util.ArrayList;

public class Utils {

    static ArrayList<SliderImageHomeModel> images = new ArrayList<>();
    public static ArrayList<SliderImageHomeModel> FillImages(){
        images=new ArrayList<>();
        images.add(new SliderImageHomeModel(R.drawable.image_slider1));
        images.add(new SliderImageHomeModel(R.drawable.image2));
        images.add(new SliderImageHomeModel(R.drawable.image_slider1));
        images.add(new SliderImageHomeModel(R.drawable.image2));
        images.add(new SliderImageHomeModel(R.drawable.image_slider1));
        images.add(new SliderImageHomeModel(R.drawable.image2));


        return images;
    }



    static ArrayList<CategoriesModel> categories = new ArrayList<>();

    public static ArrayList<CategoriesModel> FillCategories(){
        categories=new ArrayList<>();
        categories.add(new CategoriesModel(R.drawable.category1,"عجلات"));
        categories.add(new CategoriesModel(R.drawable.category2,"درجات نارية"));
        categories.add(new CategoriesModel(R.drawable.category3,"بدالات"));
        categories.add(new CategoriesModel(R.drawable.category4,"قطع غيار"));
        categories.add(new CategoriesModel(R.drawable.category1,"درجات هوائية"));
        categories.add(new CategoriesModel(R.drawable.category2,"متنوع"));
        categories.add(new CategoriesModel(R.drawable.category4,"أخرى"));


        return categories;
    }



    static ArrayList<OfferModel> offers = new ArrayList<>();

    public static ArrayList<OfferModel> FillOffers(){
        offers=new ArrayList<>();
        offers.add(new OfferModel(R.drawable.category1,"دراجة نارية","دراجة نارية مميزةمميزFAFSAKSPKGDPSDKGPSPKGKPSKPSGPKة","1800","1100"));
        offers.add(new OfferModel(R.drawable.category2,"دراجة نارية","دراجة نارية مميزة","1200","1050"));
        offers.add(new OfferModel(R.drawable.category3,"دراجة نارية","دراجة نارية مميزمميزFAFSAKSPKGDPSDKGPSPKGKPSKPSGPKةة","1400","1200"));
        offers.add(new OfferModel(R.drawable.category4,"دراجة نارية","دراجة نارية مميزة","1400","1200"));
        offers.add(new OfferModel(R.drawable.category2,"دراجة نارية","دراجة نارية مميزة","1080","900"));
        offers.add(new OfferModel(R.drawable.category1,"دراجة نارية","دراجة نارية مميزة","1090","1200"));
        offers.add(new OfferModel(R.drawable.category3,"دراجة نارية","دراجة نارية مميزة","1100","1000"));
        offers.add(new OfferModel(R.drawable.category4,"دراجة نارية","دراجة نارية مميزة","1040","900"));



        return offers;
    }

    static ArrayList<PopularModel> populars = new ArrayList<>();

    public static ArrayList<PopularModel> FillPopulars(){
        populars=new ArrayList<>();
        populars.add(new PopularModel(R.drawable.category1,"دراجة نارية","دراجة نارية مميزةمميزFAFSAKSPKGDPSDKGPSPKGKPSKPSGPKة","1800"));
        populars.add(new PopularModel(R.drawable.category2,"دراجة نارية","دراجة نارية مميزة","1200"));
        populars.add(new PopularModel(R.drawable.category3,"دراجة نارية","دراجة نارية مميزمميزFAFSAKSPKGDPSDKGPSPKGKPSKPSGPKةة","1400"));
        populars.add(new PopularModel(R.drawable.category4,"دراجة نارية","دراجة نارية مميزة","1400"));
        populars.add(new PopularModel(R.drawable.category2,"دراجة نارية","دراجة نارية مميزة","1080"));
        populars.add(new PopularModel(R.drawable.category1,"دراجة نارية","دراجة نارية مميزة","1090"));
        populars.add(new PopularModel(R.drawable.category3,"دراجة نارية","دراجة نارية مميزة","1100"));
        populars.add(new PopularModel(R.drawable.category4,"دراجة نارية","دراجة نارية مميزة","1040"));



        return populars;
    }






    public static ArrayList<SliderImageHomeModel> getImagesProducts(){
        ArrayList<SliderImageHomeModel> imageHomeModels = new ArrayList<>();
        for(int i=0; i<images.size();i++){
            imageHomeModels.add(images.get(i));

        }
        return imageHomeModels;
    }
}