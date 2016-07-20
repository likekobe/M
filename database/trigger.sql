/
create or replace trigger after_comid_update
  after update on us_zwj
  for each row
declare

  cursor c_name is SELECT c.gateway_id  FROM us_com_infor c WHERE c.id=:new.Pk_Com;
  cursor g_name is SELECT c.gateway_id  FROM us_udp_info c WHERE c.pk_udp_info=:new.pk_Udp_Info;
  newgatewayid CHAR(32);
BEGIN

  UPDATE us_elecmeter_info e SET e.com_id=:new.PK_COM WHERE e.mid_machine_id=:OLD.PK_ZWJ;
  UPDATE us_elecmeter_info e SET e.pk_udp_info=:new.Pk_Udp_Info WHERE e.mid_machine_id=:old.Pk_Zwj;

  OPEN c_name;
  LOOP
    FETCH c_name INTO newgatewayid;
    EXIT WHEN c_name%NOTFOUND;
    IF newgatewayid IS NOT NULL
    THEN
      UPDATE us_elecmeter_info e SET e.gateway_id=newgatewayid WHERE e.mid_machine_id=:OLD.PK_ZWJ;
      END IF;
  END LOOP;
  CLOSE c_name;
   OPEN g_name;
  LOOP
    FETCH g_name INTO newgatewayid;
    EXIT WHEN g_name%NOTFOUND;
    IF newgatewayid IS NOT NULL THEN

      UPDATE us_elecmeter_info e SET e.gateway_id=newgatewayid WHERE e.mid_machine_id=:OLD.PK_ZWJ;
      END IF;
  END LOOP;
  CLOSE g_name;
end after_comid_update;
/