package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.common.util.DBConnection;

public class BoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO(){
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/sign");
		} catch (Exception e) {
			System.out.println("board dao e : "+e);
			return;
		}
		
	}
	//리스트
	public int getListCount() {
		int x= 0;
		
		try {
			conn=ds.getConnection();
			System.out.println("getConnection");
			
			pstmt=conn.prepareStatement("select count(*) from gw_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return x;
	}
	public int SearchCount(String searchName,String searchValue) {
		int x= 0;
				try {
			conn=ds.getConnection();
			System.out.println("getConnection");
			if(searchName.equals("title")) {
				String sql = "select count(*) from gw_board where upper(board_title) like upper('%"+searchValue+"%')";
				pstmt=conn.prepareStatement(sql);
			}
			else {
				String sql = "select count(*) from gw_member where upper(member_name) like upper('%"+searchValue+"%')";
				pstmt=conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
	return x;
	}
	public List<BoardVO> Search(int page, int limit,String searchName,String searchValue) {
		System.out.println("Search메소드 실행");
		String board_list_sql="select * from " + 
				"(select rownum rnum,t2.board_num, t2.board_title, t1.member_name, t2.board_writedate, t2.board_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_board t2 on t1.emp_num=t2.emp_num order by t2.board_num desc) " + 
				"WHERE rnum>=? and rnum<=? ";
		if(searchName.equals("title")) {
			board_list_sql+= "and upper(board_title) like upper(?)";
		}else {
			board_list_sql+="and upper(member_name) like upper(?)";
		}
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		int startrow=(page-1)*10+1;//이게뭐지?
		int endrow=startrow+limit-1;//이것도뭐지
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%"+searchValue+"%");
			rs = pstmt.executeQuery();
			System.out.println("query 문 실행");
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoard_num(rs.getInt("board_num"));
				bvo.setBoard_title(rs.getString("board_title"));
				System.out.println("board_title : "+rs.getString("board_title"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setBoard_writedate(rs.getString("board_writedate"));
				bvo.setBoard_readcount(rs.getString("board_readcount"));
				bvo.setMember_team(rs.getString("member_team"));
				System.out.println("list : "+bvo.toString());
				
				list.add(bvo);
			}
			
			
		} catch (Exception e) {
			System.out.println("getboardList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return list;
	}
	public List<BoardVO> getBoardList(int page, int limit) {
		String board_list_sql="select * from " + 
				"(select rownum rnum,t2.board_num, t2.board_title, t1.member_name, t2.board_writedate, t2.board_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_board t2 on t1.emp_num=t2.emp_num order by t2.board_num desc) " + 
				"WHERE rnum>=? and rnum<=?";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		int startrow=(page-1)*10+1;//이게뭐지?
		int endrow=startrow+limit-1;//이것도뭐지
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoard_num(rs.getInt("board_num"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setBoard_writedate(rs.getString("board_writedate"));
				bvo.setBoard_readcount(rs.getString("board_readcount"));
				bvo.setMember_team(rs.getString("member_team"));
				list.add(bvo);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getboardList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	//등록 이름, 팀명
	public BoardVO getNameTeam(String empNum) {
		BoardVO teamVO = new BoardVO();
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement("select member_name from gw_member where emp_num='?'");
			pstmt.setString(1, empNum);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				teamVO.setMember_name(rs.getString("member_name"));
				teamVO.setMember_team(rs.getString("member_team"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return teamVO;	
	}
	//등록 
	public boolean insertBoard(BoardVO bvo) {
		
		int num = 0;
		String sql="";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(board_num) from gw_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			sql="insert into gw_board(board_num,emp_num,board_title,board_content,board_readcount,board_file)"
					+ " values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bvo.getEmp_num());
			pstmt.setString(3, bvo.getBoard_title());
			pstmt.setString(4, bvo.getBoard_content());
			pstmt.setInt(5, 0);
			pstmt.setString(6, bvo.getBoard_file());
			
			result=pstmt.executeUpdate();
			if(result==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("boardInsert : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//조회수
	public void setReadCountUpdate(int num)throws Exception{
		
		String sql="update gw_board set BOARD_READCOUNT = "+
		"board_readcount+1 where board_num = "+num;
		
		try {
			conn= ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("setReadCountUpdate 조회수 : "+e);
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				}
				catch(Exception e){}
		}
		
	}
	//글의 내용 상세보기 출력
	public BoardVO getDetail(int num) throws Exception{
		BoardVO bvo = null;
		try {
			conn = ds.getConnection();
			pstmt = 
			conn.prepareStatement("select * from gw_board where board_num = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				bvo = new BoardVO();
				bvo.setEmp_num(rs.getString("emp_num"));
				bvo.setBoard_num(num);
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_writedate(rs.getString("board_writedate"));
				bvo.setBoard_file(rs.getString("board_file"));
				}
			return bvo;
		}catch (Exception e) {
			System.out.println("getDetail 상세보기 출력 : "+e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	
	//수정
	public boolean boardModify(BoardVO bvo) throws Exception{
		
		String sql="update gw_board set board_title=?,"
				+ " board_content=?,board_file=? where board_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getBoard_title());
			pstmt.setString(2, bvo.getBoard_content());
			pstmt.setString(3, bvo.getBoard_file());
			pstmt.setInt(4,bvo.getBoard_num());
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("boardModify : "+ e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//삭제
	public boolean boardDelete(int num) {
		String board_delete_sql="delete from gw_board where board_num=?";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
			
		} catch (Exception e) {
			System.out.println("boardDelete 지우기 : "+e);
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				}
				catch(Exception ex){}
		}
		return false;
	}
	//getmemberteaminpo
	public String getmemberteaminpo(String emp_num) {
		String sql="select member_team from gw_member where emp_num=?";
		String memberteam="";
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_num);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				memberteam=rs.getString("member_team");
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				if(rs!=null)rs.close();
				}
				catch(Exception ex){}			
		}
		return memberteam;
	}
	public String getmemberRank(String emp_num) {
		String sql="select member_rank from gw_member where emp_num=?";
		String memberrank="";
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_num);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				memberrank=rs.getString("member_rank");
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				if(rs!=null)rs.close();
				}
				catch(Exception ex){}			
		}
		return memberrank;
	}
	
	
	//페이지기능구현
	 public ArrayList<BoardVO> getListBoard(int startRow, int endRow) {
		 
		    ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		     String sql="";
		    try {
		      conn = ds.getConnection();
		       
		      sql = "select * from " + 
		      		"(select rownum rnum,t2.board_num, t2.board_title, t1.member_name, t2.board_writedate, t2.board_readcount, t1.member_team " + 
		      		"from gw_member t1 inner join gw_board t2 on t1.emp_num=t2.emp_num order by t2.board_num desc)" + 
		      		"WHERE rnum>=? and rnum<=?";

		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1, startRow);
		      pstmt.setInt(2, endRow);
		      rs = pstmt.executeQuery();
		       
		      while(rs.next()) {
		        
		        BoardVO dto = new BoardVO();
		        dto .setBoard_num(rs.getInt("board_num"));
		        dto .setBoard_title(rs.getString("board_title"));
		        dto .setBoard_readcount(rs.getString("board_readcount"));
		        dto .setBoard_writedate(rs.getString("board_writedate"));
		        dto.setMember_team(rs.getString("member_team"));
		        dto.setMember_name(rs.getString("member_name"));
		        list.add(dto);
		      }
		       
		    } catch (Exception e){
		      e.printStackTrace();
		    } finally {
		    	try{
					if(pstmt!=null)pstmt.close();
					if(conn!=null) conn.close();
					if(rs!=null)rs.close();
					}
					catch(Exception ex){}	
		    }
		    return list;
		  }
}
