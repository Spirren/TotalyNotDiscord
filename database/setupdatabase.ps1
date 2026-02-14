
$PG_PATH = "c:\Program Files\PostgreSQL\18\bin\psql.exe"

$SQL_FILE = "C:\Users\nils\tda357\task1\runsetup.sql"
$PASSWORD = "postgres"
$USER = "postgres"
$DATABASE = "postgres"

& $PG_PATH -f $SQL_FILE postgresql://${USER}:${PASSWORD}@127.0.0.1:5433/$DATABASE