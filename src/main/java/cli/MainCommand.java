package cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(
        name = "StarWarsReport",
        subcommands = {
                PeopleCommand.class,
                FilmsCommand.class,
                StarshipsCommand.class,
                PlanetsCommand.class,
                HelpCommand.class },
        description = "Resolves inquiries about Star Wars diverse aspects.")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
