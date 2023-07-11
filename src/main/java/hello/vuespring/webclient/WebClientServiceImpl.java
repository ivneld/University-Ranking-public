package hello.vuespring.webclient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hello.vuespring.advice.assertThat.DefaultAssert;
import hello.vuespring.entity.UniversityDetail;
import hello.vuespring.entity.dto.UniversityDetailDto;
import hello.vuespring.webclient.response.ComparisonUniversitySearchListResponse;
import hello.vuespring.webclient.response.NoticeFreshmanDrafteesRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.time.Duration;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class WebClientServiceImpl {

    private final static String serviceKey = "u2yqn8gHcEjG+vSdrdvEG12AuoGA85vSYCScBTE+tzTwxKEvmK8CSDGevgferBEneSagGwaKJZVw2YJ9ZWrULw==";
    private static String baseUrl1 = "http://openapi.academyinfo.go.kr/openapi/service/rest/BasicInformationService";
    private static String baseUrl2 = "http://openapi.academyinfo.go.kr/openapi/service/rest/StudentService";
    private static String baseUrl3 = "http://openapi.academyinfo.go.kr/openapi/service/rest/EducationResearchService";
    private static String baseUrl4 = "http://openapi.academyinfo.go.kr/openapi/service/rest/EducationConditionService";
    private static String baseUrl5 = "http://openapi.academyinfo.go.kr/openapi/service/rest/FinancesService";

    public UniversityDetail getUniversityDetail(String korName, Integer svyYr) {
        UniversityDetailDto dto = getUniversityDetailDto(korName, svyYr);

        return UniversityDetail.builder()
                .schlId(dto.getSchlId())
                .svyYr(dto.getSvyYr())
                .estbDivNm(dto.getEstbDivNm())
                .schlKndNm(dto.getSchlKndNm())
                .fcbsr(dto.getFcbsr())
                .freshRate(dto.getFreshRate())
                .recruits(dto.getRecruits())
                .selection(dto.getSelection())
                .freshAvg(dto.getFreshAvg())
                .freshImg(dto.getFreshImg())
                .enroll(dto.getEnroll())
                .students(dto.getStudents())
                .enrollRate(dto.getEnrollRate())
                .enrollAvg(dto.getEnrollAvg())
                .enrollImg(dto.getEnrollImg())
                .compRate(dto.getCompRate())
                .graduate(dto.getGraduate())
                .employed(dto.getEmployed())
                .candidate(dto.getCandidate())
                .employedRate(dto.getEmployedRate())
                .employedAvg(dto.getEmployedAvg())
                .employedImg(dto.getEmployedImg())
                .stprRate(dto.getStprRate())
                .lcRate(dto.getLcRate())
                .insideResearchGrant(dto.getInsideResearchGrant())
                .outsideResearchGrant(dto.getOutsideResearchGrant())
                .professors(dto.getProfessors())
                .researchRate1(dto.getResearchRate1())
                .researchRate2(dto.getResearchRate2())
                .researchAvg(dto.getResearchAvg())
                .researchImg(dto.getResearchImg())
                .dormitoryRate(dto.getDormitoryRate())
                .libraryBudget(dto.getLibraryBudget())
                .purchasePrice(dto.getPurchasePrice())
                .tuition(dto.getTuition())
                .scholarship(dto.getScholarship())
                .expenseReduction(dto.getExpenseReduction())
                .loan(dto.getLoan())
                .loanUseRate(dto.getLoanUseRate())
                .build();
    }

    private UniversityDetailDto getUniversityDetailDto(String korName, Integer svyYr) {
        UniversityDetailDto dto = new UniversityDetailDto();
        getComparisonUniversitySearchList(svyYr, korName, dto);
        getComparisonFreshmanChanceBalanceSelectionRatio(dto);
        getNoticeFreshmanDrafteesRateResponse(dto);
        getNoticeEnrolledStudentDrafteesRate(dto);
        getComparisonInsideFixedNumberFreshmanCompetitionRate(dto);
        getNoticeGraduateEmploymentRate(dto);
        getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent(dto);
        getComparisonLectureChargeRatio(dto);
        getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant(dto);
        getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant(dto);
        getNoticeFullTimeFacultyResearchCrntSt(dto);
        getComparisonDormitoryAcceptanceCrntSt(dto);
        getComparisonLibraryBudgetCrntSt(dto);
        getComparisonStudentForPersonDataPurchasePrice(dto);
        getComparisonTuitionCrntSt(dto);
        getComparisonScholarshipBenefitCrntSt(dto);
        getComparisonEducationalExpensesReductionCrntSt(dto);
        getComparisonEducationExpensesLoanCrntSt(dto);
        getComparisonEducationExpensesLoanUseStudentRatioTuition(dto);
        return dto;
    }

    private void getComparisonUniversitySearchList(Integer svyYr, String schlKrnNm, UniversityDetailDto dto) {
//        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
//        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

//         webClient 기본 설정
        WebClient webClient = getWebClient(baseUrl1);

        // api 요청
        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonUniversitySearchList")
                        .queryParam("serviceKey","{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlKrnNm","{schlKrnNm}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey,svyYr, schlKrnNm, 999);

        String xmlString = getResponse(webClient, uri);

        // 결과 확인
        try {
            Optional<ComparisonUniversitySearchListResponse> findResponse = xmlToUniversitySearchList(xmlString, schlKrnNm);
//            DefaultAssert.isOptionalPresent(findResponse);

            if (findResponse.isEmpty()) {
                log.info("{} -> {} 실패", schlKrnNm, "getComparisonUniversitySearchList");
                dto.setSchlKrnNm(schlKrnNm);
                dto.setSvyYr(String.valueOf(svyYr));
                dto.setSchlId(schlKrnNm);
            } else {
                ComparisonUniversitySearchListResponse response = findResponse.get();

                dto.setSchlKrnNm(response.getSchlKrnNm());
                dto.setSchlId(response.getSchlId());
                dto.setSvyYr(response.getSvyYr());
                dto.setEstbDivNm(response.getEstbDivNm());
                dto.setSchlKndNm(response.getSchlKndNm());

                log.info(response.getSchlKrnNm());
                log.info(response.getSchlId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getComparisonFreshmanChanceBalanceSelectionRatio(UniversityDetailDto dto) {
//        String schlId = dto.getSchlId();
//        String svyYr = dto.getSvyYr();

        WebClient webClient = getWebClient(baseUrl2);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonFreshmanChanceBalanceSelectionRatio")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> findFcbsr = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(findFcbsr);
            if (findFcbsr.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonUniversitySearchList");
            } else {
                String fcbsr = findFcbsr.get();

                dto.setFcbsr(fcbsr);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info(response);
    }

    private void getNoticeFreshmanDrafteesRateResponse(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl2);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getNoticeFreshmanDrafteesRate")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String xmlString = getResponse(webClient, uri);

        try {
            Optional<Object> findObject = extractMultiParam(xmlString, NoticeFreshmanDrafteesRateResponse.class);
//            DefaultAssert.isOptionalPresent(findObject);
            if (findObject.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getNoticeFreshmanDrafteesRateResponse");
            } else {
                NoticeFreshmanDrafteesRateResponse response = (NoticeFreshmanDrafteesRateResponse) findObject.get();
                log.info(response.getIndctVal1());
                dto.setFreshRate(response.getIndctVal4());
                dto.setRecruits(response.getIndctVal1());
                dto.setSelection(response.getIndctVal2());
                dto.setFreshAvg(response.getIndctAvg());
                dto.setFreshImg(response.getIndctImg());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getNoticeEnrolledStudentDrafteesRate(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl2);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getNoticeEnrolledStudentDrafteesRate")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String xmlString = getResponse(webClient, uri);

        try {
            Optional<Object> findObject = extractMultiParam(xmlString, NoticeFreshmanDrafteesRateResponse.class);
//            DefaultAssert.isOptionalPresent(findObject);
            if (findObject.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getNoticeEnrolledStudentDrafteesRate");
            } else {
                NoticeFreshmanDrafteesRateResponse response = (NoticeFreshmanDrafteesRateResponse) findObject.get();

                dto.setEnroll(response.getIndctVal1());
                dto.setStudents(response.getIndctVal2());
                dto.setEnrollRate(response.getIndctVal4());
                dto.setEnrollAvg(response.getIndctAvg());
                dto.setEnrollImg(response.getIndctImg());
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonInsideFixedNumberFreshmanCompetitionRate(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl2);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonInsideFixedNumberFreshmanCompetitionRate")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String xmlString = getResponse(webClient, uri);
        String target = "indctVal1";

        try {
            Optional<String> findObject = extractOneParam(xmlString, target);
//            DefaultAssert.isOptionalPresent(findObject);
            if (findObject.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonInsideFixedNumberFreshmanCompetitionRate");
            } else {
                String compRate = findObject.get();

                dto.setCompRate(compRate);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getNoticeGraduateEmploymentRate(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl2);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getNoticeGraduateEmploymentRate")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String xmlString = getResponse(webClient, uri);

        try {
            Optional<Object> object = extractMultiParam(xmlString, NoticeFreshmanDrafteesRateResponse.class);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getNoticeGraduateEmploymentRate");
            } else {
                NoticeFreshmanDrafteesRateResponse response = (NoticeFreshmanDrafteesRateResponse) object.get();
//            api response 형태 같음 <- NoticeFreshmanDrafteesRateResponse
                dto.setGraduate(response.getIndctVal1());
                dto.setEmployed(response.getIndctVal2());
                dto.setCandidate(response.getIndctVal3());
                dto.setEmployedRate(response.getIndctVal4());
                dto.setEmployedAvg(response.getIndctAvg());
                dto.setEmployedImg(response.getIndctImg());
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl3);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonFullTimeFacultyForPersonStudentNumberEnrolledStudent");
            } else {
                String stprRate = object.get();

                dto.setStprRate(stprRate);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonLectureChargeRatio(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl3);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonLectureChargeRatio")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonLectureChargeRatio");
            } else {
                String lcRate = object.get();

                dto.setLcRate(lcRate);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl3);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonFullTimeFacultyInsideOfSchoolForPersonResearchGrant");
            } else {
                String insideResearchGrant = object.get();

                dto.setInsideResearchGrant(insideResearchGrant);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl3);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonFullTimeFacultyOutsideOfSchoolForPersonResearchGrant");
            } else {
                String outsideResearchGrant = object.get();

                dto.setOutsideResearchGrant(outsideResearchGrant);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getNoticeFullTimeFacultyResearchCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl3);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getNoticeFullTimeFacultyResearchCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String xmlString = getResponse(webClient, uri);

        try {
            Optional<Object> findObject = extractMultiParam(xmlString, NoticeFreshmanDrafteesRateResponse.class);
//            DefaultAssert.isOptionalPresent(findObject);

            if (findObject.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getNoticeFullTimeFacultyResearchCrntSt");
            } else {
                NoticeFreshmanDrafteesRateResponse response = (NoticeFreshmanDrafteesRateResponse) findObject.get();

                dto.setProfessors(response.getIndctVal1());
                dto.setResearchRate1(response.getIndctVal2());
                dto.setResearchRate2(response.getIndctVal4());
                dto.setResearchAvg(response.getIndctAvg());
                dto.setResearchImg(response.getIndctImg());
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonDormitoryAcceptanceCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl4);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonDormitoryAcceptanceCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonDormitoryAcceptanceCrntSt");
            } else {
                String dormitoryRate = object.get();

                dto.setDormitoryRate(dormitoryRate);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonLibraryBudgetCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl4);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonLibraryBudgetCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonLibraryBudgetCrntSt");
            } else {
                String libraryBudget = object.get();

                dto.setLibraryBudget(libraryBudget);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonStudentForPersonDataPurchasePrice(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl4);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonStudentForPersonDataPurchasePrice")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonStudentForPersonDataPurchasePrice");
            } else {
                String purchasePrice = object.get();

                dto.setPurchasePrice(purchasePrice);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonTuitionCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl5);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonTuitionCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonTuitionCrntSt");
            } else {
                String tuition = object.get();

                dto.setTuition(tuition);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonScholarshipBenefitCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl5);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonScholarshipBenefitCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonScholarshipBenefitCrntSt");
            } else {
                String scholarship = object.get();

                dto.setScholarship(scholarship);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonEducationalExpensesReductionCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl5);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonEducationalExpensesReductionCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
            if (object.isPresent()) {
                String expenseReduction = object.get();

                dto.setExpenseReduction(expenseReduction);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonEducationExpensesLoanCrntSt(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl5);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonEducationExpensesLoanCrntSt")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonEducationExpensesLoanCrntSt");
            } else {
                String loan = object.get();

                dto.setLoan(loan);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getComparisonEducationExpensesLoanUseStudentRatioTuition(UniversityDetailDto dto) {
        WebClient webClient = getWebClient(baseUrl5);

        Function<UriBuilder, URI> uri = uriBuilder ->
                uriBuilder
                        .path("/getComparisonEducationExpensesLoanUseStudentRatioTuition")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("svyYr", "{svyYr}")
                        .queryParam("schlId", "{schlId}")
                        .queryParam("numOfRows", "{numOfRows}")
                        .build(serviceKey, dto.getSvyYr(), dto.getSchlId(), 999);

        String response = getResponse(webClient, uri);
        String target = "indctVal1";
        try {
            Optional<String> object = extractOneParam(response, target);
//            DefaultAssert.isOptionalPresent(object);
            if (object.isEmpty()) {
                log.info("{} -> {} 실패", dto.getSchlKrnNm(), "getComparisonEducationExpensesLoanUseStudentRatioTuition");
            } else {
                String loanUseRate = object.get();

                dto.setLoanUseRate(loanUseRate);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getResponse(WebClient webClient, Function<UriBuilder, URI> uri) {
        String response = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(10L));
        return response;
    }

    private static WebClient getWebClient(String baseUrl) {
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl(baseUrl)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                        .build();
        return webClient;
    }

    private Optional<ComparisonUniversitySearchListResponse> xmlToUniversitySearchList(String xmlString, String schlKrnNm) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        NodeList items = getNodeList(xmlString);

        for (int i = 0; i < items.getLength(); i++) {
            ComparisonUniversitySearchListResponse result = new XmlMapper().readValue(xmlToString(items.item(i)), ComparisonUniversitySearchListResponse.class);
            if (result.getSchlKrnNm().equals(schlKrnNm)) {
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

    private Optional<Object> extractMultiParam(String xmlString, Class<?> object) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        NodeList items = getNodeList(xmlString);
        if (items.getLength() != 0) {
            Object result = new XmlMapper().readValue(xmlToString(items.item(0)), object);
            return Optional.of(result);
        }
        else
            return Optional.empty();
    }
    private Optional<String> extractOneParam(String xmlString, String target) throws ParserConfigurationException, IOException, SAXException {
        NodeList items = getNodeList(xmlString);


        for (int i = 0; i < items.getLength(); i++) {
//            i 번째 아이템
//            의 두번째 노드 -> indictVal1
//                log.info(items.item(i).getFirstChild().getNodeName());
//                log.info(items.item(i).getChildNodes().item(1).getTextContent());
//                return Optional.of(items.item(i).getChildNodes().item(1).getTextContent());
                NodeList childNodes = items.item(i).getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeName().equals(target)) {
                        log.info(childNodes.item(j).getTextContent());
                        return Optional.of(childNodes.item(j).getTextContent());
                    }
                }
        }
        return Optional.empty();
    }

    private static NodeList getNodeList(String xmlString) throws SAXException, IOException, ParserConfigurationException {
        StringReader sr = new StringReader(xmlString);
        InputSource is = new InputSource(sr);
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("response");
        NodeList items = nList.item(0)
                .getChildNodes().item(1)        // body
                .getFirstChild().getChildNodes();
        return items;
    }
    private static String xmlToString(Node node) throws TransformerException {
        try {
            StringWriter clsOutput = new StringWriter();
            Transformer clsTrans = TransformerFactory.newInstance().newTransformer();

            clsTrans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            clsTrans.setOutputProperty(OutputKeys.METHOD, "xml");
            clsTrans.setOutputProperty(OutputKeys.INDENT, "yes");
            clsTrans.setOutputProperty(OutputKeys.ENCODING, "utf-8");

            clsTrans.transform(new DOMSource(node), new StreamResult(clsOutput));
            return clsOutput.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
