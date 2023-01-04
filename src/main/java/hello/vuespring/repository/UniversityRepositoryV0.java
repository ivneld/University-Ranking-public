package hello.vuespring.repository;

import hello.vuespring.connection.DBConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;

import static hello.vuespring.connection.DBConnectionUtil.*;

@Slf4j
public class UniversityRepositoryV0 {

    private final DataSource dataSource;

    public UniversityRepositoryV0(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public JSONObject selectAll() throws SQLException, ParseException {
        String sql = "select * from universities";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            ArrayList<University> list = new ArrayList<>();
            while (rs.next()) {
                University universities = new University();
                universities.setId(rs.getInt("id"));
                universities.setName(rs.getString("name"));
                universities.setEngName(rs.getString("engName"));
                universities.setCitation(rs.getString("citation"));
                universities.setCompRate(rs.getString("compRate"));

                universities.setIntro(rs.getString("intro"));
                universities.setSFRatio(rs.getString("SFRatio"));
                universities.setTotStud(rs.getInt("TotStud"));
                universities.setTuition(rs.getInt("tuition"));
                universities.setWebsite(rs.getString("website"));
//                universities.setSource(rs.getString("source"));
                String source = rs.getString("source");

                universities.setSource(stringToJSON(source));

                setArrayList(universities);

                list.add(universities);

            }

            JSONArray array = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                University ele = list.get(i);
                obj.put("id", ele.getId());
                obj.put("name",ele.getName());
                obj.put("engName", ele.getEngName());
                obj.put("citation", ele.getCitation());
                obj.put("compRate", ele.getCompRate());
                obj.put("intro", ele.getIntro());
                obj.put("SFRatio", ele.getSFRatio());
                obj.put("TotStud", ele.getTotStud());
                obj.put("tuition", ele.getTuition());
                obj.put("website", ele.getWebsite());
                obj.put("qs_subjects", ele.getQs_subjects());
                obj.put("the_subjects", ele.getThe_subjects());
                obj.put("source",ele.getSource());

                array.add(obj);
            }

            JSONObject object = new JSONObject();
            object.put("universities", array);
            return object;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void setArrayList(University university) {
        university.insertQSList("물리학");
        university.insertQSList("예술");
        university.insertQSList("수학");
        university.insertQSList("과학");
//        university.insertQSList("SM_rank");
        university.insertTHEList("사회");
        university.insertTHEList("우주공학");
        university.insertTHEList("컴퓨터공학");
        university.insertTHEList("인문");
    }
    private JSONObject stringToJSON(String source) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(source);
        return jsonObject;
    }


    private void close(Connection con, Statement stmt, ResultSet resultSet) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(con);
    }

    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }
}
