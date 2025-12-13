INSERT INTO external_api_config
(app_name, operation, url, http_method, auth_type, auth_header, auth_token)
VALUES
('CALENDLY',
 'FETCH_USERS',
 'https://api.calendly.com/users/me',
 'GET',
 'OAUTH2',
 'Authorization',
 'DUMMY_TOKEN');

