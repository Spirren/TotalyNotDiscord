CREATE OR REPLACE FUNCTION notify_new_message() 
RETURNS trigger AS $$
BEGIN
  -- We use json_build_object to turn multiple numbers into ONE string
  -- The ::text at the end makes it compatible with pg_notify
  PERFORM pg_notify(
    'new_message', 
    NEW.chatId || '|' || NEW.messageIndex::text
  );
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;