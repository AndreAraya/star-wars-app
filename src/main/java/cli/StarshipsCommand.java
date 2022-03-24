package cli;

import com.google.gson.Gson;
import module.StarshipsReport;
import module.SwapiReport;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import provider.SwapiStarshipsResource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Command(name = "starships", description = "Get information about Star Wars Starships.")
public class StarshipsCommand implements Runnable {

    private final static String BASE_URL = "https://swapi.dev/api/";

    @Parameters(paramLabel = "<starship number>", description = "starship to be resolved", defaultValue = "")

    private String starshipNumber;

    @Override
    public void run() {

        System.out.println("Starship Number is:" + starshipNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiStarshipsResource service = retrofit.create(SwapiStarshipsResource.class);


        if (starshipNumber.equals("")){

            try{

                Call<SwapiReport> starshipsReportCall = service.starships();

                var starshipReport = starshipsReportCall.execute().body();

                for (Object p : starshipReport.getResults()){
                    Gson gson = new Gson();
                    System.out.println(gson.toJson(p));
                }




            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }


        }else{

            try{

                Call<StarshipsReport> starshipsReportCall = service.starships(Integer.parseInt(starshipNumber));

                var starshipReport = starshipsReportCall.execute().body();

                Gson gson = new Gson();
                System.out.println(gson.toJson(starshipReport));

            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }



        System.exit(0);

    }
}
