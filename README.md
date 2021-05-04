# Group project for the Systems For Design and Implementation course
Application for managing Actors, Movies, Directors and Roles, with operations for each one such as : **Add, Delete, Update, List, Filter and Report**, each implemented in a different environment. Each branch contains a different approach on implementing this app.

**Branches**
- **main** - REST Templates
	- Server - Client communication is done with controller endpoints that are accessed via **HTTP Methods** (**GET, POST, PUT, DELETE**)
	- **JPA Repository** & **PostgreSQL** for data persistence
- **RMIFinal**
	- Server - Client communication is made with **Remote Method Invocation**
	- Repositories are connected to the Database
- **RMI-JPA-Final**
	- Server - Client communication is done by **Remote Method Invocation**
	- Now repositories are made automatically with **JPA**
- **SocketFinal**
	- Simulates an RPC server
	- Server - Client communication is made via TCP messages containing the Called Method and the parameters
