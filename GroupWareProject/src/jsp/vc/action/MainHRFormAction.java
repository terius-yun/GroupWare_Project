package jsp.vc.action;


import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.HrDAO;
import jsp.vc.model.HrVO;

public class MainHRFormAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       request.setCharacterEncoding("utf-8");

      ActionForward forward = new ActionForward();
      HttpSession session=request.getSession();
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

      
      cal.set(Integer.parseInt(cal_year),cal_month-1,1);
      
      int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 월의 말일 구하기
      
      HrDAO hdao = HrDAO.getInstance();
      ArrayList<HrVO> hrinfo = hdao.HrInfo(emp_num);
      String [] hr_checkin = new String[hrinfo.size()];
      String [] hr_checkout = new String[hrinfo.size()];

      int hrCount=0;
      
      //i랑 비교할 날짜, 달력에 표시할 시간.
      int [] startYear = new int[hrinfo.size()];
      int [] endYear = new int[hrinfo.size()];
      int [] startMonth = new int[hrinfo.size()];
      int [] endMonth = new int[hrinfo.size()];
      int [] startDay = new int[hrinfo.size()];
      int [] endDay = new int[hrinfo.size()];

      
      String [] cal_checkin = new String[hrinfo.size()];
      String [] cal_checkout = new String[hrinfo.size()];
      
         if( hrinfo != null) {
            for( int i = 0; i < hrinfo.size(); i++) {
               hr_checkin[i] = hrinfo.get(i).getHr_checkin();
               hr_checkout[i] = hrinfo.get(i).getHr_checkout();
               
               startYear[i] = Integer.parseInt(hr_checkin[i].substring(0,4));
               if(hr_checkout[i] != null) {
            	   endYear[i] = Integer.parseInt(hr_checkout[i].substring(0,4));
               }else {
            	   endYear[i] = startYear[i];
               }
               
               
               startMonth[i] = Integer.parseInt(hr_checkin[i].substring(5,7));
               if(hr_checkout[i] != null)
            	   endMonth[i] = Integer.parseInt(hr_checkout[i].substring(5,7));
               else
            	   endMonth[i] = startMonth[i];
               
               startDay[i]= Integer.parseInt(hr_checkin[i].substring(8,10));
               if(hr_checkout[i] != null)
            	   endDay[i]= Integer.parseInt(hr_checkout[i].substring(8,10));
               else
            	   endDay[i] = startDay[i];
               
               //달력 내용
               cal_checkin[i] = hr_checkin[i].substring(11,16);
               if(hr_checkout[i] != null)
            	   cal_checkout[i] = hr_checkout[i].substring(11,16);
               else
            	   cal_checkout[i] = "";

               hrCount=i+1;
            }
            request.setAttribute("startYear", startYear);            
            request.setAttribute("startMonth", startMonth);            
            request.setAttribute("startDay", startDay);   

            request.setAttribute("cal_checkin", cal_checkin);   
         
         request.setAttribute("endYear", endYear);
         request.setAttribute("endMonth", endMonth);
         request.setAttribute("endDay", endDay);   
         request.setAttribute("cal_checkout", cal_checkout);
         request.setAttribute("hrCount", hrCount);

      }
      request.setAttribute("cal_year", cal_year);
      request.setAttribute("cal_month", cal_month);
       
      forward.setRedirect(false);
      forward.setNextPath("mainHR.vc");
      
      return forward;
   }

}