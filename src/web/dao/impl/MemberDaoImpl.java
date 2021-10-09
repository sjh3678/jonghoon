package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dao.face.MemberDao;
import web.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntMemberByUseridUserpw(Connection conn, Member member) {

		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE 1=1";
		sql += " AND userid = ?";
		sql += " AND userpw = ?";
		
		//결과 저장할 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			
			rs = ps.executeQuery();
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}

	@Override
	public Member selectMemberByUserid(Connection conn, Member member) {

		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE 1=1";
		sql += " 	AND userid = ?";
		
		//조회결과를 저장할 객체
		Member result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new Member();
				
				result.setUserid(rs.getString("userid"));
				result.setUserpw(rs.getString("userpw"));
				result.setUsernick(rs.getString("usernick"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return result;
	}

	@Override
	public int insert(Connection conn, Member member) {
		
		String sql = "";
		sql += "INSERT INTO member (userid, userpw, usernick )";
		sql += " VALUES(?, ?, ?)";
		
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getUsernick());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
