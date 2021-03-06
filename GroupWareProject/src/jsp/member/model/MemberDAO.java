package jsp.member.model;
 
import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;

import jsp.common.util.DBConnection;
 
 
/* Data Access Object
 * 테이블 당 한개의 DAO를 작성한다.
 * 
 * JSP_MEMBER 테이블과 연관된 DAO로
 * 회원 데이터를 처리하는 클래스이다.
 */
public class MemberDAO {
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private static MemberDAO instance;
    
    // 싱글톤 패턴
    private MemberDAO(){}
    public static MemberDAO getInstance(){
        if(instance==null)
            instance=new MemberDAO();
        return instance;
    }
    
    // 회원정보를 JSP_MEMBER 테이블에 저장하는 메서드
    public void insertMember(MemberVO member) throws SQLException
    {
        
        try {
            // 커넥션을 가져온다.
            conn = DBConnection.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            // 쿼리 생성한다.
            // 가입일의 경우 자동으로 세팅되게 하기 위해 sysdate를 사용
            StringBuffer sql = new StringBuffer();
            sql.append("insert into GW_MEMBER values");
            sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, systimestamp)");      

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, member.getEmp_num());
            pstmt.setString(2, member.getMember_pw());
            pstmt.setString(3, member.getMember_name());
            pstmt.setString(4, member.getMember_birth());
            pstmt.setString(5, member.getMember_pNum());
            pstmt.setString(6, member.getMember_email());
            pstmt.setString(7, member.getMember_bank_account());
            pstmt.setString(8, member.getMember_team());
            pstmt.setString(9, member.getMember_rank());
            pstmt.setString(10, member.getMember_administrator());
            
            // 쿼리 실행
            pstmt.executeUpdate();
            // 완료시 커밋
            conn.commit(); 
            
        } catch (ClassNotFoundException | NamingException | SQLException sqle) {
            // 오류시 롤백
            conn.rollback(); 
            
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } // end try~catch 
    } // end insertMember()
    
    // 로그인시 아이디, 비밀번호 체크 메서드
    // 아이디, 비밀번호를 인자로 받는다.
    public int loginCheck(String emp_num, String member_pw) {
 
        String dbPW = ""; //db에서 꺼낸 비밀번호를 담을 변수
        int x = -1;
 
        try {
            // 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
            StringBuffer query = new StringBuffer();
            query.append("SELECT MEMBER_PW FROM GW_MEMBER WHERE EMP_NUM=?");
 
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, emp_num);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // 입력된 아이디에 해당하는 비번 있을경우
            {
                dbPW = rs.getString("member_pw"); // DB의 비번을 변수에 넣는다.
 
                if (dbPW.equals(member_pw)) 
                    x = 1; // 넘겨받은 비번과 꺼내온 비번 비교. 같으면 인증성공
                else                  
                    x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패
                
            } else {
                x = -1; // 해당 아이디가 없을 경우
            }
 
            return x;
 
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end loginCheck()
    
    //마이페이지 정보불러오기
    public ArrayList<MemberVO> getMemberInfo(String emp_num){
    	ArrayList<MemberVO> informations = new ArrayList<MemberVO>();
    	try {
    		conn = DBConnection.getConnection();
    		StringBuffer sql = new StringBuffer();
    		
    		sql.append("select * from GW_MEMBER ");
    		sql.append(" where EMP_NUM=?");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setString(1, emp_num);
    		
    		// StringBuffer를 비우기
    		sql.delete(0, sql.toString().length());
    		
    		rs = pstmt.executeQuery();
    		while(rs.next()) {
    			MemberVO member = new MemberVO();
    			member.setEmp_num(rs.getString("EMP_NUM"));
    			member.setMember_pw(rs.getString("MEMBER_PW"));
    			member.setMember_name(rs.getString("MEMBER_NAME"));
    			member.setMember_birth(rs.getString("MEMBER_BIRTH"));
    			member.setMember_pNum(rs.getString("MEMBER_PNUM"));
    			member.setMember_email(rs.getString("MEMBER_EMAIL"));
    			member.setMember_bank_account(rs.getString("MEMBER_BANK_ACCOUNT"));
    			member.setMember_team(rs.getString("MEMBER_TEAM"));
    			member.setMember_rank(rs.getString("MEMBER_RANK"));
    			member.setMember_administrator(rs.getString("MEMBER_ADMINISTRATOR"));
    			member.setMember_regdate(rs.getDate("MEMBER_REGDATE"));
    			
    			informations.add(member);
    		}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    	
		return informations;
    }//프로필 정보불러오기 끝
    
    //프로필 정보 수정하기
    public void updateProfile(MemberVO member) throws SQLException{

        try {
            // 커넥션을 가져온다.
            conn = DBConnection.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
         // 쿼리 생성한다.
            // 가입일의 경우 자동으로 세팅되게 하기 위해 sysdate를 사용
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE GW_MEMBER ");
            sql.append(" SET");
            sql.append(" MEMBER_PW = ?, ");
            sql.append(" MEMBER_NAME = ?, ");
            sql.append(" MEMBER_PNUM = ?, ");
            sql.append(" MEMBER_EMAIL = ?, ");
            sql.append(" MEMBER_BANK_ACCOUNT = ? ");
            sql.append(" WHERE");
            sql.append(" EMP_NUM = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, member.getMember_pw());
            pstmt.setString(2, member.getMember_name());
            pstmt.setString(3, member.getMember_pNum());
            pstmt.setString(4, member.getMember_email());
            pstmt.setString(5, member.getMember_bank_account());
            pstmt.setString(6, member.getEmp_num());
            
            // 쿼리 실행
            pstmt.executeUpdate();
            // 완료시 커밋
            conn.commit(); 
        }catch (ClassNotFoundException | NamingException | SQLException sqle) {
            // 오류시 롤백
            conn.rollback(); 
            
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } // end try~catch 
    }
    
    //주소록 불러오기 및 검색기능
	public ArrayList<MemberVO> getMemberList(String condition) {
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			if(condition == null) {
				sql.append("select MEMBER_NAME, MEMBER_PNUM, MEMBER_EMAIL ");
				sql.append(" , MEMBER_TEAM, MEMBER_RANK, MEMBER_ADMINISTRATOR ");
				sql.append(" from GW_MEMBER ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				sql.delete(0, sql.toString().length());
			}else {
				sql.append("select MEMBER_NAME, MEMBER_PNUM, MEMBER_EMAIL ");
				sql.append(" , MEMBER_TEAM, MEMBER_RANK, MEMBER_ADMINISTRATOR ");
				sql.append(" from (select * from GW_MEMBER where ");
				sql.append(" MEMBER_NAME like ? OR MEMBER_TEAM like ? OR MEMBER_RANK like ?)");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, condition);
				pstmt.setString(2, condition);
				pstmt.setString(3, condition);
				
				sql.delete(0, sql.toString().length());
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMember_name(rs.getString("MEMBER_NAME"));
				member.setMember_pNum(rs.getString("MEMBER_PNUM"));
				member.setMember_email(rs.getString("MEMBER_EMAIL"));
				member.setMember_team(rs.getString("MEMBER_TEAM"));
				member.setMember_rank(rs.getString("MEMBER_RANK"));
				member.setMember_administrator(rs.getString("MEMBER_ADMINISTRATOR"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
		
		return list;
	}//주소록 불러오기 및 검색기능 끝
	
	//주소록 - 팀별 사람들 이름 불러오기
	public ArrayList<MemberVO> getMemberTeam(int teamNum){
		
		ArrayList<MemberVO> team = new ArrayList<MemberVO>();
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(teamNum == 1) {
				sql.append("select MEMBER_NAME, MEMBER_TEAM ");
				sql.append(" from (select * from GW_MEMBER where ");
				sql.append(" MEMBER_TEAM like '대표') ");
			}else if(teamNum == 2){
				sql.append("select MEMBER_NAME, MEMBER_TEAM ");
				sql.append(" from (select * from GW_MEMBER where ");
				sql.append(" MEMBER_TEAM like '기획팀') ");
			}else if(teamNum == 3){
				sql.append("select MEMBER_NAME, MEMBER_TEAM ");
				sql.append(" from (select * from GW_MEMBER where ");
				sql.append(" MEMBER_TEAM like '개발팀') ");
			}else if(teamNum == 4){
				sql.append("select MEMBER_NAME, MEMBER_TEAM ");
				sql.append(" from (select * from GW_MEMBER where ");
				sql.append(" MEMBER_TEAM like '디자인팀') ");
			}
				pstmt = conn.prepareStatement(sql.toString());
				
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMember_name(rs.getString("MEMBER_NAME"));
				team.add(member);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
		
		return team;
	}// 주소록 - 팀별 사람들 이름불러오기
	
	
	//주소록 - 개개인 정보 불러오기
	 public ArrayList<MemberVO> getMemberUniqueInfo(String member_name){

	    	ArrayList<MemberVO> information = new ArrayList<MemberVO>();
	    	try {
	    		conn = DBConnection.getConnection();
	    		StringBuffer sql = new StringBuffer();
	    		

	    		int idx = member_name.indexOf("_");
	    		
	    		String member_team = member_name.substring(idx);
	    		member_name = member_name.substring(0, idx);
	    		
	    		if(member_team.equals("_ceo")) {
	    			member_team ="대표";
	    		}else if(member_team.equals("_plan")) {
	    			member_team ="기획팀";
	    		}else if(member_team.equals("_dev")) {
	    			member_team ="개발팀";
	    		}else if(member_team.equals("_desi")) {
	    			member_team ="디자인팀";
	    		}
	    		
	    		sql.append("select * from GW_MEMBER ");
	    		sql.append(" where MEMBER_NAME=? AND MEMBER_TEAM=?");
	    		
	    		pstmt = conn.prepareStatement(sql.toString());
	    		pstmt.setString(1, member_name);
	    		pstmt.setString(2, member_team);
	    		
	    		// StringBuffer를 비우기
	    		sql.delete(0, sql.toString().length());
	    		
	    		rs = pstmt.executeQuery();
	    		while(rs.next()) {
	    			MemberVO member = new MemberVO();
	    			member.setMember_name(rs.getString("MEMBER_NAME"));
	    			member.setMember_pNum(rs.getString("MEMBER_PNUM"));
	    			member.setMember_email(rs.getString("MEMBER_EMAIL"));
	    			member.setMember_team(rs.getString("MEMBER_TEAM"));
	    			member.setMember_rank(rs.getString("MEMBER_RANK"));
	    			
	    			information.add(member);
	    		}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}finally {
	            // Connection, PreparedStatement를 닫는다.
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( conn != null ){ conn.close(); conn=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	    	
			return information;
	    }//주소록 - 개개인 정보 불러오기
	 
	 public ArrayList<MemberVO> getMemberSearchInfo(String member_name){
		 ArrayList<MemberVO> information = new ArrayList<MemberVO>();
	    	try {
	    		conn = DBConnection.getConnection();
	    		StringBuffer sql = new StringBuffer();
	    		
	    		sql.append("select * from GW_MEMBER ");
	    		sql.append(" where MEMBER_NAME=?");
	    		
	    		pstmt = conn.prepareStatement(sql.toString());
	    		pstmt.setString(1, member_name);
	    		
	    		// StringBuffer를 비우기
	    		sql.delete(0, sql.toString().length());
	    		
	    		rs = pstmt.executeQuery();
	    		while(rs.next()) {
	    			MemberVO member = new MemberVO();
	    			member.setMember_name(rs.getString("MEMBER_NAME"));
	    			member.setMember_pNum(rs.getString("MEMBER_PNUM"));
	    			member.setMember_email(rs.getString("MEMBER_EMAIL"));
	    			member.setMember_team(rs.getString("MEMBER_TEAM"));
	    			member.setMember_rank(rs.getString("MEMBER_RANK"));
	    			
	    			information.add(member);
	    		}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}finally {
	            // Connection, PreparedStatement를 닫는다.
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( conn != null ){ conn.close(); conn=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	    	
			return information;
	 }
}
