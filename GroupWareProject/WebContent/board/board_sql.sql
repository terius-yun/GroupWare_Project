insert into GW_BOARD VALUES(2,'test1','title','content',44,sysdate);
alter table gw_board drop primary key;
alter table gw_board add foreign key(emp_num) references gw_member(emp_num);

select t1.member_name,t2.BOARD_NUM, t2.BOARD_READCOUNT,
t2.BOARD_CONTENT,t2.BOARD_TITLE, t2.BOARD_WRITEDATE
from gw_member t1 inner join  GW_BOARD t2 on
t1.emp_num=t2.emp_num;

select * from GW_BOARD;

alter table gw_board
add PRIMARY key (board_num);

create table gw_board(
	board_num number(4)not null primary key,
	emp_num varchar2(40),	
	board_title varchar2(40),
	board_content varchar2(3000),
	board_readcount varchar2(40),
	board_writedate date default sysdate
);
drop table gw_board;

insert into gw_board values(
	1,'2','test1타이틀','test1content','test1카운트',sysdate
);


alter table gw_board add  board_file varchar2(100);
