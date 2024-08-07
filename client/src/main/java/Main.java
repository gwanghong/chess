import data.DataStorage;
import ui.Repl;

public class Main {
    public static void main(String[] args) {

        Repl repl = new Repl();

        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        DataStorage.getInstance().setRun(serverUrl);
        repl.run();
    }
}