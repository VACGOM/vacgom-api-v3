백곰 앱 개발 프로젝트 백엔드 Repository 입니다.

- PM : Elly
- FE : Garden, Amy
- BE : Dan
- APP / MODULE : Jude

## [ Contents ]

- [Version Control](#version-control)
- [Tech Stack](#tech-stack)
    - [Framework & Library](#framework--library)
    - [Build tool](#build-tool)
    - [Infra](#infra)

## Version Control

| Version # | Revision Date | Description                        | Author |
|:---------:|:-------------:|:-----------------------------------|:------:|
|  v0.0.1   |  2024.09.12   | Vacgom Refactoring Project init    |  Dan   |
|  v0.0.2   |  2024.09.14   | DB Migration (MySQL -> PostgreSQL) |  Dan   |
|  v0.0.3   |  2024.09.14   | MSA Architecture 적용                |  Dan   |

## Tech

### Framework & Library

- JDK 21
- SpringBoot 3.3.3

### Database

- PostgreSQL
- Redis

### System Architecture

- Multi Module Architecture를 적용하여 각 모듈별로 분리하여 개발하고, 빌드 및 배포를 진행
    - vacgom-api [Kotlin] : Client와 연동하는 외부 API를 담당하는 모듈
    - vacgom-infra [Kotlin] : Database, Infrastructure, Cloud Service와 연동하는 모듈
    - vacgom-domain [Java] : 도메인 로직을 담당하며, repository를 보호하는 모듈

### Infra

- AWS ECS
- Docker
- GitHub Action
