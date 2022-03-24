package cli;

import com.google.gson.Gson;
import module.PeopleReport;
import module.SwapiReport;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import provider.SwapiPeopleResource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Command(name = "people", description = "Get information about Star Wars Characters.")
public class PeopleCommand implements Runnable {

    private final static String BASE_URL = "https://swapi.dev/api/";

    @Parameters(paramLabel = "<person number>", description = "person to be resolved", defaultValue = "")

    private String personNumber;

    @Override
    public void run() {

        System.out.println("Person Number is:" + personNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiPeopleResource service = retrofit.create(SwapiPeopleResource.class);

        if (personNumber.equals("")){
            try{

                Call<SwapiReport> peopleReportCall = service.people();

                var peopleReport = peopleReportCall.execute().body();



                for (Object p : peopleReport.getResults()){
                    Gson gson = new Gson();
                    System.out.println(gson.toJson(p));
                }





            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }else{
            try{

                Call<PeopleReport> peopleReportCall = service.people(Integer.parseInt(personNumber));

                var personReport = peopleReportCall.execute().body();

                Gson gson = new Gson();
                System.out.println(gson.toJson(personReport));

            } catch (IOException e) {
                throw new RuntimeException("Error when calling remote provider.", e);
            }

        }



        System.exit(0);
    }
}
