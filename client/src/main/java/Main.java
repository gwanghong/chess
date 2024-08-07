import data.DataStorage;
import ui.Repl;

public class Main {
    public static void main(String[] args) {

        Repl repl = new Repl();

        int port = 8080;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        String url = "http://localhost:" + port;


        DataStorage.getInstance().setRun(url);
        repl.run();
    }
}