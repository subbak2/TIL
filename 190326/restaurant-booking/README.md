# **Restaurant Booking Refactoring**

## **Restaurant Booking**
 - 레스토랑 예약 시스템

## **프로젝트 세부사항**
 - BookingScheduler 를 통해 시간대별 예약을 관리한다.
 - 예약은 정시에만 가능하다. (e.g. 09:00 → 가능, 09:03 → 불가능)
 - 시간대별 수용 가능한 인원을 정할 수 있다. (모든 시간대에 동일한 인원수 적용)
 - 일요일은 예약이 불가능하다.
 - 예약 완료 시 SMS를 발송한다.
 - 메일 주소가 있는 경우에는 메일을 발송한다.

## **Refactoring 실습**
 1. restaurant-booking 프로젝트 Clone 및 Import
 2. 레거시 코드에 대한 테스트 코드 작성
    - BookingScheduler 클래스의 테스트 커버리지 100% 달성
    - 다른 레거시 코드의 수정 없이 BookingSchedulerTest 클래스만 수정 가능
        - 단, setter 추가 등의 작은 수정은 가능
    - Mock 을 사용하지 않고 테스트 코드를 작성
 3. BookingScheduler 의 주석 처리된 부분을 해제한 후 테스트 코드 작성
    - BookingScheduler 클래스의 테스트 커버리지 100% 달성
 4. Mock 을 사용하여 테스트 코드 리팩토링
    - Mock Framework(Mockito) 을 적용하여 기존에 작성된 테스트 코드를 리팩토링
