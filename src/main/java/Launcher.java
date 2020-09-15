import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.util.UUID;

public class Launcher {
    public static void main(String[] args) {
        //Permission Mode
        Variant QBPermissionMode = new Variant(1);
        //Mode for Multi user/Single User or both, this setting is both
        Variant QBaccessMode = new Variant(2);
        //Leave Empty to use the currently opened QB File
        String fileLocation = "";  //not needed unless opening QB file which is currently not opened
        String XMLRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<?qbxml version=\"13.0\"?>\n" +
                "<QBXML>\n" +
                "  <QBXMLMsgsRq onError=\"stopOnError\">\n" +
                "    <CustomerQueryRq requestID=\"" + UUID.randomUUID() + "\">\n" +
                " \n" +
                "      <FullName>Andres, Cristina</FullName>\n" +
                " \n" +
                "      <OwnerID>0</OwnerID>\n" +
                "    </CustomerQueryRq>  \n" +
                "  </QBXMLMsgsRq>\n" +
                "</QBXML>";


        String appID = "";//not needed unless you want to set AppID
        String applicationName = "QB Sync Test";
        Dispatch MySessionManager = new Dispatch("QBXMLRP2.RequestProcessor");
        Dispatch.call(MySessionManager, "OpenConnection2", appID, applicationName, QBPermissionMode);
        Variant ticket = Dispatch.call(MySessionManager, "BeginSession",fileLocation, QBaccessMode);
        Variant apiResponse = Dispatch.call(MySessionManager, "ProcessRequest", ticket, XMLRequest);
        System.out.println(apiResponse.toString());
        Dispatch.call(MySessionManager, "EndSession", ticket);
        Dispatch.call(MySessionManager, "CloseConnection");

    }

}
