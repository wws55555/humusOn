# humusOn
오더관리 시스템의 api를 만들었습니다.
대표적인 기능으로 4가지가 있으며, 차례대로 소개해보자면,
1. 주문저장
2. 외부로 주문전송
3. 주문id로 주문조회
4. 주문리스트 조회
입니다.

사용기술스택은 JAVA, 스프링프레임워크이며, ORM방식인 JPA를 사용했고, DB는 H2를 사용했습니다.

특이사항을 나열하자면,
1. 에러처리를 위해 프론트쪽에서 오는 request를 validation check를 하였습니다.
2. 로직 중간마다 직접 커스터마이징한 exception을 만들어서, globalExceptionHandler로 예외처리하였습니다.
3. 주문전송을 위해 WebClient를 이용하였습니다.
4. 테스트는 서비스계층은 통합테스트 하였고, 컨트롤러계층이나 WebClient의 경우 단위테스트를 하기위해 mockito와 mockserver를 이용하였습니다.





