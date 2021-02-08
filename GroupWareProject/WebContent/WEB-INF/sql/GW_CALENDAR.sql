--------------------------------------------------------
--  DDL for Table GW_CALENDAR
--------------------------------------------------------

  CREATE TABLE GW_CALENDAR
   (	CAL_TITLE VARCHAR2(20 BYTE), 
	CAL_MEMBER VARCHAR2(20 BYTE), 
	CAL_CONTENT VARCHAR2(300 BYTE), 
	CAL_START_DATE DATE, 
	CAL_END_DATE DATE, 
	EMP_NUM VARCHAR2(20 BYTE), 
	CAL_NUM VARCHAR2(20 BYTE) DEFAULT 1
   );
--------------------------------------------------------
--  Ref Constraints for Table GW_CALENDAR
--------------------------------------------------------
  ALTER TABLE GW_CALENDAR ADD FOREIGN KEY (EMP_NUM)
	  REFERENCES GW_MEMBER (EMP_NUM) ENABLE;

CREATE SEQUENCE cal_seq;