# aceleradev-event-logger
Final project for Codenation's AceleraDev Java  
  
# Defaults (inserted by schema.sql)
OAuth's ClientId = publicid  
OAuth's ClientSecret = publicsecret  
  
Admin User:  
Username = suporte  
Password = 12345678
  
# User Management
User info (GET) = /account/me  
Registration (POST) = /account/register  
Deletion (POST) = /account/delete  
Access Token Request (POST) = /oauth/token  
  
Form-based login/logout endpoints:  
Login = /login  
Logout / logout
  
# API Endpoints
Lists multiple events, which can be filtered, but hiding logs (GET) = /v1/events  
(Filter available: event's properties and also page, size, sort, etc.)
GET/POST/PUT/PATCH/DEL event (identified by id) = /v1/events/id  
  
# Documentation
/swagger-ui.htlm
