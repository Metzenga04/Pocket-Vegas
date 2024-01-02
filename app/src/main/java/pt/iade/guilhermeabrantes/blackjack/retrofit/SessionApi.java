package pt.iade.guilhermeabrantes.blackjack.retrofit;

import java.util.List;

import pt.iade.guilhermeabrantes.blackjack.models.Session;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionApi {
    @GET("/session/get-all")
    Call<List<Session>> getAllSessions();

    @POST("/session/save")
    Call<Session> save(@Body Session session);
}
