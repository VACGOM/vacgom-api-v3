> kakao X goorm 2024 벚꽃톤 최우수상 수상 서비스 🏆<br>
> kakao X goorm 2024 구름톤유니브 In Jeju 우수상 수상작 🏆<br>
> kakao X goorm [DEEPDIVE 우수사례 소개](https://deepdive.goorm.io/4f838374-1a46-4f2d-852b-3915b336c76c)<br>
> [백곰 한국경제 신문 보도](https://www.hankyung.com/article/202404260197i)

<br>
<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/153f2cdc-b9d1-43ea-b3f1-266697450920" height=200px>
  <img src="https://github.com/user-attachments/assets/abbc86e2-2aa6-4f84-999a-049d8e0dd73f" height=200px>
</div>

<br>

# 💉 종합 백신 관리 서비스 : 백곰 🐻‍❄️

<백곰> 🐻‍❄️ 백엔드 리팩토링 Repository<br>
2025년 01월 실제 앱 서비스로 배포 예정<br>
벚꽃톤 이후 개발 사항은 [백곰 Organization](https://github.com/VACGOM)을 참고해주세요.

- 백곰은 kakaocloud의 k8s 환경을 지원 받아, 해당 서버 기반으로 구동되고 있습니다.
- 백곰은 HectoData로부터 codef API를 지원받고 있습니다.

![](https://github.com/goormthon-Univ/2024_BEOTKKOTTHON_TEAM_4_FE/assets/53892427/c89039ca-a866-452c-b372-79537abbc4d0)

<div align="right">
  <b>Vacgom | Powered By kakaocloud</b><br>
  Special Thanks to kakaocloud & goorm
</div>

### Framework & Library

<img src="https://skillicons.dev/icons?i=spring,java,kotlin" alt=""/>

- JDK 21
    - Java 21 기반의 엔티티 설계
    - Kotlin 기반의 비즈니스 로직 설계
- SpringBoot 3.3.3

### Database & ORM

<img src="https://skillicons.dev/icons?i=postgres,redis" alt=""/>

- PostgresSQL
- Redis
- Spring Data JPA
- QueryDSL

### System Architecture

- Multi Module Architecture를 적용하여 각 모듈별로 분리하여 개발하고, 빌드 및 배포를 진행
    - vacgom-api [Kotlin]
      Client와 연동하는 외부 API를 담당하는 모듈
    - vacgom-infra [Kotlin]
      Database, Infrastructure, Cloud Service와 연동하는 모듈
    - vacgom-domain [Java]
      도메인 로직을 담당하며, repository를 보호하는 모듈
        - CORE-CONFIG [Submodule]
          private submodule로, 외부에 노출할 수 없는 Property Configuration

### Infra

- AWS ECS
- Docker
- GitHub Action
