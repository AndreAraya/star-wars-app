package cli;

import com.google.gson.Gson;
import module.PlanetsReport;
import module.SwapiReport;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import provider.SwapiPlanetsResource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Command(name = "planets", description = "Get information about Star Wars Planets.")
public class PlanetsCommand implements Runnable {

    private final static String BASE_URL = "https://swapi.dev/api/";

    @Parameters(paramLabel = "<planet number>", description = "planet to be resolved", defaultValue =  "")

    private String planetNumber;

    @Override
    public void run() {

        System.out.println("Planet Number is:" + planetNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiPlanetsResource service = retrofit.create(SwapiPlanetsResource.class);


        if (planetNumber.equals("")){

            try{
                Call<SwapiReport> planetsReportCall = service.planets();

                SwapiReport planetReport = planetsReportCall.execute().body();

                for (Object p : planetReport.getResults()){
                    Gson gson = new Gson();
                    System.out.println(gson.toJson(p));
                }



            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }else{

            try{
                Call<PlanetsReport> planetsReportCall = service.planets(Integer.parseInt(planetNumber));

                var planetReport = planetsReportCall.execute().body();

                Gson gson = new Gson();
                System.out.println(gson.toJson(planetReport));

            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }



        System.exit(0);


        //despues del execute body



    }
}
