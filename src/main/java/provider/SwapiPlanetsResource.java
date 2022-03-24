package provider;

import module.PlanetsReport;
import module.SwapiReport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiPlanetsResource {

    @GET("planets/")
    Call<SwapiReport> planets();

    @GET("planets/{planetNumber}")
    Call<PlanetsReport> planets(@Path("planetNumber") int planetNumber);
}
