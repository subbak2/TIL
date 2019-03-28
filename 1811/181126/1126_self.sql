--1. 문제) EMP Table에서 이름, 급여, 커미션 금액, 총액(sal + comm)을
-- 구하여 총액이 많은 순서로 출력하라. 단, 커미션이 NULL인
-- 사람은 제외한다.

SELECT  ENAME, SAL, COMM, SAL+COMM
FROM    EMP
WHERE   COMM IS NOT NULL ;

--2. 문제) 10번 부서의 모든 사람들에게 급여의 13%를 보너스로 지불하기로
-- 하였다. 이름, 급여, 보너스 금액, 부서번호를 출력하라.

SELECT  ENAME AS 이름, SAL AS 급여, SAL*0.13 AS 보너스, DEPTNO AS 부서번호
FROM    EMP
WHERE   DEPTNO = 10;

--3. 문제) 30번 부서인 사원들의 연봉을 계산하여 이름, 부서번호, 급여, 연봉을
-- 출력하라. 단, 연말에 급여의 150%를 보너스로 지급한다.

SELECT  ENAME 이름, DEPTNO 부서번호, SAL 급여, SAL*13.5 연봉 
FROM    EMP
WHERE   DEPTNO = 30;

--4. 문제 ) 부서번호가 20인 부서의 시간당 임금을 계산하여 출력하라.
--단, 1달의 근무일수는 20일이고, 1일 근무시간은 5시간이다.
--출력양식은 이름, 급여, 시간당 임금(소수이하 첫 번째 자리
-- 에서 반올림)을 출력하라

SELECT  ENAME 이름, SAL 급여,ROUND(SAL/100) "시간당 임금"
FROM    EMP
WHERE   DEPTNO=20;

--5. 문제) 급여가 $1,500부터 $3,000 사이의 사람은 급여의 15%를 회비로
-- 지불하기로 하였다. 이를 이름, 급여, 회비(십단위 자리에서
-- 반올림)를 출력하라.

SELECT  ENAME 이름, SAL 급여, ROUND(SAL*0.15,-2) 회비
FROM    EMP
WHERE   SAL BETWEEN 1500 AND 3000;


--6. 문제) 급여가 $2,000 이상인 모든 사람은 급여의 15%를 경조비로 내기
-- 로 하였다. 이름, 급여, 경조비(소수점 이하 절삭)를 출력하라.

SELECT  ENAME 이름, SAL 급여, TRUNC(SAL*0.15) 경조비
FROM    EMP
WHERE   SAL>=2000;

--7. 문제) 사원의 부서번호, 이름, 입사
-- 일, 현재일, 근무일수(소수점 이하 절삭), 근무년수(소수이하 1자리로, 반올림), 근무개월수
--(한달 30일 기준,소수점 이하 절삭), 근무주수(소수점 이하 절삭)를 출력하라.

SELECT  DEPTNO 부서번호,
ENAME 이름, 
HIREDATE 입사일,
SYSDATE 현재일,
trunc(sysdate - HIREDATE) 근무일수,
extract(year from sysdate) - extract(year from hiredate) 근무년수, 
trunc(months_between(sysdate,hiredate),0) 근무개월수
FROM    EMP;

--8. 문제) 모든 사원의 실수령액을 계산하여 출력하라. 단, 급여가 많은
-- 순으로 이름, 급여, 실수령액을 출력하라.(실수령액은 금여에
-- 대해 10%의 세금을 뺀 금액)

SELECT  ENAME 이름, SAL 급여, SAL*0.9 실수령액
FROM    EMP
ORDER BY SAL DESC;


--9. 문제) 사원이름, 입사일, 입사일로부터 90일이 지난 후의 날, 급여를 출력하라.
SELECT  ENAME 사원이름, HIREDATE 입사일, HIREDATE+90 "입사 90일 후", SAL 급여
FROM    EMP;

--10. 문제) 사원의 입사일, 입사일로부터 6개월이 지난 후의 날짜, 급여
-- 를 출력하라

SELECT  HIREDATE 입사일, ADD_MONTHS(HIREDATE,6) "입사일로부터 6개월 후", SAL 급여
FROM    EMP;

--11. 문제) 모든 사원의 입사일로부터 60일이 지난 후의 월요일은 몇 년,몇 월,
--몇 일 인가를 구하여 이름, 입사일,’MONDAY’를 출력하라

SELECT  ENAME 이름 , HIREDATE 입사일, NEXT_DAY(HIREDATE+60,1) "MONDAY"
FROM    EMP;

--12. 문제) 입사일을 ‘1996년 5월 14일’의 형태로 이름, 입사일을 출력
-- 하라.

SELECT  TO_CHAR(HIREDATE,'FMYYYY"년" MM"월" DD"일"') 입사일
FROM    EMP;

--13. 문제) 이름의 글자수가 6자 이상인 사람의 이름을 앞에서 3자만 구하
-- 여 소문자로 이름만을 출력하라.

SELECT  SUBSTR(LOWER(ENAME),1,3)
FROM    EMP
WHERE   ENAME LIKE '%______%';


--14. 문제) 10번 부서의 평균급여, 최고급여, 최저급여, 인원수를 구하여 출력하라.

SELECT      ROUND(AVG(SAL),0) 평균급여 , MAX(SAL) 최고급여, MIN(SAL) 최저급여, COUNT(*) 인원수
FROM        EMP
WHERE       DEPTNO=10
GROUP BY    DEPTNO;

--15. 문제) 각 부서별 부서번호,평균급여, 최고급여, 최저급여, 인원수를 구하여 출력하라.

SELECT      DEPTNO, ROUND(AVG(SAL),0) 평균급여 , MAX(SAL) 최고급여, MIN(SAL) 최저급여, COUNT(*) 인원수
FROM        EMP
GROUP BY    DEPTNO;

--16. 문제) 각 부서별 같은 업무를 하는 사원들을 구하여 부서번호,
--업무명, 인원수를 출력하라.

SELECT      DEPTNO, JOB, COUNT(*)
FROM        EMP
GROUP BY    DEPTNO, JOB;

--17. 문제) 같은 업무를 하는 사람의 수가 3명 이상인 업무와 인원수를
-- 출력하라.

SELECT  JOB, COUNT(*)
FROM    EMP
GROUP BY JOB
HAVING  COUNT(*)>=3;

--18. 문제) 각 부서별 평균 월급, 전체 월급, 최고 월급, 최저 월급을 구하
-- 여 평균 월급이 많은 순으로 출력하라.

SELECT      ROUND(AVG(SAL)) "평균 월급", SUM(SAL) "전체 월급", MAX(SAL) "최고 월급", MIN(SAL) "최저 월급"
FROM        EMP
GROUP BY    DEPTNO
ORDER BY    AVG(SAL) DESC;

--19. 문제) 부서별 입사월별 평균급여를 아래 포맷으로 출력하라
--    1월   2월  3월 4월 5월 6월 7월 8월 9월 10월 11월 12월
-- 10 1300
-- 20
-- 30       1425
SELECT      DEPTNO, AVG("1월"), AVG("2월"), AVG("3월"), AVG("4월"), AVG("5월"), AVG("6월"), AVG("7월"), AVG("8월"), AVG("9월"), AVG("10월"), AVG("11월"), AVG("12월")
FROM        (SELECT DEPTNO, CASE WHEN extract(month from hiredate)=1 THEN SAL END "1월",
                            CASE WHEN extract(month from hiredate)=2 THEN SAL END "2월",
                            CASE WHEN extract(month from hiredate)=3 THEN SAL END "3월",
                            CASE WHEN extract(month from hiredate)=4 THEN SAL END "4월",
                            CASE WHEN extract(month from hiredate)=5 THEN SAL END "5월",
                            CASE WHEN extract(month from hiredate)=6 THEN SAL END "6월",
                            CASE WHEN extract(month from hiredate)=7 THEN SAL END "7월",
                            CASE WHEN extract(month from hiredate)=8 THEN SAL END "8월",
                            CASE WHEN extract(month from hiredate)=9 THEN SAL END "9월",
                            CASE WHEN extract(month from hiredate)=10 THEN SAL END "10월",
                            CASE WHEN extract(month from hiredate)=11 THEN SAL END "11월",
                            CASE WHEN extract(month from hiredate)=12 THEN SAL END "12월"
            FROM EMP
            )
GROUP BY    DEPTNO
ORDER BY    DEPTNO;

