package vn.icar.contacts.api;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import vn.icar.contacts.LoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;


public interface UserService {
    @FormUrlEncoded
    @POST("login?")
    Call<LoginResponse> userLogin(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("register?")
    Call<LoginResponse> register(@Field("_id") String _id, @Field("password") String password, @Field("username") String username);


}
