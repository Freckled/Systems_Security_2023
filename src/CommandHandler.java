public class CommandHandler {

    public String response (String input){
        String response = switch (input) {
            case "Knock knock" -> "Who's there?";
            case "Who's there?" -> "Radio";
            case "Radio" -> "Radio who?";
            case "Radio who?" -> "Radio not, here I come! ";
            default -> "done";
        };

        return response;
    }

}
