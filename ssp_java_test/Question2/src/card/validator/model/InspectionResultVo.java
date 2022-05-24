package card.validator.model;

import card.validator.utils.CardUtility;

public class InspectionResultVo {
    public String inspecotrId;
    public String busId;
    public CardInfo cardInfo;
    public String resultCode;
    public String inpectionTime;


    public InspectionResultVo(String currentBusId, String startInspectionTime, CardInfo cardInfo) {
        this.busId = currentBusId;
        this.cardInfo = cardInfo;
        this.inpectionTime = startInspectionTime;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(inspecotrId).append("#")
                .append(cardInfo.toString()).append("#")
                .append(resultCode).append("#")
                .append(inpectionTime);

        return sb.toString();

    }
}
