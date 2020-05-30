# Spring Boot Parking

Simple parking rest api in Spring Boot

### Prerequisites

* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/install/)

### Running

* Up containers

```
docker-compose up -d
```

### Tests

Run on Intellij Idea or from command line:

```bash
./gradlew test
```

### Testing via curl

* Change parking place status:
```bash
curl -X POST -H "Content-Type: application/json" http://localhost:8065/api/v1/parking-place/change-status -d '{"id": 1, "status": "BUSY"}'
```

* Get list of available places:
```bash
curl -H "Content-Type: application/json" http://localhost:8065/api/v1/parking-place/
```

* Check has available places:
```bash
curl -H "Content-Type: application/json" http://localhost:8065/api/v1/parking-place/has-available/
```

## Troubleshooting

If port `8065` is busy on your local machine, change it in `docker-compose.yml` to another unused port and run `docker-compose up -d` again
 
