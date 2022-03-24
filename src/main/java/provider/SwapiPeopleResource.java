package provider;

import module.SwapiReport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import module.PeopleReport;

public interface SwapiPeopleResource {

    @GET("people/")
    Call<SwapiReport> people();

    @GET("people/{personNumber}")
    Call<PeopleReport> people(@Path("personNumber") int personNumber);
}
