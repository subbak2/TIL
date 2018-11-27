-- 1. 사원들의 이름, 부서번호, 부서이름을 출력하라.
SELECT E.ENAME, E.DEPTNO, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO);

-- 2. DALLAS에서 근무하는 사원의 이름, 업무, 부서번호, 부서이름을 출력하라
SELECT E.ENAME, E.JOB, D.DEPTNO, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO)
WHERE D.LOC='DALLAS';

-- 3. 이름에 'A'가 들어가는 사원들의 이름과 부서이름을 출력하라.
SELECT E.ENAME, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE E.ENAME LIKE '%A%';

-- 4. 사원이름과 그 사원이 속한 부서의 부서명, 그리고 월급을
--출력하는데 월급이 3000이상인 사원을 출력하라
SELECT E.ENAME, D.DNAME, E.SAL
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO)
WHERE E.SAL>=3000;

-- 5. 업무가 'SALESMAN'인 사원들의 업무와 그 사원이름, 그리고
-- 그 사원이 속한 부서 이름을 출력하라.
SELECT E.JOB, E.ENAME, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE E.JOB='SALESMAN';

-- 6. 커미션이 책정된(정해진) 사원들의 사원번호, 이름, 연봉, 연봉+커미션,
-- 급여등급을 출력하되, 각각의 컬럼명을 '사원번호', '사원이름',
-- '연봉','실급여', '급여등급'으로 하여 출력하라.
SELECT E.EMPNO 사원번호, E.ENAME 사원이름, E.SAL 연봉, E.SAL+E.COMM 실급여, S.GRADE 급여등급
FROM EMP E
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
WHERE E.COMM IS NOT NULL;

-- 7. 부서번호가 10번인 사원들의 부서번호, 부서이름, 사원이름,
-- 월급, 급여등급을 출력하라
SELECT D.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E
LEFT JOIN DEPT D ON(E.DEPTNO = D.DEPTNO)
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
WHERE E.DEPTNO = 10;

-- 8. 부서번호가 10번, 20번인 사원들의 부서번호, 부서이름,
-- 사원이름, 월급, 급여등급을 출력하라. 그리고 그 출력된
-- 결과물을 부서번호가 낮은 순으로, 월급이 높은 순으로
-- 정렬하라.
SELECT D.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
ORDER BY D.DEPTNO, E.SAL DESC;


-- 9. 사원번호와 사원이름, 그리고 그 사원을 관리하는 관리자의
-- 사원번호와 사원이름을 출력하되 각각의 컬럼명을 '사원번호',
-- '사원이름', '관리자번호', '관리자이름'으로 하여 출력하라.
SELECT E.EMPNO 사원번호, E.ENAME 사원이름, M.EMPNO 관리자번호, M.ENAME 관리자이름
FROM EMP E
LEFT JOIN EMP M ON (E.MGR=M.EMPNO);

-- 10. --사원 이름 및 커미션을 출력하는데
--커미션을 받지 않는 사원일 경우 'No Commision'을 표시하고 열이름을 COMM으로 작성한다
SELECT E.ENAME, CASE WHEN NVL(E.COMM, 0) > 0 THEN TO_CHAR(E.COMM)
                     ELSE 'No Commision'
                     END COMM
FROM EMP E;

-- 11. 사원 이름, 사원 번호, 해당 사원의 관리자 이름, 관리자 번호를 함께
--표시하되,관리자가 없는 king(사원)포함한 모든 사원을 표시한다
SELECT E.ENAME 사원이름, E.EMPNO 사원번호, M.ENAME 관리자이름, M.EMPNO 관리자번호
FROM EMP E
LEFT JOIN EMP M ON (E.MGR = M.EMPNO);

-- 12.자신의 관리자보다 먼저 입사한 모든 사원의 이름 및 입사일을 해당
--관리자의 이름 및입사일과 함게 표시하고 열 이름을 각각
--EMPLOYEE,EMPHIREDATE,MANAGER,MGRHIREDATE로 저장한다.
SELECT E.ENAME EMPLOYEE, E.HIREDATE EMPHIREDATE, M.ENAME MANAGER, M.HIREDATE MGRHIREDATE
FROM EMP E
INNER JOIN EMP M ON (E.MGR = M.EMPNO)
WHERE E.HIREDATE<M.HIREDATE;

-- 13. 업무가 동일한 사람의 수를 출력하라.
SELECT JOB, COUNT(*)
FROM EMP
GROUP BY JOB;

-- 14. 관리자 목록 없이 관리자 수만 표시하고 열 이름을
--NUMBER OF MANAGERS로 지정.
SELECT DISTINCT COUNT(*) AS "NUMBER OF MANAGERS"
FROM (
    SELECT DISTINCT M.EMPNO
    FROM EMP E
    JOIN EMP M ON (E.MGR=M.EMPNO)
);

-- 15.해당 부서의 모든 사원에 대한 부서 이름, 위치, 사원 수 및 평균 급여를
--표시하는 정의를 작성한다.
--열 이름을 각각 DNAME,LOC,NUMBER OF PEOPLE,SALARY로 한다.
SELECT D.DNAME, D.LOC, COUNT(*) "NUMBER OF PEOPLE", ROUND(AVG(SAL),0) "SALARY"
FROM DEPT D
JOIN EMP E ON (D.DEPTNO = E.DEPTNO)
GROUP BY D.DNAME, D.LOC;

-- 16. 사원테이블에서 급여가 높은 순서대로 등수를 부여하고자 한다.
--	   다음과 같은 결과를 출력하라.(같은 순위를 갖는 사원들은 동일한 순위부여하고 그 다음 순위는 같은 순위를 갖는 사람의 수만큼 건너뛴다.)
--   사원명      등수
--     KING		 1
--     FORD		 2
--     ....
SELECT ENAME AS "사원명", SAL, RANK() OVER (ORDER BY SAL DESC) AS "등수"
FROM EMP
WHERE SAL IS NOT NULL;

--SELECT 사원명, SAL, ROWNUM AS "등수"
--FROM (SELECT ENAME AS "사원명", SAL
--    FROM EMP
--    WHERE SAL IS NOT NULL
--    ORDER BY SAL DESC);



