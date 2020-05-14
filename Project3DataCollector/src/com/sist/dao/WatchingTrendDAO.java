package com.sist.evaluationtrend;

import java.util.*;
import java.sql.*;

public class WatchingTrendDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public WatchingTrendDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			// TODO: handle exception
		}
	}

	public void WatchingTrendInsert(WatchingTrendVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO Watching_Trend (movie_id,male_rating,female_rating,age_10,age_20,age_30,age_40,age_50) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMovie_id());
			ps.setDouble(2, vo.getMale_rating());
			ps.setDouble(3, vo.getFemale_rating());
			ps.setDouble(4, vo.getAge_10());
			ps.setDouble(5, vo.getAge_20());
			ps.setDouble(6, vo.getAge_30());
			ps.setDouble(7, vo.getAge_40());
			ps.setDouble(8, vo.getAge_50());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}

}
