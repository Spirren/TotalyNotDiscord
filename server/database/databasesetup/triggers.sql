CREATE TRIGGER message_trigger
AFTER INSERT ON Messages
FOR EACH ROW
EXECUTE FUNCTION notify_new_message();