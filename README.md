Java Challenge

To run, a simple `mvn spring-boot:run ` will start up the microservice. (Slight warning before hand that 200k data will be loaded into the db, may take up to 30second to run, sorry about that!)
  
To test it out, Postman is required. There is a postman collection in the project directory which can be imported into postman to test.  
Steps to run Postman:  
1) Create an account or log in using google.  
https://www.postman.com/postman/workspace/postman-public-workspace/documentation/12959542-c8142d51-e97c-46b6-bd77-52bb66712c9a  
  
3) Create a new workspace  
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/7cdcfe55-c97e-40a9-a43f-7047e5902e5b)    
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/fa5b4f55-285a-49f3-b479-558b7bc35fae)    
  
4) Import collection  
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/1a9aead0-2f8e-4d3d-877a-a2c01e5dd9ab)    
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/31521146-92d1-4047-a95a-250f47d7072c)  
  
5) Happy running the API's!  
  
    
API's :      
POST _/v1/jchallenge/distance_   
*(Authentication not needed for this API)*  
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/65ae6b7c-9aa9-4065-9f20-1884499ca293)  
  
PATCH _/v1/jchallenge/postal_    
*(Basic Auth is needed for this API, user and password is "admin")*  
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/a5347d2f-fbc8-49b2-a8b8-2f9c8e257ae9)  
  
GET _/v1/jchallenge/postal_    
*(Basic Auth is needed for this API, user and password is "admin")*  
![image](https://github.com/ahmadizzaz/javachallenge/assets/36722847/b28b282d-6c18-45b6-950c-3eb5e28c4b75)  
