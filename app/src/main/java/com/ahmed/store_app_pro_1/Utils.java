package com.ahmed.store_app_pro_1;

import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
import com.ahmed.store_app_pro_1.ui.models.ColorProductModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;
import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;

import java.util.ArrayList;
import java.util.List;

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



    static ArrayList<SliderImageHomeModel> imagesForProduct = new ArrayList<>();
    public static ArrayList<SliderImageHomeModel> FillImagesForProduct(){
        imagesForProduct=new ArrayList<>();
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image1));
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image2));
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image3));
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image4));
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image3));
        imagesForProduct.add(new SliderImageHomeModel(R.drawable.image1));


        return imagesForProduct;
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

    private static final ArrayList<Integer> colorArray1 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray() {
        colorArray1.add(R.color.primary_color);
        colorArray1.add(R.color.secondary_color);
        colorArray1.add(R.color.secondary_color);
        colorArray1.add(R.color.dot_un_select_color);
        colorArray1.add(R.color.black);

        return colorArray1;
    }

    private static final ArrayList<Integer> colorArray2 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray2() {
        colorArray2.add(R.color.purple_700);
        colorArray2.add(R.color.secondary_color);
        colorArray2.add(R.color.border_button_sign_by_google_on_boarding_second);
        colorArray2.add(R.color.dot_un_select_color);
        colorArray2.add(R.color.black);

        return colorArray2;
    }


    private static final ArrayList<Integer> colorArray3 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray3() {
        colorArray3.add(R.color.teal_200);
        colorArray3.add(R.color.secondary_color);
        colorArray3.add(R.color.primary_color);
        colorArray3.add(R.color.dot_un_select_color);
        colorArray3.add(R.color.teal_700);

        return colorArray3;
    }


    static ArrayList<ColorProductModel> colors = new ArrayList<>();

//
//    public static ArrayList<ColorProductModel> FillColors(){
//        colors=new ArrayList<>();
//
//        colors.add(new ColorProductModel(getColorArray()));
//
//        return colors;
//    }
//



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






    static ArrayList<ProductModel> allProducts = new ArrayList<>();

    public static ArrayList<ProductModel> FillProducts(){
        allProducts=new ArrayList<>();

        allProducts.add(new ProductModel("بدالات",R.drawable.image2,"بدالات فاخرة", "دراجة نارية فاخرة","18","15",getColorArray(),"90.99",FillImages(),false));
        allProducts.add(new ProductModel("عجلات",R.drawable.image2,"عجلات فاخرة", "دراجة نارية فاخرة","12","09",getColorArray2(),"99.99",FillImages(),true));
        allProducts.add(new ProductModel("متنوع",R.drawable.image2,"متنوع فاخرة", "دراجة نارية فاخرة","15","13",getColorArray3(),"104.99",FillImages(),false));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image2,"قطع غيار فاخرة", "دراجة نارية فاخرة","18","12",getColorArray(),"79.99",FillImages(),true));
        allProducts.add(new ProductModel("دراجات هوائية",R.drawable.image2,"دراجة هوائية فاخرة", "دراجة نارية فاخرة","20","16",getColorArray2(),"89.99",FillImages(),false));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image2,"دراجة نارية فاخرة", "دراجة نارية فاخرة","38","35",getColorArray(),"119.99",FillImages(),false));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image2,"قطع غيار فاخرة", "دراجة نارية فاخرة","28","18",getColorArray3(),"209.99",FillImages(),true));
        allProducts.add(new ProductModel("متنوع",R.drawable.image2,"متنوع فاخرة", "دراجة نارية فاخرة","68","58",getColorArray(),"60.99",FillImages(),false));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image2,"قطع غيار فاخرة", "دراجة نارية فاخرة","58","48",getColorArray2(),"50.99",FillImages(),false));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image2,"دراجة نارية فاخرة", "دراجة نارية فاخرة","14","08",getColorArray3(),"110.99",FillImages(),true));
        allProducts.add(new ProductModel("بدالات",R.drawable.image2,"بدالات فاخرة", "دراجة نارية فاخرة","24","18",getColorArray(),"130.99",FillImages(),true));
        allProducts.add(new ProductModel("متنوع",R.drawable.image2,"متنوع فاخرة", "دراجة نارية فاخرة","54","48",getColorArray2(),"129.99",FillImages(),false));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image2,"دراجة نارية فاخرة", "دراجة نارية فاخرة","92","80",getColorArray(),"139.99",FillImages(),true));
        allProducts.add(new ProductModel("عجلات",R.drawable.image2,"عجلات فاخرة", "دراجة نارية فاخرة","17","27",getColorArray3(),"149.99",FillImages(),false));


        return allProducts;
    }

    public static ArrayList<ProductModel> getProducts(String category){
        ArrayList<ProductModel> products = new ArrayList<>();
        for(int i=0;i<allProducts.size();i++){
            if(allProducts.get(i).getCategory().equals(category)){
                products.add(allProducts.get(i));
            }
           else if(allProducts.get(i).getCategory().equals("الكل")){
                products.add(allProducts.get(i));
            }

        }
        return products;
    }



    public static ArrayList<ProductModel> getAllProducts(){
        ArrayList<ProductModel> allproductsData = new ArrayList<>();
        for(int i=0;i<allProducts.size();i++){
            allproductsData.add(allProducts.get(i));

        }
        return allproductsData;
    }

// this.title = title;
//        this.description = description;
//        this.colors = colors;
//        this.price = price;
//        this.sliderImageHomeModels = sliderImageHomeModels;
//        this.isFavorite = isFavorite;
//
//


    public static ArrayList<SliderImageHomeModel> getImagesProducts(){
        ArrayList<SliderImageHomeModel> imageHomeModels = new ArrayList<>();
        for(int i=0; i<images.size();i++){
            imageHomeModels.add(images.get(i));

        }
        return imageHomeModels;
    }
}