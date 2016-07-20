spool procedure.log

-- Create table
create global temporary table TESTAA
(
  TEST VARCHAR2(20)
)
on commit delete rows;

CREATE OR REPLACE PACKAGE TESTPACKAGE AS
  TYPE TEST_CURSOR IS REF CURSOR;
End Testpackage;

/
create or replace procedure QueryMonthTotal(queryYear in VARCHAR2,querytype in VARCHAR2,monthvalue_array out TESTPACKAGE.TEST_CURSOR ) IS
--定义变量
elecMonthValue VARCHAR2(15);
warmMonthValue VARCHAR2(15);
theyear VARCHAR(4);
thetype VARCHAR(6);
begintime VARCHAR(19);
endtime VARCHAR(19);
outsql VARCHAR2(100);
type c_cursor is ref cursor;
metermonthvalues c_cursor;
warmmonthvalues c_cursor;

BEGIN--变量赋初值
  theyear :=queryYear;
  thetype :=querytype;

  FOR i IN 1..12 LOOP--12个月的循环
    IF i<9 THEN  begintime:=theyear||'-0'||i||'-01 00:00:00'; endtime:=theyear||'-0'||(i+1)||'-01 00:00:00';
    ELSIF i=9 THEN begintime:=theyear||'-0'||i||'-01 00:00:00'; endtime:=theyear||'-10-01 00:00:00';
    ELSIF i=12 THEN begintime:=theyear||'-'||i||'-01 00:00:00'; endtime:=(theyear+1)||'-01-01 00:00:00';
    ELSE begintime:=theyear||'-'||i||'-01 00:00:00'; endtime:=theyear||'-'||(i+1)||'-01 00:00:00';
    END IF;
 -----分查询情况查询
  IF thetype='classi' THEN --查询的是分类
    OPEN metermonthvalues FOR--每个月份值(电)
    SELECT SUM(MAX(T.TOTAL_USED) - MIN(T.TOTAL_USED))
    FROM BS_TREATEDDATA T
 WHERE 
   t.elec_meter_id IN( SELECT e.meter_id FROM us_elecmeter_info e,us_physical_address p WHERE e.addr_id=p.addr_id AND E.IS_DISABLE='N'  AND e.addr_stat_area =0  AND E.IS_COUNT = 'N' AND p.level_code=103 )
   AND T.TREATE_DATE >= begintime
   AND T.TREATE_DATE <=endtime
 GROUP BY T.ELEC_METER_ID;
  LOOP FETCH
      metermonthvalues
      INTO elecMonthValue;
      EXIT WHEN metermonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||elecMonthValue||''')');
      END LOOP;
      CLOSE metermonthvalues;

     OPEN warmmonthvalues FOR--每个月份值(暖)
    SELECT SUM( MAX(t.total_used)-MIN(t.total_used)) FROM bs_treateddata t ,us_warmmeter_info w,us_physical_address p
    WHERE t.warm_meter_id=w.meter_id AND w.addr_id=p.addr_id AND p.level_code=103 AND t.treate_date>=begintime AND t.treate_date<=endtime GROUP BY t.warm_meter_id;
    LOOP FETCH
      warmmonthvalues
      INTO warmMonthValue;
      EXIT WHEN warmmonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||warmMonthValue||''')');
      END LOOP;
      CLOSE warmmonthvalues;

      ELSE --查询的是分项
        OPEN metermonthvalues FOR--每个月份值(照明插座)
    SELECT SUM( MAX(t.total_used)-MIN(t.total_used)) FROM bs_treateddata t ,us_elecmeter_info e,us_physical_address p
    WHERE t.elec_meter_id=e.meter_id AND e.addr_id=p.addr_id AND p.level_code=103 AND E.IS_DISABLE='N' AND E.IS_COUNT = 'Y'   AND
     (E.METER_MEASURE_ID = '00000000000000000000000000000101' OR E.METER_MEASURE_ID = '00000000000000000000000000000102'
      OR E.METER_MEASURE_ID = '00000000000000000000000000000103' OR E.METER_MEASURE_ID = '00000000000000000000000000000104') 
       AND t.treate_date>=begintime AND t.treate_date<=endtime GROUP BY t.elec_meter_id;
  LOOP FETCH
      metermonthvalues
      INTO elecMonthValue;
      EXIT WHEN metermonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||elecMonthValue||''')');

      END LOOP;
      CLOSE metermonthvalues;

       OPEN metermonthvalues FOR--每个月份值(空调通风)
    SELECT SUM( MAX(t.total_used)-MIN(t.total_used)) FROM bs_treateddata t ,us_elecmeter_info e,us_physical_address p
    WHERE t.elec_meter_id=e.meter_id AND e.addr_id=p.addr_id AND p.level_code=103 AND E.IS_DISABLE='N' AND E.IS_COUNT = 'Y'   AND
     (E.METER_MEASURE_ID = '00000000000000000000000000000201' OR E.METER_MEASURE_ID = '00000000000000000000000000000202'
      OR E.METER_MEASURE_ID = '00000000000000000000000000000203')
       AND t.treate_date>=begintime AND t.treate_date<=endtime GROUP BY t.elec_meter_id;
  LOOP FETCH
      metermonthvalues
      INTO elecMonthValue;
      EXIT WHEN metermonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||elecMonthValue||''')');

      END LOOP;
      CLOSE metermonthvalues;
       OPEN metermonthvalues FOR--每个月份值(动力用电)
    SELECT SUM( MAX(t.total_used)-MIN(t.total_used)) FROM bs_treateddata t ,us_elecmeter_info e,us_physical_address p
    WHERE t.elec_meter_id=e.meter_id AND e.addr_id=p.addr_id AND p.level_code=103 AND E.IS_DISABLE='N' AND E.IS_COUNT = 'Y'  AND
     (E.METER_MEASURE_ID = '00000000000000000000000000000301' OR E.METER_MEASURE_ID = '00000000000000000000000000000302'
      OR E.METER_MEASURE_ID = '00000000000000000000000000000303' OR E.METER_MEASURE_ID = '00000000000000000000000000000304')
       AND t.treate_date>=begintime AND t.treate_date<=endtime GROUP BY t.elec_meter_id;
  LOOP FETCH
      metermonthvalues
      INTO elecMonthValue;
      EXIT WHEN metermonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||elecMonthValue||''')');

      END LOOP;
      CLOSE metermonthvalues;
       OPEN metermonthvalues FOR--每个月份值(特殊用电)
    SELECT SUM( MAX(t.total_used)-MIN(t.total_used)) FROM bs_treateddata t ,us_elecmeter_info e,us_physical_address p
    WHERE t.elec_meter_id=e.meter_id AND e.addr_id=p.addr_id AND p.level_code=103 AND E.IS_DISABLE='N' AND E.IS_COUNT = 'Y'  AND
     (E.METER_MEASURE_ID = '00000000000000000000000000000401' OR E.METER_MEASURE_ID = '00000000000000000000000000000402'
      OR E.METER_MEASURE_ID = '00000000000000000000000000000403') AND t.treate_date>=begintime AND t.treate_date<=endtime GROUP BY t.elec_meter_id;
  LOOP FETCH
      metermonthvalues
      INTO elecMonthValue;
      EXIT WHEN metermonthvalues%NOTFOUND;
      --插入临时表
      Execute Immediate ('insert into  TESTaa  values('''||elecMonthValue||''')');

      END LOOP;
      CLOSE metermonthvalues;
      END IF;

      END LOOP;
      outsql:= 'select * from TESTaa';--返回的游标

open monthvalue_array for outsql;

end QueryMonthTotal;


/


commit;
spool off