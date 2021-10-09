package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.MemberDao;
import web.dao.impl.MemberDaoImpl;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public boolean login(Member member) {
		if( memberDao.selectCntMemberByUseridUserpw(JDBCTemplate.getConnection(), member)>0) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	public Member info(Member member) {
		return memberDao.selectMemberByUserid(JDBCTemplate.getConnection(), member);
	}

	@Override
	public Member getLoginMember(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setUserid( req.getParameter("userid") );
		member.setUserpw( req.getParameter("userpw") );
		
		return member;
	}

	@Override
	public Member getJoinMember(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setUserid(req.getParameter("userid"));
		member.setUserpw(req.getParameter("userpw"));
		member.setUsernick(req.getParameter("usernick"));
		
		return member;
	}

	@Override
	public void join(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, member)>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

}
