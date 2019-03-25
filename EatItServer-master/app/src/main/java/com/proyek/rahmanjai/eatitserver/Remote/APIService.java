package com.proyek.rahmanjai.eatitserver.Remote;

import com.proyek.rahmanjai.eatitserver.Model.MyResponse;
import com.proyek.rahmanjai.eatitserver.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA5OEwxF8:APA91bEt_52PQ1UhUoD9wVv725LGrI6Gzb8ySym9ENPc7wF7ZFheO15eIPTEr98IzDfD-ovDBO3-qKXOt7wkVoRR3HUHdzADB7XxXsQzL7Bgz2s4DhwSkvrsf0SQm7_6UX-TG6dfiYqI"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
