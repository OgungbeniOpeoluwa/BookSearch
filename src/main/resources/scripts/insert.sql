truncate table user;
truncate table reading_books cascade;
truncate table persons;


INSERT into user (id,email,password,username) values(201,"test@gmail.com","mxor123" ,"ope_mia");

INSERT into persons (id,birth_year,death_year,name) values(200,"1908","1970" ,"Willams Shakespeare");




INSERT into reading_books (id,title,authors_id,user_id)
                      values(206,"My love life",
                           200,201);
