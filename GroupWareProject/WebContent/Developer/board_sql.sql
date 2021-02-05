insert into GW_BOARD VALUES(2,'test1','title','content',44,sysdate);
alter table gw_board drop primary key;
alter table gw_design_board add foreign key(emp_num) references gw_member(emp_num);

select t1.member_name,t2.BOARD_NUM, t2.BOARD_READCOUNT,
t2.BOARD_CONTENT,t2.BOARD_TITLE, t2.BOARD_WRITEDATE
from gw_member t1 inner join  GW_BOARD t2 on
t1.emp_num=t2.emp_num;

select * from GW_BOARD;

alter table gw_board
add PRIMARY key (board_num);

create table gw_design_board(
	design_num number(4)not null primary key,
	emp_num varchar2(40),	
	design_title varchar2(40),
	design_content varchar2(3000),
	design_readcount varchar2(40),
	design_writedate date default sysdate
);
drop table gw_board;

insert into gw_board values(
	1,'2','test1타이틀','test1content','test1카운트',sysdate
);


alter table gw_board add  board_file varchar2(100);

--1. 게시판 list를 나타내는 수식
select * from (select ROWNUM rnum,board_num,board_title,emp_num 
from (select * from gw_board order by board_num desc))
where rnum>=? and rnum<=?;

--2. 게시판 작성시 이름을 가져오기 위한 수식
select t1.member_name,t2.BOARD_NUM, t2.BOARD_READCOUNT,
t2.BOARD_CONTENT,t2.BOARD_TITLE, t2.BOARD_WRITEDATE, t2.board_file
from gw_member t1 inner join  GW_BOARD t2 on
t1.emp_num=t2.emp_num;

--3. 게시판에 있는 데이터 값
insert into GW_BOARD values( 1,'gd0','titel02','content','readcount',sysdate,'');
insert into gw_board values(3,'gd1','title03','content03','readcount03',SYSDATE,'');

select * from 
(select rownum rnum,t2.board_num, t2.board_title, t1.member_name
from gw_member t1 inner join gw_board t2 on t1.emp_num=t2.emp_num order by t2.board_num desc)
WHERE rnum>=1 and rnum<=3;

--4. 칼럼 추가 file
alter table GW_DESIGN_BOARD add gw_DESIGN_file varchar2(100);
alter table GW_DEVELOPER_BOARD add gw_DEVELOPER_file varchar2(100);
alter table GW_NOTICE_BOARD add gw_NOTICE_file varchar2(100);
alter table GW_PLAN_BOARD add gw_PLAN_file varchar2(100);

 select m.member_administrator from GW_MEMBER m inner join gw_board b on m.emp_num=b.emp_num where member_administrator='2' or member_administrator='3';


