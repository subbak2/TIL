--1. 'SMITH'보다 월급을 많이 받는 사원들의 이름과 월급을 출력하라.
SELECT ENAME, SAL
FROM EMP
WHERE SAL>(SELECT SAL FROM EMP WHERE ENAME = 'SMITH');

--2. 10번 부서의 사원들과 같은 월급을 받는 사원들의 이름, 월급,
-- 부서번호를 출력하라.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE SAL IN (SELECT SAL FROM EMP WHERE DEPTNO=10);

--3. 'BLAKE'와 같은 부서에 있는 사원들의 이름과 고용일을 뽑는데
-- 'BLAKE'는 빼고 출력하라.
SELECT ENAME, HIREDATE
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'BLAKE')
AND ENAME != 'BLAKE';

--4. 평균급여보다 많은 급여를 받는 사원들의 사원번호, 이름, 월급을
-- 출력하되, 월급이 높은 사람 순으로 출력하라.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL)FROM EMP)
ORDER BY SAL DESC;

--5. 이름에 'T'를 포함하고 있는 사원들과 같은 부서에서 근무하고
-- 있는 사원의 사원번호와 이름을 출력하라.
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO IN (SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%T%');

--6. 30번 부서에 있는 사원들 중에서 가장 많은 월급을 받는 사원보다
-- 많은 월급을 받는 사원들의 이름, 부서번호, 월급을 출력하라.
--(단, ALL 또는 ANY 연산자를 사용할 것)
SELECT ENAME, EMPNO, SAL
FROM EMP
WHERE SAL > ALL(SELECT SAL FROM EMP WHERE DEPTNO=30);

--7. 'DALLAS'에서 근무하고 있는 사원과 같은 부서에서 일하는 사원의
-- 이름, 부서번호, 직업을 출력하라.
SELECT E.ENAME, D.DNAME, E.JOB
FROM EMP E
JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE D.LOC = 'DALLAS';

--8. SALES 부서에서 일하는 사원들의 부서번호, 이름, 직업을 출력하라.
SELECT D.DEPTNO, E.ENAME, E.JOB
FROM EMP E
JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE D.DNAME='SALES';

--9. 'KING'에게 보고하는 모든 사원의 이름과 급여를 출력하라.
SELECT ENAME, SAL
FROM EMP
WHERE MGR=(SELECT EMPNO FROM EMP WHERE ENAME='KING');

--10. 자신의 급여가 평균 급여보다 많고, 이름에 'S'가 들어가는
-- 사원과 동일한 부서에서 근무하는 모든 사원의 사원번호, 이름,
-- 급여를 출력하라.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP)
AND DEPTNO IN (SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%S%');

--11. 각 부서별로 최소급여를 받는 사원의 이름,급여, 부서번호를 출력하라
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE (SAL, DEPTNO) IN (SELECT MIN(SAL), DEPTNO FROM EMP GROUP BY DEPTNO);

--12. 30번 부서 사원들과 월급과 커미션이 같지 않은
-- 사원들의 이름, 월급, 커미션을 출력하라.
SELECT ENAME, SAL, COMM
FROM EMP
WHERE (SAL, COMM) NOT IN (SELECT SAL, COMM FROM EMP WHERE DEPTNO=30);

--13.사원을 관리 할 수 있는 사원의 평균 급여보다 급여를 많이
--받는 사원의 정보를 출력하자
SELECT *
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP WHERE EMPNO IN (SELECT MGR FROM EMP));

-- 14. 본인이 받는 급여가 본인이 속한 부서의 평균 급여보다 적은 급여를
--		받는 사원의 이름, 급여, 부서번호를 출력하라.
SELECT ENAME, SAL, DEPTNO
FROM EMP E
WHERE SAL < (SELECT AVG(SAL) FROM EMP EE WHERE E.DEPTNO = EE.DEPTNO);
