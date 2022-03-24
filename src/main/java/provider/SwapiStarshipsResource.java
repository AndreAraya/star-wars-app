package provider;

import module.StarshipsReport;
import module.SwapiReport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiStarshipsResource {

    @GET("starships/")
    Call<SwapiReport> starships();

    @GET("starships/{starshipNumber}")
    Call<StarshipsReport> starships(@Path("starshipNumber") int starshipNumber);
}
