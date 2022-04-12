# idusProject
# 백패커/아이디어스 개발과제
- 회원 주문 서비스 개발 
## 목차
- [개발 환경](#개발-환경)
- [기능 요구사항](#기능-요구사항)
---

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA Ultimate
    - OS: Window
    - GIT
- Server (Base URI: `http://localhost:8082`)
    - Java11
    - Spring Boot 2.6.6
    - Spring Security 
    - JPA
    - H2
    - Gradle 6.8.3
    - Junit 4.1.2
    - gson 2.8.5
    - swagger
- SQL
    - H2 Database (MySQL)
## DBMS
### ERD 테이블
![image](https://user-images.githubusercontent.com/47424425/162865435-119914d3-3bb8-4086-91f5-cfe3a1c12772.png)

#### MEMBER 테이블
```
create table tb_member (
      email VARCHAR(100) not null,
      name VARCHAR(20) not null,
      password varchar(255) not null,
      phone_number VARCHAR(20) not null,
      role VARCHAR(10) not null,
      sex VARCHAR(10) not null,
      primary key (email)
  )
```
#### ORDER 테이블
```
create table tb_order (
      order_id bigint generated by default as identity,
      email VARCHAR(100) not null,
      name VARCHAR(20) not null,
      order_dt timestamp not null,
      product_name VARCHAR(100) not null,
      primary key (order_id)
  )
```
## 기능 요구사항
- [회원 가입 API](#회원-가입-API)
- [로그인 API](#로그인-API)
- [로그아웃 API](#로그아웃-API)
- [주문 API](#주문-API)
- [단일 회원 주문 목록 조회 API](#단일-회원-주문-목록-조회-API)
- [전체 회원 주문 목록 조회](#전체-회원-주문-목록-조회-API)
- [마지막 주문 정보 조회 API](#마지막-주문-정보-조회-API)
- [회원 정보 조회 API](#회원-정보-조회-API)
---
## 회원 가입 API
- 이메일, 이름, 별명, 패스워드, 전화번호, 성별을 입력 시 회원 가입을 진행한다.
- 비밀 번호의 경우 암호화 되어 저장된다.
  - 만약 중복된 이메일이 있다면 가입이 불가능하다.
- 제약 사항
  - 이름 : 한글, 영문 대소문자만 허용 
  - 별명 : 영어 소문자만 허용
  - 비밀번호 : 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함
  - 전화번호 : 숫자
  - 이메일 : 이메일 형식
## Request
- URI : (`http://localhost:8082/auth/signup`)
- Method : POST
```
{
  "email": "cjy9249@naver.com",
  "name": "cjy",
  "nickname": "cjy",
  "password": "cjy9249@CJY9249",
  "phoneNumber": "010112119",
  "sex": "female"
}
```
## Respone
- String
- 회원 가입 성공
```
{
   cjy92249@naver.com님 회원가입 되었습니다.
}
```
- 회원 가입 중복
```
{
   이미 존재하는 이메일 입니다.
}
```
## 로그인 API
- email, password로 로그인을 진행합니다.

## Request
- URI : (`http://localhost:8082/auth/login`)
- Method : POST
```
{
  "email": "cjy9249@naver.com",
  "password": "cjy9249@CJY9249"
}
````
## Respone
- String
- 응답코드 및 메시지를 가져옵니다.
    - 성공
    - 실패 
```
{
    cjy9249@naver.com 님 반갑습니다.
}
```
```
{
    자격 증명에 실패하였습니다.
}
```
## 로그아웃 API
- 로그 아웃을 진행합니다.
  - 만약 로그인 상태가 아니면 예외처리 하였습니다.  

## Request
- URI : (`http://localhost:8082/auth/logout`)
- Method : GET

## Response
- String
- 로그아웃 성공
- 로그아웃 실패
```
{
  로그아웃 되었습니다.
}
```
```
{
  로그인 해주세요.
}
```
## 주문 API
- 상품으로 이름으로 주문을 진행합니다.
- 로그인시 해당 이메일, 이름이 컬럼에 추가 됩니다.
- 오프라인시 익명으로 주문이 들어갑니다.
## Request
- URI : (`http://localhost:8082/order-product`)
- Method : POST
```
{
  "productName": "전기장판"
}
````
## Respone
- String
- 주문 완료
```
{
  anonymousUser 님 전기장판 주문이 완료 되었습니다.
}
```

## 단일 회원 주문 목록 조회 API
- 이메일 주소를 이용하여 단일 회원의 주문 목록을 조회합니다.
## Request
- URI : (`http://localhost:8082/order/info?email=이메일주소`)
- Method : GET
```
{
  "productName": "전기장판"
}
````
## Respone
- Json
- 조회 완료
```
{
  "data": [
    {
      "orderId": 1,
      "productName": "전기장판",
      "orderDt": "2022-04-12T11:36:41.687553",
      "name": "anonymousUser",
      "email": "anonymousUser@google.com"
    }
  ],
  "status": 200,
  "message": "불러오기 완료"
}
```
- 미조회
```
{
  "timestamp": "2022-04-12T02:38:42.219+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "com.cjy9249.orderShop.common.exception.BaseException: 해당하는 주문이 없습니다.\n\tat 
}
```

## 전체 회원 주문 목록 조회
- 전체 회원의 주문 목록을 조회합니다.
- 또한 페이징 처리하여 페이징 사이즈만큼 조회 됩니다.
## Request
- URI : (`http://localhost:8082/order/user-list`)
- Method : GET

## Respone
- Json
- 조회 완료
```
{
  "content": [
    {
      "orderId": 1,
      "productName": "전기장판",
      "orderDt": "2022-04-12T11:36:41.687553",
      "name": "anonymousUser",
      "email": "anonymousUser@google.com"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "empty": true,
      "unsorted": true
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "numberOfElements": 1,
  "number": 0,
  "first": true,
  "sort": {
    "sorted": false,
    "empty": true,
    "unsorted": true
  },
  "size": 10,
  "empty": false
}
```
## 마지막 주문 정보 조회 API
- 해당 회원의 마지막 주문 정보를 조회합니다.
## Request
- URI : (`http://localhost:8082/order/last-order`)
- Method : POST
```
{
  "email": "cjy9249@naver.com",
  "name": "cjy"
}
````
## Respone
- Json
- 주문 완료
```
  {
    "orderId": 11,
    "productName": "전기장판",
    "orderDt": "2022-04-12T11:51:25.868603",
    "name": "cjy",
    "email": "cjy9249@naver.com"
  }
```
## 마지막 주문 정보 조회 API
- 해당 회원의 마지막 주문 정보를 조회합니다.
## Request
- URI : (`http://localhost:8082/order/last-order`)
- Method : POST
```
{
  "email": "cjy9249@naver.com",
  "name": "cjy"
}
````
## Respone
- String
- 조회 완료
```
  {
    "orderId": 11,
    "productName": "전기장판",
    "orderDt": "2022-04-12T11:51:25.868603",
    "name": "cjy",
    "email": "cjy9249@naver.com"
  }
```
- 조회 미완료
```
{
  "timestamp": "2022-04-12T02:52:48.821+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "com.cjy9249.orderShop.common.exception.BaseException: 해당하는 주문이 없습니다.\n\tat
}
```

## 회원 정보 조회 API
- 해당 회원의 상세 정보를 조회합니다.
## Request
- URI : (`http://localhost:8082/member/info`)
- Method : GET

## Respone
- String
- 조회 완료
```
{
  "data": {
    "email": "cjy9249@naver.com",
    "name": "cjy",
    "password": "$2a$10$GuS.0z6DDjgndmFrVpVMTe5AsNqXrjW0woZ05u2EYH8HFzNYQfQn2",
    "phoneNumber": "010112119",
    "sex": "female",
    "role": "LOGINCHECK"
  },
  "status": 200,
  "message": "불러오기 완료"
}
```
- 조회 미완료
```
{
  "timestamp": "2022-04-12T02:54:28.635+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "com.cjy9249.orderShop.common.exception.BaseException: 존재하지 않는 회원입니다.
}
```
