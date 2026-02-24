
$PG_PATH = "c:\Program Files\PostgreSQL\18\bin\psql.exe"

$SQL_FILE = "C:\Users\alfre\Documents\Skola\OOA\new\TotalyNotDiscord\server\database\databasesetup\runsetup.sql"
$PASSWORD = "7410"
$USER = "postgres"
$DATABASE = "postgres"

& $PG_PATH -f $SQL_FILE postgresql://${USER}:${PASSWORD}@127.0.0.1:5432/$DATABASE