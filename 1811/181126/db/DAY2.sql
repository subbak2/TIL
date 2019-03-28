--단일행함수 : 행단위로 실행하여 결과 반환하는 함수
--다중행함수(그룹함수) : 그룹단위로 실행하여 결과 반환하는 함수(그룹 수만큼 처리하여 리턴)

select  ename, lower(ename),upper(ename), initcap(ENAME),
        concat(ename,'사원')
from    emp;

select  substr('i love you',3,4) a,
        substr('i love you',3) b,
        substr('i love you',-3,3) c,
        substr('i love you',-3) d,
        substr('커피 타임',1,2) e
from    dual;

select *
from   emp
where  substr(ename,1,1)='S';

select  instr('i love you','you'),instr('i love you','me'),
        instr('i love you you','you'),
        instr('i love you you','you',1,1),
        instr('i love you you','you',1,2),
        instr('i love you you','you',-1,1)
from    dual;

select  *
from    emp
where   instr(ename,'S') != 0;
--where   ename like '%S%';

-- khy@androidjava.com, 
select  SUBSTR('khy@androidjava.com',1,instr('khy@androidjava.com','@')-1) as mailid,
        SUBSTR('khy@androidjava.com',instr('khy@androidjava.com','@')+1)as maildomain
from    dual;

select  ltrim('*xyzilove you*','*xz') a,
        rtrim('*xyzilove you*','*xz') b,
        trim(both '*' from '**i love you*') c,
        trim('  i love you   ') d,
        ltrim(rtrim('  i love you   ')) d2,
        length('  i love you   ') e,
        length(trim('  i love you   ')) f
from    dual;

update  emp
set     ename='SMITH '
where   empno = '7369';

select  *
from    emp
where   ename = 'SMITH';


select length(ename) from emp where empno = 7369;

rollback;


select  length('안녕'), lengthb('안녕')
from    dual;

select  123.123456789, round(123.123456789),
        round(123.123456789,0),
        round(123.123456789,5),
        round(125.123456789,-1)
from    dual;

select  123.123456789, trunc(123.123456789),
        trunc(123.123456789,0),
        trunc(123.123456789,5),
        trunc(125.123456789,-1)
from    dual;

-- 날짜 +(-) 숫자(일) = 날짜
-- 날짜 -날짜 = 숫자
select  sysdate, to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') a,
        to_char(sysdate+1,'yyyy/mm/dd hh24:mi:ss') b,
        to_char(sysdate-2,'yyyy/mm/dd hh24:mi:ss') c,
        to_char(sysdate+1/24,'yyyy/mm/dd hh24:mi:ss') d
from    dual;

select  sysdate - HIREDATE,trunc(sysdate - HIREDATE)
from    emp;

select  add_months(sysdate,1)
from    dual;

select  months_between(sysdate,hiredate),
        months_between(hiredate,sysdate)
from    emp;

select  sysdate, trunc(sysdate+0.5,'dd'),trunc(sysdate+10,'mm'),trunc(sysdate,'yyyy'),
                 round(sysdate+0.5,'dd'),round(sysdate+10,'mm'),round(sysdate,'yyyy')
from    dual;

select  next_day(sysdate,2)
from    dual;









select  sysdate, to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') a,
                 to_char(sysdate,'yyyy/mm/dd') b,
                 to_char(sysdate,'fmyyyy"년" mm"월" dd"일"') b2,
                 to_char(sysdate,'mm') c,
                 to_char(sysdate,'dd') d
from    dual;


select  extract(year from hiredate),
        extract(month from hiredate)
from    emp;

select   *
from     emp
where    hiredate = '81/12/03';

select   *
from     emp
where    trunc(hiredate,'dd') = to_date('1981/12/03','yyyy/mm/dd');

--where    to_char(hiredate,'yy/mm/dd hh24:mi:ss') 
--        = to_date('81/12/03','yy/mm/dd')

select   *
from     emp
where    to_char(hiredate,'yy/mm/dd hh24:mi:ss') 
        = to_char(to_date('81/12/03','yy/mm/dd'),'yy/mm/dd hh24:mi:ss') ;
        
-- 그룹함수는 null 데이터는 포함하여 처리하지 않는다.
select  count(*) 전사원수, sum(sal) 사원급여합, avg(sal) 사원급여평균,
        sum(comm), avg(comm), sum(comm)/count(*)
from    emp;

select  count(*), count(comm), count(deptno), count(DISTINCT deptno)
from    emp;

select  count(*), count(comm)
from    emp
where   deptno = 30;
        
select      deptno, count(*), avg(sal), count(comm)
from        emp
group by    deptno;
        
insert into emp(empno,ename,sal) values(9999,'SCSA',2000);        

rollback;
--error
select      deptno, ename, max(sal)
from        emp
group by    deptno;

select      deptno, ename, max(sal)
from        emp
group by    deptno, ename;


select      deptno dno, max(sal)
from        emp
group by    deptno;
--group by    1;  -- 사용불가
--group by    dno; -- 사용불가

select    SUBSTR(ENAME,1,1), COUNT(*)
from      EMP
GROUP BY  SUBSTR(ENAME,1,1)
ORDER BY  1;

SELECT      MAX(AVG(SAL))
FROM        EMP
GROUP BY    JOB;


SELECT      DEPTNO, COUNT(*)
FROM        EMP
GROUP BY    DEPTNO
HAVING      COUNT(*) >= 5;

SELECT      DEPTNO, COUNT(*)
FROM        EMP
WHERE       SAL >= 1000
GROUP BY    DEPTNO
HAVING      COUNT(*) >= 5;

SELECT      DEPTNO, COUNT(*)
FROM        EMP
GROUP BY    DEPTNO
HAVING      COUNT(*) >= 5  AND SAL >= 1000; -- ERROR

SELECT      DEPTNO, COUNT(*), AVG(SAL)
FROM        EMP
WHERE       SAL>=1000
GROUP BY    DEPTNO
HAVING      COUNT(*) >= 4;

SELECT      DEPTNO, COUNT(*), AVG(SAL)
FROM        EMP
WHERE       SAL>=1000
GROUP BY    DEPTNO
HAVING      COUNT(*) >= 4 AND AVG(SAL) >= 2000;


INSERT INTO EMP(EMPNO,ENAME,DEPTNO) VALUES(9999,'SCSA',50);

INSERT INTO EMP VALUES(9999,'SCSA',50);


DELETE FROM DEPT WHERE DEPTNO = 10;

SELECT  EMPNO,ENAME,DNAME
FROM    EMP,DEPT;

SELECT  EMP.EMPNO,EMP.ENAME,DEPT.DNAME
FROM    EMP,DEPT
WHERE   EMP.DEPTNO = DEPT.DEPTNO;

-- equi join
SELECT  e.EMPNO,e.ENAME,d.DNAME
FROM    EMP e,DEPT d
WHERE   e.DEPTNO = d.DEPTNO;

-- non-equi join
SELECT  e.EMPNO,e.ENAME,s.grade
FROM    EMP e,salgrade s
where   e.sal BETWEEN s.losal and s.hisal;

SELECT  e.EMPNO,e.ENAME,d.DNAME,s.grade
FROM    EMP e,DEPT d,salgrade s
WHERE   e.DEPTNO = d.DEPTNO
and     e.sal BETWEEN s.losal and s.hisal;   

SELECT  e.EMPNO,e.ENAME,s.grade
FROM    EMP e,salgrade s
where   e.sal BETWEEN s.losal(+) and s.hisal(+);


update  emp
set     deptno = null, SAL = 1000
where   empno = 7839;

SELECT  e.EMPNO,e.ENAME,d.DNAME
FROM    EMP e,DEPT d
WHERE   e.DEPTNO = d.DEPTNO(+);


SELECT  e.EMPNO,e.ENAME,d.deptno, d.DNAME
FROM    EMP e,DEPT d
WHERE   e.DEPTNO(+) = d.DEPTNO;

-- error : full outer join 지원하지 않음
SELECT  e.EMPNO,e.ENAME,d.deptno, d.DNAME
FROM    EMP e,DEPT d
WHERE   e.DEPTNO(+) = d.DEPTNO(+);

select  e.empno,e.ename ,m.empno mno,m.ename mname
from    emp e, emp m
where   e.mgr = m.empno;

select  e.empno,e.ename ,m.empno mno,m.ename mname
from    emp e, emp m
where   e.mgr = m.empno(+);

update  emp
set     sal = (select avg(sal) from emp)
where   empno = 7369;

select  e.deptno, e.empno,e.ename, e.sal
from    emp e
where   e.sal = (
                select max(sal)
                from   emp
                where  e.deptno = deptno
              );
              
select *
from    emp
where   deptno = (select deptno
                    from emp
                    where ename = 'SMITH');

select *
from    emp
where   JOB IN (select  JOB
                    from emp
                    where DEPTNO = 10)
AND     DEPTNO != 10;
              
 -- 관리자인 사원의 사번,이름,월급여 조회
 
 SELECT EMPNO,ENAME,SAL,'관리자' AS 구분
 FROM   EMP
 WHERE  EMPNO IN (SELECT DISTINCT MGR
                  FROM EMP);
              
-- 관리자가 아닌 사원의 사번,이름,월급여 조회
 
 SELECT EMPNO,ENAME,SAL,'사원' AS 구분
 FROM   EMP
 WHERE  EMPNO NOT IN (SELECT DISTINCT MGR
                      FROM EMP
                      WHERE MGR IS NOT NULL);       
                      
SELECT EMPNO,ENAME,SAL,'관리자' AS 구분
FROM   EMP
WHERE  EMPNO IN (SELECT DISTINCT MGR
                  FROM EMP)
UNION ALL
SELECT EMPNO,ENAME,SAL,'사원' AS 구분
FROM   EMP
WHERE  EMPNO NOT IN (SELECT DISTINCT MGR
                      FROM EMP
                      WHERE MGR IS NOT NULL);                         
                      
SELECT EMPNO ENO,ENAME ENM,SAL ESAL,'관리자' AS 구분
FROM   EMP
WHERE  EMPNO IN (SELECT DISTINCT MGR
                  FROM EMP)
UNION
SELECT EMPNO,ENAME,SAL,'사원' AS 구분
FROM   EMP
WHERE  EMPNO NOT IN (SELECT DISTINCT MGR
                      FROM EMP
                      WHERE MGR IS NOT NULL);                      
                      
                      
                      
SELECT EMPNO ENO,ENAME ENM,SAL ESAL,'관리자' AS 구분
FROM   EMP
WHERE  EMPNO IN (SELECT DISTINCT MGR
                  FROM EMP)
UNION
SELECT EMPNO,ENAME,SAL,'사원' AS 구분
FROM   EMP
WHERE  EMPNO NOT IN (SELECT DISTINCT MGR
                      FROM EMP
                      WHERE MGR IS NOT NULL)
ORDER BY ESAL DESC;
        
-- ANY, ALL
--  > ANY (서브쿼리), < ANY (서브쿼리), = ANY (서브쿼리)
--  > ALL (서브쿼리), < ALL (서브쿼리)
                      
                      
SELECT EMPNO,ENAME,SAL,'관리자' AS 구분
FROM   EMP
WHERE  EMPNO = ANY (SELECT DISTINCT MGR
                  FROM EMP);               

-- 최대값 비교
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL > ALL(
                    SELECT SAL
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL > (
                    SELECT  MAX(SAL)
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );                   
-- 최소값비교
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL < ALL(
                    SELECT SAL
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL < (
                    SELECT  MIN(SAL)
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );  
-- 최소값 비교
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL > ANY(
                    SELECT SAL
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL > (
                    SELECT  MIN(SAL)
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                );
-- 최대값 비교                
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL < ANY(
                    SELECT SAL
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                   );   
                   
SELECT  ENAME,SAL
FROM    EMP
WHERE   SAL < (
                    SELECT  MAX(SAL)
                    FROM    EMP
                    WHERE   JOB = 'SALESMAN'
                );   
                
SELECT  EMPNO,ENAME,SAL,(SELECT AVG(SAL) FROM EMP),SAL -(SELECT AVG(SAL) FROM EMP)
FROM    EMP;
                
                
                
                
                
                
                
                