package hello.vuespring.data;

import hello.vuespring.entity.UniversityDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Slf4j
@Component
public class DataPortalRequest {

    private final String key = "u2yqn8gHcEjG%2BvSdrdvEG12AuoGA85vSYCScBTE%2BtzTwxKEvmK8CSDGevgferBEneSagGwaKJZVw2YJ9ZWrULw%3D%3D";

    public void getApi() throws ParserConfigurationException, IOException, SAXException {
        String url = "http://openapi.academyinfo.go.kr/openapi/service/rest/BasicInformationService/getComparisonUniversitySearchList?";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(url + "serviceKey=" + key + "&svyYr=2022" + "&schlKmNm=서울대학교" + "&numOfRows=999");

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("response");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            Element element = (Element) nNode;

            log.info(element.getNodeName());
            Node item = nNode.getChildNodes().item(1).getFirstChild();
            NodeList childNodes = item.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item1 = childNodes.item(i);
                NodeList childNodes1 = item1.getChildNodes();
                log.info(childNodes1.item(10).getTextContent());
            }
        }
    }

    public UniversityDetail getComparisonUniversitySearchList(String korName, Integer svyYr) throws ParserConfigurationException, IOException, SAXException {
        String url = "http://openapi.academyinfo.go.kr/openapi/service/rest/BasicInformationService/getComparisonUniversitySearchList?";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(url + "serviceKey=" + key + "&svyYr=" + svyYr + "&schlKmNm=" + korName + "&numOfRows=999");

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("response");

        return null;
    }
    public static String getTagValue(String tag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        result = nlList.item(0).getTextContent();

        return result;
    }

    // tag값의 정보를 가져오는 함수
    public static String getTagValue(String tag, String childTag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        for(int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

            //result += nlList.item(i).getFirstChild().getTextContent() + " ";
            result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
        }

        return result;
    }


}
