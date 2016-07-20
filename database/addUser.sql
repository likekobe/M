
spool addUser.log
-- Create the user 
create user ecms
  identified by "1"
  default tablespace ECMS_DATA1
  temporary tablespace ECMS_TEMP;

GRANT SYSDBA TO ecms ;
GRANT "DBA" TO ecms ;

spool off