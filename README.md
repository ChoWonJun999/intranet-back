# ODPIA Spring Boot Project

## 📌 프로젝트 개요

이 프로젝트는 Spring Boot를 기반으로 한 웹 애플리케이션으로, MariaDB를 사용하며 RESTful API를 제공합니다.

---

## ⚙️ 기술 스택

- **Backend:** Spring Boot 3.5.4
- **Language:** Java 21
- **Build Tool:** Gradle
- **Database:** MariaDB
- **ORM:** Spring Data JPA
- **Logging:** Logback
- **Lombok:** 코드 간결화를 위한 자동 코드 생성
- **Testing:** JUnit 5

---

## 📂 프로젝트 구조

```
project-root/
 ├── src/
 │   ├── main/
 │   │   ├── java/        # Java 소스 코드
 │   │   └── resources/   # 설정 파일(application.yml 등)
 │   └── test/            # 테스트 코드
 ├── build.gradle         # Gradle 빌드 스크립트
 └── settings.gradle      # Gradle 설정 파일
```

---

## 🔧 필수 설치 항목

- **Java 21 이상**
- **Gradle** (Wrapper 포함)
- **MariaDB** (서버 실행 필요)

---

## 📦 주요 Dependencies

- `spring-boot-starter-web` : Spring MVC 기반 REST API 제공
- `spring-boot-starter-data-jpa` : JPA를 활용한 데이터베이스 연동
- `mariadb-java-client` : MariaDB JDBC 드라이버
- `lombok` : Getter/Setter, Builder 등 자동 생성
- `spring-boot-configuration-processor` : 자동 구성 프로세서
- `spring-boot-starter-test` : JUnit 기반 테스트 환경

---

## 🚀 실행 방법

1. 프로젝트 Clone

```
git clone [repository-url]
```

2. Gradle Dependencies 설치

```
./gradlew build
```

3. 애플리케이션 실행

```
./gradlew bootRun
```

또는

```
java -jar build/libs/odpia-0.0.1-SNAPSHOT.jar
```

---

## 🧪 테스트 실행

```
./gradlew test
```

---

## 📌 추가 사항

- `application.yml` 또는 `application.properties`에서 DB 연결 정보(MariaDB URL, USER, PASSWORD) 설정 필요
- Lombok 사용을 위해 IDE에서 Lombok 플러그인 활성화 필요
