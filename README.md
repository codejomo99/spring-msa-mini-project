# MSA Mini Project

## 🏗 프로젝트 개요
이 프로젝트는 **MSA(Microservices Architecture)** 기반으로 설계된 **주문(Order) - 상품(Product) 서비스**입니다.  
**FeignClient**와 **Eureka**를 사용하여 마이크로서비스 간의 통신을 관리하고,  
**JWT 인증** 및 **Gateway Custom Filter**를 적용하여 보안성을 강화하였습니다.

## 🛠 기술 스택
- **Spring Boot**
- **Spring Cloud Eureka** - 서비스 디스커버리 및 로드 밸런싱
- **Spring Cloud Gateway** - API Gateway 및 JWT 인증 필터 적용
- **Spring Security & JWT** - 인증 및 보안
- **Spring Data JPA & QueryDSL** - 데이터 검색 및 페이징 처리
- **Zipkin** - 분산 추적(Distributed Tracing)
- **FeignClient** - 마이크로서비스 간 RESTful API 호출
- **H2 / MySQL** - 데이터 저장

## 📌 프로젝트 구조
```
msa-mini-project/
│── auth-service/      # 인증 서비스 (JWT 기반 인증)
│── gateway-service/   # API 게이트웨이 (Custom JWT 필터 적용)
│── order-service/     # 주문 관리 서비스 (FeignClient 사용)
│── product-service/   # 상품 관리 서비스 (FeignClient 사용)
│── server/  # Eureka 서비스 디스커버리
└── README.md          # 프로젝트 문서
```

## 🚀 주요 기능
### ✅ **1. 마이크로서비스 구성**
- `auth-service`: JWT를 활용한 인증 서비스
- `gateway-service`: 모든 API 요청을 통합 관리 (JWT 필터 적용)
- `order-service`: 주문 관련 기능 제공 (상품 정보 조회, 주문 생성 등)
- `product-service`: 상품 관련 기능 제공 (검색, CRUD)
- `server`: Eureka를 활용한 서비스 디스커버리

### ✅ **2. 인증 및 보안**
- **JWT 기반 인증** (`auth-service`에서 JWT 발급)
- **Spring Security 적용** (`gateway-service`에서 JWT 검증)
- **Custom JWT Filter** (`gateway-service`에서 모든 요청 필터링)

### ✅ **3. 서비스 간 통신**
- **FeignClient**를 사용하여 `order-service`에서 `product-service`로 상품 정보 요청
- `order-service`에서 `product-service`의 재고를 감소시키는 API 호출

```java
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponseDto getProduct(@PathVariable("id") Long id);

    @GetMapping("/products/{id}/reduceQuantity")
    void reduceProductQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);
}
```

### 🏆 마무리

이 프로젝트는 MSA 아키텍처의 기본 개념을 익히고, 마이크로서비스 간의 통신 및 보안 적용을 경험하는 것을 목표로 합니다.
추후 성능 개선 및 추가 기능을 점진적으로 적용할 계획입니다.
