package web.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService {

	//BoardDao 객체 생성
	private BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public List<Board> getList() {
		
		//게시글 전체 조회 결과 처리
		return boardDao.selectAll(JDBCTemplate.getConnection());
		
	}
	
	@Override
	public List<Board> getList(Paging paging) {

		//게시글 전체 조회 결과 처리 - 페이징 추가
		return boardDao.selectAll(JDBCTemplate.getConnection(), paging);
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {
		
		//boardno를 저장할 객체 생성
		Board boardno = new Board();
		
		//boardno 전달파라미터 검증 - null, ""
		String param = req.getParameter("boardno");
		if(param!=null && !"".equals(param)) {
			
			//boardno 전달파라미터 추출
			boardno.setBoardno( Integer.parseInt(param) );
		}
		
		//결과 객체 반환
		return boardno;
	}

	@Override
	public Board view(Board boardno) {

		Connection conn = JDBCTemplate.getConnection();

		//조회수 증가
		if( boardDao.updateHit(conn, boardno) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Board board = boardDao.selectBoardByBoardno(conn, boardno); 
		
		return board;
	}

}



















