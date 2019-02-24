Running inside docker
====================

Build the project

`gradlew build`

Create a bootable jar file
 
`gradlew bootJar`

 Create and start docker images

`docker-compose -f docker-compose.yml up --build`


API
===================
There are two implemented endpoints:
1) GET: `http://localhost:8080/node/{nodeID}/children`

Retrieves a list of children for `{nodeID}` 

2) PUT: `http://localhost:8080/node/{nodeID}/updateParent/{newParentID}`

Changes the parent of `{nodeID}` to `{newParentID}`