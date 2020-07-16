package lab2;

public class ActionsParser {
    public Action[] parse(String[] args) throws IllegalArgumentException {
        Action[] actions = new Action[args.length];
        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "d+":
                    actions[i] = Action.DAY_LATER;
                    i++;
                    break;
                case "d-":
                    actions[i] = Action.DAY_EARLIER;
                    i++;
                    break;
                case "t+":
                    actions[i] = Action.TIME_LATER;
                    i++;
                    break;
                case "t-":
                    actions[i] = Action.TIME_EARLIER;
                    i++;
                    break;
                default:
                    i++;
                    throw new IllegalArgumentException("Translation " + arg + " is incorrect");
            }
        }
        return actions;

    }
}
