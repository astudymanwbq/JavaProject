--秒杀执行存储过程
DELIMITER $$ --console;转换为$$
--定义存储过程
--参数：in 输入参数 ；out 输出参数
--row_count（）返回上一条修改类型sql（delete，update insert）的影响行数
--row-count：0：未修改数据 >0表示修改的行数 <0sql错误、未执行修改sql
create procedure `seckill`.`execute_seckill1`
  (in v_seckill_id bigint,in v_phone bigint,
    in v_kill_time TIMESTAMP ,out r_result int)
BEGIN
  DECLARE insert_count int DEFAULT 0;
  start TRANSACTION ;
  INSERT ignore into success_killed
      (seckill_id,user_phone,create_time)
  VALUES (v_seckill_id,v_phone,v_kill_time);
  select ROW_COUNT() into insert_count;
  if(insert_count=0) THEN
      ROLLBACK ;
  set r_result=-1;
  elseif(insert_count<0) THEN
      ROLLBACK ;
      SET  r_result=-2;
  ELSE
  UPDATE  seckill
  SET  number=number-1
  WHERE  seckill_id=v_seckill_id
  and end_time>v_kill_time
  and start_time<v_kill_time
  and number>0;
SELECT ROW_COUNT() into insert_count;
if(insert_count=0) THEN
ROLLBACK ;
set r_result=0;
elseif(insert_count<0) THEN
ROLLBACK ;
set r_result=-2;
ELSE
  COMMIT ;
  set r_result=1;
END if;
end if;
END ;
$$
--存储过程定义结束
DELIMITER ;
set @r_result=-3;
--执行存错过程
call execute_seckill(1003,12345678901,now(),@r_result);
--获取结果
SELECT @r_result;

--存储过程优化 ：事务行级锁持有的时间
--不要过度依赖存储过程
--qps:一个秒杀6000/qps