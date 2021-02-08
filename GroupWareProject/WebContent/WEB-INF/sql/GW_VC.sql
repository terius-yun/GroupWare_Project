  create table GW_HR(
   EMP_NUM varchar2(20),
   HR_CHECKIN TIMESTAMP(6),
   HR_CHECKOUT TIMESTAMP(6),
  constraint fk_HR_emp_num foreign key(emp_num) references gw_member(emp_num)
  );
  

drop table GW_HR;

  create table GW_VC(
   EMP_NUM varchar2(20),
   VC_START_DATE date,
   VC_END_DATE date,
   VC_CONTENT VARCHAR2(100),
  constraint fk_VC_emp_num foreign key(emp_num) references gw_member(emp_num)
  );

select*from GW_VC;

select*from GW_member;

COMMIT