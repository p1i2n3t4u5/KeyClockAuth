For getting Access Tokens
-----------------------------
8080 port authorization  server is running
http://localhost:8080/auth/realms/my-realm/protocol/openid-connect/token

this should be a post call with url-encoded format

client_id   ----  my-client1
username    ----  user1
password    ----  password
granttype   ----  password

Logging in
------------------------------
http://localhost:8081/customers


Pass as header for accessing resource
----------------------------------------
Authorization   Bearer access_token 

access_token: token received from previous call