package provider;

import module.FilmsReport;
import module.PeopleReport;
import module.SwapiReport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiFilmsResource {

    @GET("films/")
    Call<SwapiReport> films ();

    @GET("films/{filmNumber}")
    Call<FilmsReport> films(@Path("filmNumber") int filmNumber);
}
