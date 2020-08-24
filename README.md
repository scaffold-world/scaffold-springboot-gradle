# Scaffold
A scaffold to build a web application. The tech stack includes `SpringBoot`, `FlyWay`, `docker`, `Junit5`.
Have big confidence that it the best practise about using SpringBoot to build an RESTFUL repo.

![CI/CD for master](https://github.com/Fatezhang/scaffold/workflows/CI/CD%20for%20master/badge.svg)

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
2. Make sure you have configured the IDEA environment. Configure `application-local.yml`. Edit the `Active profile` to `local`.
3. Start in IDEA via `BootApplication`.

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

Use docker to set up the postgresql database.

```shell script
# cd scaffold
export PROJECT=/<some-workspace>/scaffold
docker run -d -p 3306:3306 \
    --restart=always \
    --privileged=true \
    --name scaffold_db \
    --volume ${PROJECT}/mysql/conf:/etc/mysql \
    --volume ${PROJECT}/mysql/logs:/var/log/mysql \
    --volume ${PROJECT}/mysql/data:/var/lib/mysql \
    --volume ${PROJECT}/mysql/init/:/docker-entrypoint-initdb.d/ \
    --env MYSQL_USER=scaffold_rw \
    --env MYSQL_PASSWORD=password10 \
    --env MYSQL_ROOT_PASSWORD=123456 \
    --env MYSQL_DB=scaffold_db \
  mysql:5.7
```

## Access API

```shell script
curl -v http://localhost:8080
```

## Build / Deployment

### Deployment

Use git actions to trigger the CI/CD, see [Github action](https://github.com/Fatezhang/scaffold/actions)

## Support

### Troubleshooting

See [Troubleshooting](./.docs/TroubleShooting.md)

### NewRelic

N/A
