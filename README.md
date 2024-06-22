This example workspace includes: 
- backend SpringBoot REST for biztrips
- maria db 
- phpMyAdmin
- frontend with create-react-app, shows the biztrips 

each part has his own Dockerfile 
all will bes started with docker compose 

- it is the start of our exercise to deploy the dockerized workspace to 
a cloud wich can be chosen!
- Example Pipeline from Bitbucket: Build, Publish and Run a Docker Image on Digital Ocean from Bitbucket Pipeline on Push
- Build, Publish and Run a Docker Image on Digital Ocean from Docker-Hub

---
## Was haben wir gemacht

### Github Action

### Backend

- Cross Origin durchreichen
- Dockerimage fixxen
- DB Url durchreichen und verarbeiten (Da es im falschen Format kommt)
- Postgres als DB nutzen
- Fly Konfiguration erstellen

### DB

- DB mithilfe von einem Command deployen
- DB mit App verlinken, sodass eine Env Variable mit dem Connection String eingefügt wird

### Frontend