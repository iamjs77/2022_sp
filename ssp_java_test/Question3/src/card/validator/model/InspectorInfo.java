package card.validator.model;

public class InspectorInfo {
    public String id;
    public String pwd;

    public InspectorInfo(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public InspectorInfo(String input) {
        String[] array = input.split(" ");

        this.id = array[0];
        this.pwd = array[1];
    }
}
