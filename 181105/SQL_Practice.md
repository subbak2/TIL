conn system/'password';
show user;
 create user 'username' identified by 'password';
conn 'username'/'password';
 grant connect,resource to 'username';
	--> Grant succeeded.
 select * from tab;