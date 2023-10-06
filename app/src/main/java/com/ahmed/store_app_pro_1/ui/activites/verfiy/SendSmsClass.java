//package com.ahmed.store_app_pro_1.ui.activites.verfiy;
//
//import android.content.SharedPreferences;
//
//import com.ahmed.store_app_pro_1.Constans;
//
//import java.io.IOException;
//
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class SendSmsClass {
//
//
//
//        private static final String BASE_URL = "https://6gj498.api.infobip.com";
//        private static final String API_KEY = "App fb3f74e0202eb9caf19b2f740203b94f-ac9fe0f0-7b50-462f-831d-b0a92d20b4f2";
//        private static final String MEDIA_TYPE = "application/json";
//
//        private static final String SENDER = "InfoSMS";
//        private static final String RECIPIENT = "970592722192";
//        private static final String MESSAGE_TEXT = "This is a sample message";
//
//
//        public static void main(String[] args) throws IOException {
//            SharedPreferences prefs = VerfiyActivity.ACTIVITY_SERVICE.getSharedPreferences(Constans.SHARED_PREF, MODE_PRIVATE);
//            String token = prefs.getString(Constans.TOKEN, "");
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//
//            String bodyJson = String.format("{\"messages\":[{\"from\":\"%s\",\"destinations\":[{\"to\":\"%s\"}],\"text\":\"%s\"}]}",
//                    SENDER,
//                    RECIPIENT,
//                    MESSAGE_TEXT
//            );
//
//            MediaType mediaType = MediaType.parse(MEDIA_TYPE);
//            RequestBody body = RequestBody.create(bodyJson, mediaType);
//
//            Request request = prepareHttpRequest(body);
//            Response response = client.newCall(request).execute();
//
//            System.out.println("HTTP status code: " + response.code());
//            System.out.println("Response body: " + response.body().string());
//        }
//
//        private static Request prepareHttpRequest(RequestBody body) {
//            return new Request.Builder()
//                    .url(String.format("%s/sms/2/text/advanced", BASE_URL))
//                    .method("POST", body)
//                    .addHeader("Authorization", API_KEY)
//                    .addHeader("Content-Type", MEDIA_TYPE)
//                    .addHeader("Accept", MEDIA_TYPE)
//                    .build();
//        }
//
//
//}
