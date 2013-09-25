package jackal.objects.year2013;

/**
 * User: Jackal
 * Date: 09.11.2008
 * Time: 21:29:50
 * $Rev: 193 $
 * $Author: jackal $
 * $Date: 2013-04-18 09:27:12 +0400 (Чт, 18 апр 2013) $
 */
public class JuridicalPerson extends Declarant {
    private static final long serialVersionUID = 4121572421580815536L;

    private String title; //Реквизит №9.2.4
    private String KPP; //Реквизит №9.4
    private Boolean largestTaxpayer = false; //Реквизит №9.1.1

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        fireChange("title",this.title, title);
        this.title = title;
    }

    public String getKPP() {
        return KPP;
    }

    public void setKPP(String KPP) {
        fireChange("KPP",this.KPP, KPP);
        this.KPP = KPP;
    }

    public Boolean isLargestTaxpayer() {
        return largestTaxpayer;
    }

    public void setLargestTaxpayer(Boolean largestTaxpayer) {
        fireChange("largestTaxpayer",this.largestTaxpayer, largestTaxpayer);
        this.largestTaxpayer = largestTaxpayer;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String getFullContentAsString() {
        StringBuilder sb = new StringBuilder(title);
        sb.append(" ИНН ").append(INN).append(" КПП ").append(KPP).append(" КН ").append(isLargestTaxpayer());
        return sb.toString();
    }
}
