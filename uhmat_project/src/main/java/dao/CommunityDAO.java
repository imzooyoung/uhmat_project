package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import vo.CommunityTmiDTO;

import static db.JdbcUtil.*;

public class CommunityDAO {
	// 1. 멤버변수 선언 및 인스턴스 생성
	private static CommunityDAO instance = new CommunityDAO();
	
	// 2. 생성자 정의
	private CommunityDAO() {}
	
	public static CommunityDAO getInstance() {
		return instance;
	}

	// --------------------------------------------------------------------
	// 외부(Service Clas)로 부터 Connection 객체를 전달받아 관리하기 위해
	// Conneciton 타입 멤버변수와 Setter 메서드를 정의합니다.
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// ----------------------------------------------------------------------------------
	
	// tmi게시판 전체 게시물 수를 조회할 selcetTmiListCount() 메서드 정의
	// => 파라미터 : 없음,	리턴타입 : int(tmilistCount)
	public int selectTmiListCount() {
		System.out.println("CommunityDAO - selectTmiListCount() 호출!");
		int tmiListCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM community_tmi";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tmiListCount = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectTmiListCount() " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return tmiListCount;
	}

	// tmi게시판 목록을 조회하는 selectTmiBoardList() 메서드 정의
	// => 파라미터 : 현재페이지번호(pageNum), 페이지 당 게시물 수 (listLimit)
	// 	  리턴타입 : ArrayList<CommunityDTO> tmiBoardList
	public ArrayList<CommunityTmiDTO> selectTmiBoardList(int pageNum, int listLimit) {
		System.out.println("CommunityDAO - selectTmiBoardList() 호출!");
		ArrayList<CommunityTmiDTO> tmiBoardList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 현재 페이지 번호를 활용하여 조회 시 시작행 번호를 계산
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM community_tmi ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			tmiBoardList = new ArrayList<CommunityTmiDTO>();
			
			// while문을 사용하여 조회 결과에 대한 반복 작업을 수행.
			while(rs.next()) {
				// tmi게시판 1개 게시물 정보를 저장할 CommunityTmiDTO 의 객체를 생성.
				CommunityTmiDTO tmiBoard = new CommunityTmiDTO();
				tmiBoard.setIdx(rs.getInt("idx"));
				tmiBoard.setNickname(rs.getString("nickname"));
				tmiBoard.setSubject(rs.getString("subject"));
				tmiBoard.setDatetime(rs.getTimestamp("datetime"));
				tmiBoard.setReadcount(rs.getInt("readcount"));
				
				tmiBoardList.add(tmiBoard);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 발생! - selectTmiBoardList" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return tmiBoardList;
	}

	// CommunityDAO 객체(인스턴스)의 insertTmiBoard() 메서드를 호출하여 글쓰기 작업을 수행 한 후 결과를 리턴받습니다.
	// => 파라미터 : CommunityDAO 객체(board) 리턴타입 : int(insertCount)
	public int insertTmiBoard(CommunityTmiDTO tmiBoard) {
		System.out.println("CommunityDAO - insertTmiBoard() 호출!");
		
		int tmiInsertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 1;
		
		try {
			String sql = "SELECT MAX(idx) FROM community_tmi";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			close(pstmt);
			
			sql = "INSERT INTO community_tmi VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, tmiBoard.getNickname());
			pstmt.setString(3, tmiBoard.getSubject());
			pstmt.setString(4, tmiBoard.getContent());
			pstmt.setInt(5, tmiBoard.getReadcount());
			pstmt.setTimestamp(6, tmiBoard.getDatetime());
			
			tmiInsertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 작성 오류! - insertTmiBoard() " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return tmiInsertCount;
	}

	// tmi 글 상세정보를 조회하여 요청하는 selectTmiBoard() 메서드 정의
	// => 파라미터 : 글번호(tmiIdx)		리턴타입 : CommunityDTO(idx)
	public CommunityTmiDTO selectTmiBoard(int idx) {
		System.out.println("CommunityDAO - selectTmiBoard() 호출!");
		CommunityTmiDTO tmiBoard = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM community_tmi WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tmiBoard = new CommunityTmiDTO();
				tmiBoard.setIdx(rs.getInt("idx"));
				tmiBoard.setNickname(rs.getString("nickname"));
				tmiBoard.setSubject(rs.getString("subject"));
				tmiBoard.setContent(rs.getString("content"));
				tmiBoard.setDatetime(rs.getTimestamp("datetime"));
				tmiBoard.setReadcount(rs.getInt("readcount"));
				System.out.println(tmiBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - selectTmiBoard()" + e.getMessage());
			
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		return tmiBoard;
	}

	// tmi 글의 조회수 증가 작업을 처리하는 updateTmiReadCount() 메서드 정의
	// => 파라미터 : idx(CommunityTmiDTO)
	public void updateTmiReadCount(int idx) {
		System.out.println("CommunityDAO - updateTmiReadCount() 호출!");
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE community_tmi SET readcount=readcount+1 WHERE idx=?";
			// 조회수 증가 작업 update 구문 재 확인 필수!
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - updateReadcount()" + e.getMessage());
			
		} finally {
			close(pstmt);
		}
	}

	// 글 수정, 삭제 권한 판별을 수행하는 isTmiWriter() 메서드 정의
	public boolean isTmiWriter(int idx, String nickname) {
		boolean isTmiWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM community_tmi WHERE idx=? AND nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, nickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 조회 결과 있을 시 번호에 해당하는 닉네임이 일치
				isTmiWriter = true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - isTmiWriter()" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return isTmiWriter;
	}

	// 글 수정 작업을 수행하는 updateTmiBoard() 메서드 정의
	public int updateTmiBoard(CommunityTmiDTO tmiBoard) {
		int updateTmiCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE community_tmi SET nickname=?,subject=?,content=? WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tmiBoard.getNickname());
			pstmt.setString(2, tmiBoard.getSubject());
			pstmt.setString(3, tmiBoard.getContent());
			pstmt.setInt(4, tmiBoard.getIdx());
			
			updateTmiCount = pstmt.executeUpdate();
			System.out.println(updateTmiCount);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateTmiBoard()" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateTmiCount;
	}
		
}
	
