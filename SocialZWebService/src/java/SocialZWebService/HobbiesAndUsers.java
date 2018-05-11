package SocialZWebService;

import UtilDB.SQLHelper;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(serviceName = "HobbiesAndUsers")
public class HobbiesAndUsers {

    @WebMethod(operationName = "getHobbiesWithPractitioners")
    public String getHobbiesWithPractitioners() {
        String ris = "{";
        ArrayList<String> hobbies = SQLHelper.getHobbies();
        ris += "\"hobbies\":[";
        for (String hobby : hobbies) {
            ris += "{\"nome\":\"" + hobby + "\",\"praticanti\":[";
            ArrayList<String> praticanti = SQLHelper.getAppassionati(hobby);
            for (String praticante : praticanti) {
                ris += "\"" + praticante + "\",";
            }
            ris = ris.substring(0, ris.length() - 1);
            ris += "]},";
        }
        ris = ris.substring(0, ris.length() - 1);
        return ris + "]}";
    }
}
