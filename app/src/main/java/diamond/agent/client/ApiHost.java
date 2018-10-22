package diamond.agent.client;

/**
 * @author by xiongyan on 2018/9/12.
 */
public class ApiHost {
    private static String URL_TEST = "http://mobie.bee.frp.lu8.win/";
    public static final String CLIENT_SUCCESS_CODE ="1";

    public static String getUrlTest() {
        return URL_TEST;
    }

    public static String getUpLoadUrl(){
        return "demo/TestServlet";
    }
    public static String getBaseUrl(){
        return  "http://10.25.32.231:80/";
    }
}
