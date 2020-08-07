# Scaffold
A scaffold to build a web application. The tech stack includes `SpringBoot`, `FlyWay`, `docker`, `Junit5`.
Have big confidence that it the best practise about using SpringBoot to build an RESTFUL repo.

## Setup in local

### Prerequisites

- Java 11
- Docker

### Start within docker

```shell script
  auto/start
```

### Start without docker

1. Set up the database, use postgresql
2. Start via gradle:
    ```bash
    $ ./gradlew bootRun
    ```
3. Or, start in IDEA via `BootApplication`. Make sure you have configured the IDEA environment.

### Run all the tests within docker

```bash
$ ./auto/test
```

### Run all the tests without docker

```bash
$ ./gradlew clean check
```

## Database

### Deploy database

## Access API

## Build / Deployment

### Deployment

## Support

### Troubleshooting

### NewRelic
