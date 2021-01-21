create table GW_VC(
	EMP_NUM varchar2(20) primary key,
	VC_START_DATE date,
	VC_END_DATE date,
	VC_CONTENT varchar2(500),
	VC_TATILE varchar2(20)
);

drop table GW_VC;
create table GW_HR(
	EMP_NUM varchar2(20) primary key,
	HR_CHECK date,
	HR_CHECKOUT date
);

