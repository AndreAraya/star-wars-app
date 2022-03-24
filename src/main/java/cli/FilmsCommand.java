package cli;

import com.google.gson.Gson;
import module.FilmsReport;
import module.SwapiReport;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import provider.SwapiFilmsResource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Command(name = "films", description = "Get information about Star Wars Films.")
public class FilmsCommand implements Runnable {

    private final static String BASE_URL = "https://swapi.dev/api/";

    @Parameters(paramLabel = "<film number>", description = "film to be resolved", defaultValue = "")

    private String filmNumber;

    @Override
    public void run() {

        System.out.println("Film Number is:" + filmNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SwapiFilmsResource service = retrofit.create(SwapiFilmsResource.class);

        if (filmNumber.equals("")){
            try{
                Call<SwapiReport> filmsReportCall = service.films();

                SwapiReport filmReport = filmsReportCall.execute().body();

                for (Object p : filmReport.getResults()){
                    Gson gson = new Gson();
                    System.out.println(gson.toJson(p));
                }


            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }else{
            try{
                Call<FilmsReport> filmsReportCall = service.films(Integer.parseInt(filmNumber));

                var filmReport = filmsReportCall.execute().body();

                Gson gson = new Gson();
                System.out.println(gson.toJson(filmReport));

            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }
        }



        System.exit(0);


        //despues del execute body



    }
}
