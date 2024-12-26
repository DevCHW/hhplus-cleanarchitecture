drop table if exists lecture;
drop table if exists lecture_application;

create table lecture
(
    id                 bigint auto_increment           comment '특강 ID' primary key,
    title              varchar(255) not null           comment '특강 이름',
    lecturer           varchar(255) not null           comment '특강 ',
    lecture_datetime   timestamp(6) not null           comment '강의 시간',
    max_capacity       int          not null default 0 comment '최대 정원',
    application_count  int          not null default 0 comment '수강 인원',
    created_at         timestamp(6) not null           comment '생성 시점',
    updated_at         timestamp(6) not null           comment '마지막 수정 시점'
)
    comment '특강 정보';

create table lecture_application
(
    id               bigint auto_increment comment '특강 신청 ID' primary key,
    lecture_id       bigint         not null       comment '특강 ID',
    user_id          bigint         not null       comment '유저 ID',
    created_at       timestamp(6)   not null       comment '생성 시점',
    updated_at       timestamp(6)   not null       comment '마지막 수정 시점'
)
    comment '특강 신청 정보';