package com.ahmed.store_app_pro_1;

import com.ahmed.store_app_pro_1.ui.models.CartModel;
import com.ahmed.store_app_pro_1.ui.models.CategoriesModel;
import com.ahmed.store_app_pro_1.ui.models.ColorProductModel;
import com.ahmed.store_app_pro_1.ui.models.LastSearchModel;
import com.ahmed.store_app_pro_1.ui.models.NotificationModel;
import com.ahmed.store_app_pro_1.ui.models.OfferModel;
import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.PopularModel;
import com.ahmed.store_app_pro_1.ui.models.ProductModel;
import com.ahmed.store_app_pro_1.ui.models.SliderImageHomeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {

    static ArrayList<Integer> images = new ArrayList<>();
    public static ArrayList<Integer> FillImages(){
        images=new ArrayList<>();
        images.add(R.drawable.image_slider1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image_slider1);
        images.add(R.drawable.image3);
        images.add(R.drawable.image1);
        images.add(R.drawable.image_slider1);



        return images;
    }
//    String [] tabs = {"الكل","بدالات","عجلات","متنوع","قطع غيار","دراجات هوائية","دراجات نارية",};

    static ArrayList<String> tabs = new ArrayList<>();
    public static ArrayList<String> FillTabs(){
        tabs=new ArrayList<>();
        tabs.add("الكل");
        tabs.add("بدالات");
        tabs.add("عجلات");
        tabs.add("متنوع");
        tabs.add("قطع غيار");
        tabs.add("دراجات هوائية");
        tabs.add("دراجات نارية");




        return tabs;
    }


    static ArrayList<Integer> imagesForProduct = new ArrayList<>();
    public static ArrayList<Integer> FillImagesForProduct(){
        imagesForProduct=new ArrayList<>();
        imagesForProduct.add(R.drawable.image1);
        imagesForProduct.add(R.drawable.image2);
        imagesForProduct.add(R.drawable.image3);
        imagesForProduct.add(R.drawable.image4);
        imagesForProduct.add(R.drawable.image3);
        imagesForProduct.add(R.drawable.image1);

        return imagesForProduct;
    }




    static ArrayList<Integer> imagesForProduct3 = new ArrayList<>();
    public static ArrayList<Integer> FillImagesForProduct3(){
        imagesForProduct3=new ArrayList<>();
        imagesForProduct3.add(R.drawable.image1);
        imagesForProduct3.add(R.drawable.image2);

        return imagesForProduct3;
    }



    static ArrayList<Integer> imagesForProduct2 = new ArrayList<>();
    public static ArrayList<Integer> FillImagesForProduct2(){
        imagesForProduct2=new ArrayList<>();
        imagesForProduct2.add(R.drawable.image1);
        imagesForProduct2.add(R.drawable.image4);
        imagesForProduct2.add(R.drawable.image2);
        imagesForProduct2.add(R.drawable.image4);
        imagesForProduct2.add(R.drawable.image3);
        imagesForProduct2.add(R.drawable.image1);

        return imagesForProduct2;
    }



    static ArrayList<LastSearchModel> lastSearchModels = new ArrayList<>();
    public static ArrayList<LastSearchModel> FillLastSearch(){
        lastSearchModels=new ArrayList<>();
        lastSearchModels.add(new LastSearchModel("عجلات"));
        lastSearchModels.add(new LastSearchModel("بدالات"));
        lastSearchModels.add(new LastSearchModel("قطع غيار"));
        lastSearchModels.add(new LastSearchModel("دراجة"));

        return lastSearchModels;
    }








    static ArrayList<CategoriesModel> categories = new ArrayList<>();

    public static ArrayList<CategoriesModel> FillCategories(){
        categories=new ArrayList<>();
        categories.add(new CategoriesModel(R.drawable.category1,"عجلات"));
        categories.add(new CategoriesModel(R.drawable.category2,"دراجات نارية"));
        categories.add(new CategoriesModel(R.drawable.category3,"بدالات"));
        categories.add(new CategoriesModel(R.drawable.category4,"قطع غيار"));
        categories.add(new CategoriesModel(R.drawable.category1,"دراجات هوائية"));
        categories.add(new CategoriesModel(R.drawable.category2,"متنوع"));
        categories.add(new CategoriesModel(R.drawable.category4,"أخرى"));


        return categories;
    }

    private static  ArrayList<Integer> colorArray1 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray() {
        colorArray1=new ArrayList<>();

        colorArray1.add(R.color.primary_color);
        colorArray1.add(R.color.secondary_color);
        colorArray1.add(R.color.secondary_color);
        colorArray1.add(R.color.dot_un_select_color);
        colorArray1.add(R.color.black);

        return colorArray1;
    }

    private static ArrayList<Integer> colorArray2 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray2() {
        colorArray2=new ArrayList<>();
        colorArray2.add(R.color.purple_700);
        colorArray2.add(R.color.secondary_color);
        colorArray2.add(R.color.border_button_sign_by_google_on_boarding_second);
        colorArray2.add(R.color.dot_un_select_color);
        colorArray2.add(R.color.black);

        return colorArray2;
    }


    private static  ArrayList<Integer> colorArray3 = new ArrayList<Integer>();
    public static ArrayList<Integer> getColorArray3() {
        colorArray3=new ArrayList<>();

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





    static ArrayList<CartModel> cartModels = new ArrayList<>();

    public static ArrayList<CartModel> FillCart(){
        cartModels=new ArrayList<>();
        cartModels.add(new CartModel(R.drawable.image3,"دراجة نارية فاخرة ","99.00",1));
        cartModels.add(new CartModel(R.drawable.image4,"بدالة مميزة","80.00",2));
        cartModels.add(new CartModel(R.drawable.image2,"عجلات مميزة","199.00",3));
        cartModels.add(new CartModel(R.drawable.image3,"دراجة نارية","79.00",1));



        return cartModels;
    }





    static ArrayList<OrderModel> orderModels = new ArrayList<>();

    public static ArrayList<OrderModel> FillOrder(){
        orderModels=new ArrayList<>();
        orderModels.add(new OrderModel(123,"مكتمل",99.00,"غزة",5,94));
        orderModels.add(new OrderModel(124,"مستلم",79.00,"غزة",7,72));
        orderModels.add(new OrderModel(135,"مكتمل",59.00,"غزة",4,55));
        orderModels.add(new OrderModel(167,"مستلم",299.00,"غزة",30,269));



        return orderModels;
    }










    static ArrayList<ProductModel> allProducts = new ArrayList<>();

    public static ArrayList<ProductModel> FillProducts(){
        allProducts=new ArrayList<>();

        allProducts.add(new ProductModel("بدالات",R.drawable.image2,"بدالات فاخرة", "دراجة نارية فاخرة","18","15",getColorArray(),"90.99",FillImagesForProduct2(),true));
        allProducts.add(new ProductModel("عجلات",R.drawable.image1,"عجلات فاخرة", "دراجة نارية فاخرة","12","09",getColorArray2(),"99.99",FillImagesForProduct3(),false));
        allProducts.add(new ProductModel("متنوع",R.drawable.image3,"متنوع فاخرة", "دراجة نارية فاخرة","15","13",getColorArray3(),"104.99",FillImages(),true));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image4,"قطع غيار فاخرة", "دراجة نارية فاخرة","18","12",getColorArray(),"79.99",FillImagesForProduct2(),false));
        allProducts.add(new ProductModel("دراجات هوائية",R.drawable.image2,"دراجة هوائية فاخرة", "دراجة نارية فاخرة","20","16",getColorArray2(),"89.99",FillImages(),false));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image3,"دراجة نارية فاخرة", "دراجة نارية فاخرة","38","35",getColorArray(),"119.99",FillImagesForProduct(),true));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image1,"قطع غيار فاخرة", "دراجة نارية فاخرة","28","18",getColorArray3(),"209.99",FillImagesForProduct2(),false));
        allProducts.add(new ProductModel("متنوع",R.drawable.image4,"متنوع فاخرة", "دراجة نارية فاخرة","68","58",getColorArray(),"60.99",FillImagesForProduct(),true));
        allProducts.add(new ProductModel("قطع غيار",R.drawable.image2,"قطع غيار فاخرة", "دراجة نارية فاخرة","58","48",getColorArray2(),"50.99",FillImages(),false));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image3,"دراجة نارية فاخرة", "دراجة نارية فاخرة","14","08",getColorArray3(),"110.99",FillImagesForProduct3(),true));
        allProducts.add(new ProductModel("بدالات",R.drawable.image2,"بدالات فاخرة", "دراجة نارية فاخرة","24","18",getColorArray(),"130.99",FillImages(),false));
        allProducts.add(new ProductModel("متنوع",R.drawable.image1,"متنوع فاخرة", "دراجة نارية فاخرة","54","48",getColorArray2(),"129.99",FillImagesForProduct(),true));
        allProducts.add(new ProductModel("دراجات نارية",R.drawable.image4,"دراجة نارية فاخرة", "دراجة نارية فاخرة","92","80",getColorArray(),"139.99",FillImages(),true));
        allProducts.add(new ProductModel("عجلات",R.drawable.image3,"عجلات فاخرة", "دراجة نارية فاخرة","17","27",getColorArray3(),"149.99",FillImagesForProduct2(),false));


        return allProducts;
    }

    public static ArrayList<ProductModel> getProducts(String category){
        ArrayList<ProductModel> products = new ArrayList<>();
        for(int i=0;i<allProducts.size();i++){
            if(allProducts.get(i).getCategory().equals(category)){
                products.add(allProducts.get(i));
            }
//           else if(allProducts.get(i).getCategory().equals("الكل")){
//                products.add(allProducts.get(i));
//            }

        }
        return products;
    }


    public static ArrayList<LastSearchModel> getLastSearchModels(String text){
        ArrayList<LastSearchModel> searches = new ArrayList<>();
        for(int i=0;i<lastSearchModels.size();i++){
            if(lastSearchModels.get(i).getText().toLowerCase().contains(text.toLowerCase())){
                searches.add(lastSearchModels.get(i));
            }
//           else if(allProducts.get(i).getCategory().equals("الكل")){
//                products.add(allProducts.get(i));
//            }

        }
        return searches;
    }



    public static ArrayList<ProductModel> getFavoritesModel(){
        ArrayList<ProductModel> favorites = new ArrayList<>();
        for(int i=0;i<allProducts.size();i++) {
            if (allProducts.get(i).getFavorite()) {
                favorites.add(allProducts.get(i));
            }
        }

        return favorites;
    }




    static ArrayList<NotificationModel> notificationModels ;
    public static ArrayList<NotificationModel> FillNotification(){
        notificationModels=new ArrayList<>();
        notificationModels.add(new NotificationModel("الان","نص عربي افتراضي نص عربي افتراضي نص عربي افتراضي"));
        notificationModels.add(new NotificationModel("أمس","نص عربي افتراضي نص عربي افتراضي نص عربي افتراضي"));
        notificationModels.add(new NotificationModel("08:35","نص عربي افتراضي نص عربي افتراضي نص عربي افتراضي"));
        notificationModels.add(new NotificationModel("01:15"," نفس " +
                "المساحة، لقد تم توليد هذا النص من مولد النص العربى،\n" +
                "حيث يمكنك أن تولد مثل هذا النص أو العديد من\n" +
                "النصوص الأخرى إضافة إلى زيادة عدد الحروف التى يولدها\n" +
                "التطبيق.هذا النص هو مثال لنص يمكن أن يستبدل في نفس\n" +
                "المساحة، لقد تم توليد هذا النص من مولد النص العربى،\n" +
                "حيث يمكنك أن تولد مثل هذا النص أو العديد من\n"));



        return notificationModels;
    }














    public static ArrayList<Integer> getImagesProducts(){
        ArrayList<Integer> imageHomeModels = new ArrayList<>();
        for(int i=0; i<images.size();i++){
            imageHomeModels.add(images.get(i));

        }
        return imageHomeModels;
    }
}