## Filtering matches

Filtering presents an interesting example on how to query geo location positions with a Spring Boot + MongoDB powered backend, and how to present those values in a Spring boot + Thymeleaf frontend. 

## Requirements
- latest docker version

### Running
- `git clone` it
- On root folder, run `docker-compose up --build` on command line
- Point browser to http://localhost:8080 and check the filtering interface
- The default filter values, if used, will provide visible results, but feel free to input other values

### Architecture
1. `filtering-matches-backend` is a Spring Boot + In memory MongoDB web application, providing data through a REST api. The database is loaded with an initial fixture file and coordinated from inside the `Dockerfile`. Could be enhanced with a proper separated MongoDb instance, and with the proper database optimization and indexing. 
2. `filtering-matches-frontend` is a Spring Boot + Thymeleaf web application, consuming data from `filtering-matches-backend` and rendering it with `Thymeleaf` templates. I chose this stack because I am not a frontend engineer, and Thymeleaf is backend-friendly :)

### Questions and help
If something does not work as expected, please contact me on github. 



 
