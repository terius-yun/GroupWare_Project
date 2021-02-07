--gw_board 테이블
create table gw_board(
   board_num number(4)not null primary key,
   emp_num varchar2(40),   
   board_title varchar2(40),
   board_content varchar2(3000),
   board_readcount varchar2(40),
   board_writedate date default sysdate,
   board_file varchar2(100)
);
--gw_design_board 테이블
create table gw_design_board(
   design_num number(4)not null primary key,
   emp_num varchar2(40),   
   design_title varchar2(40),
   design_content varchar2(3000),
   design_readcount varchar2(40),
   design_writedate date default sysdate,
   gw_design_file varchar2(100)
);
--gw_developer_board 테이블
create table gw_developer_board(
   developer_num number(4)not null primary key,
   emp_num varchar2(40),   
   developer_title varchar2(40),
   developer_content varchar2(3000),
   developer_readcount varchar2(40),
   developer_writedate date default sysdate,
   gw_developer_file varchar2(100)
);
--gw_plan_board 테이블
create table gw_plan_board(
   plan_num number(4)not null primary key,
   emp_num varchar2(40),   
   plan_title varchar2(40),
   plan_content varchar2(3000),
   plan_readcount varchar2(40),
   plan_writedate date default sysdate,
   gw_plan_file varchar2(100)
);

alter table gw_board add foreign key(emp_num) references gw_member(emp_num);
alter table gw_DESIGN_board add foreign key(emp_num) references gw_member(emp_num);
alter table gw_DEVELOPER_board add foreign key(emp_num) references gw_member(emp_num);
alter table gw_PLAN_board add foreign key(emp_num) references gw_member(emp_num);
