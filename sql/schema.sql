-- create table
create table players (
id serial primary key,
team text,
name text,
salary text,
position text
);

create table team (
uid serial primary key,
team text
);

create table players (
uid serial primary key,
team text,
name text,
salary text,
position text,
team_id int not null references team(uid)
);

insert into team (team) values ('Command A');

-- add new player
insert into players (team_id, name, team, salary, position) values (1, 'Bobbie', 'Arizona', '325000.0', 'Catcher');
insert into players (team_id, name, team, salary, position) values (1, 'Marty', 'Arizona', '21000000.0', 'Pitcher');

-- select player + team
select players.name, players.team, players.salary, players.position, team.team from players
inner join team on team.id = players.team_id;
