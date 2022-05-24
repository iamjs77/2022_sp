package card.validator.model;

public class CardInfo {
    // [카드ID(8)][버스ID(7)][승차/하차 코드(1)][최근 승차시각(14)

    public String cardId;
    public String busId;
    public String code;
    public String boardTime;

    public CardInfo(String input) {
        this.cardId = input.substring(0,8);
        this.busId= input.substring(8, 15);
        this.code= input.substring(15, 16);
        this. boardTime= input.substring(16,30);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(cardId).append("#")
                .append(busId.toString()).append("#")
                .append(code).append("#")
                .append(boardTime);
        return sb.toString();
    }
}
