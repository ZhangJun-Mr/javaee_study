delimiter //
create procedure demo_in(in p_int int)
  # alter procedure demo_in(inout p_int int)
  begin
    declare continue handler for not found set xxx = 1;
    declare i int;
    set i = 0;
    while i < 10000000 do
      insert into mypap (num, content)
      values
        (i, 'string;');
      set i = i + 1;
    end while;
    select p_int;
  end //
delimiter ;

call demo_in(1);
show procedure status like '%%';
delete from mypap;

delimiter //
create trigger after_myapp_update
  after update
  on mypap
  for each row

  begin
    insert into mypap_trigger_data(mpap_id, `option`, mpap_content) values (NEW.id, 'update', NEW.content);
  end //
delimiter ;

drop trigger after_myapp_update;

show triggers ;



