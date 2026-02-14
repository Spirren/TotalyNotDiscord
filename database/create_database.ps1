$PG_PATH = "c:\Program Files\PostgreSQL\18\bin\psql.exe"
$PASSWORD = "postgres"
$USER = "postgres"
$DATABASE = "postgres"

& $PG_PATH -U postgres -h 127.0.0.1 -c "CREATE DATABASE $DATABASE;"
& $PG_PATH -U postgres -h 127.0.0.1 -c "CREATE USER $USER WITH PASSWORD '$PASSWORD';"
& $PG_PATH -U postgres -h 127.0.0.1 -c "GRANT ALL PRIVILEGES ON DATABASE $DATABASE TO $USER;"


