package jsp.vc.action;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.VcDAO;
import jsp.vc.model.VcVO;

public class MainVCFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//인코딩
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		//session emp_num 가져오기
		String emp_num = (String)session.getAttribute("sessionID");
		
		Calendar cal = Calendar.getInstance();
		String year;//받아올, 설정할 년
		String cal_year;// 캘린더에 출력될 년
		String month;// 받아올, 설정할 월
		int cal_month; // 캘린더에 출력될 월
		
		
		//년 구하기/ 받아온 년도가 있다면 그것으로 설정 아니라면 현재 년도로 설정.
		year = (String)request.getParameter("year");
		if(year == null) {
			cal_year = Integer.toString(cal.get ( Calendar.YEAR ));
		}else {
			cal_year = year;
		}
		//월 구하기/ 받아온 월이 있다면 그것으로 설정 아니라면 현재 월로 설정.
		month = (String) request.getParameter("month");
		if(month == null) {
			cal_month = cal.get(Calendar.MONTH)+1;
		}else {
			cal_month = Integer.parseInt(month);
		}
		System.out.println("출력되어야 하는 달은? : "+ month);
		
		cal.set(Integer.parseInt(cal_year),cal_month-1,1);
		
		int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 월의 말일 구하기
		System.out.println("이달의 마지막 일::"+endDayOfMonth);
		
		System.out.println("년도::"+cal_year);

		cal_year = cal_year.substring(2);//년도 앞 20 제외하고 받아오기 dao에서 검색하기 위한 작업.
		//GW_VC 정보 DAO 통해서 가져오기 
		VcDAO vdao = VcDAO.getInstance();
		ArrayList<VcVO> list = vdao.VcInfo(emp_num , cal_year);
		
		System.out.println("dao에서 최초 받아오는 값 = "+list);
		
			//list value값만큼 배열 초기화
			String [] start_date = new String[list.size()];
			String [] end_date = new String[list.size()];
			String [] content = new String[list.size()];	
			int vcCount = 0;
			cal_year ="20"+cal_year;//dao작업 이후 다시 yyyy형태로 변경
			
			//변수 시작월, 종료월, 시작일, 종료일.
			int []startYear = new int[list.size()];
			int []endYear = new int[list.size()];
			int []startMonth = new int[list.size()];
			int []endMonth = new int[list.size()];
			int []startDay = new int[list.size()];
			int []endDay = new int[list.size()];		
			
			for( int i =0; i < list.size(); i++){
					start_date[i] = list.get(i).getVc_start_date();
					end_date[i] = list.get(i).getVc_end_date();
					content[i] =  list.get(i).getVc_content();
					
					startYear[i] = Integer.parseInt(start_date[i].substring(0,4));
					endYear[i] = Integer.parseInt(start_date[i].substring(0,4));
					System.out.println("시작하는 년도 : " + startYear[i] + " 끝나는 년도 : " + endYear[i]);
					
					startMonth[i] = Integer.parseInt(start_date[i].substring(5,7));
					endMonth[i] = Integer.parseInt(end_date[i].substring(5,7));
					System.out.println("시작하는 달 : " + startMonth[i] + " 끝나는 달 : " + endMonth[i]);
					
					startDay[i] = Integer.parseInt(start_date[i].substring(8,10));
					endDay[i] = Integer.parseInt(end_date[i].substring(8,10));
					System.out.println("일자 변환 전 시작하는 일 :" + startDay[i] + " 끝나는 일 : " + endDay[i]);
					
					System.out.println("일자 변환전 : 시작 날짜 : "+start_date[i] + " 끝나는 날짜 : " + end_date[i]);
						
					if ( startMonth[i] < cal_month ) {
						startMonth[i] = cal_month;//시작날이 현재달보다 작을경우 현재달을 시작하는달로 설정
						if( cal_month < 10 ) {
							start_date[i] = cal_year+"-0"+cal_month+"-01";
						} else {
							start_date[i] = cal_year+"-"+cal_month+"-01";
						}
					}
					if( endMonth[i] > cal_month) {
						endMonth[i] = cal_month;//끝나는달이 현재달보다 작을경우 현재달을 끝나는달로 설정
						if( cal_month < 10 ) {
							end_date[i] = cal_year + "-0" + cal_month + "-" + endDayOfMonth;
						} else {
							end_date[i] = cal_year+"-"+cal_month+"-" + endDayOfMonth;
						}
					}
					//일자 변환 후 일자 다시 대입
					startDay[i] = Integer.parseInt(start_date[i].substring(8,10));
					endDay[i] = Integer.parseInt(end_date[i].substring(8,10));
	
					System.out.println("일자 변환 후 시작하는 일 :" + startDay[i] + " 끝나는 일 : " + endDay[i]);
					
					
					System.out.println("일자 변환후 시작하는 날짜 : " + start_date[i] + " 끝나는 날짜 : " + end_date[i]+"\n");
					
			        vcCount = i+1;
			}
			request.setAttribute("startYear", startYear);
			request.setAttribute("endYear", endYear);
			request.setAttribute("startMonth", startMonth);
			request.setAttribute("endMonth", endMonth);		
			request.setAttribute("startDay", startDay);
		    request.setAttribute("endDay", endDay);				
		    request.setAttribute("content", content);
			request.setAttribute("vcCount", vcCount);
		
		request.setAttribute("cal_year", cal_year);
		request.setAttribute("cal_month", cal_month);
		
		forward.setRedirect(false);
		forward.setNextPath("mainVC.vc");
		
		return forward;  
	}

}
