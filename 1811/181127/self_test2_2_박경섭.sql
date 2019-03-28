--1. 'SMITH'���� ������ ���� �޴� ������� �̸��� ������ ����϶�.
SELECT ENAME, SAL
FROM EMP
WHERE SAL>(SELECT SAL FROM EMP WHERE ENAME = 'SMITH');

--2. 10�� �μ��� ������ ���� ������ �޴� ������� �̸�, ����,
-- �μ���ȣ�� ����϶�.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE SAL IN (SELECT SAL FROM EMP WHERE DEPTNO=10);

--3. 'BLAKE'�� ���� �μ��� �ִ� ������� �̸��� ������� �̴µ�
-- 'BLAKE'�� ���� ����϶�.
SELECT ENAME, HIREDATE
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'BLAKE')
AND ENAME != 'BLAKE';

--4. ��ձ޿����� ���� �޿��� �޴� ������� �����ȣ, �̸�, ������
-- ����ϵ�, ������ ���� ��� ������ ����϶�.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL)FROM EMP)
ORDER BY SAL DESC;

--5. �̸��� 'T'�� �����ϰ� �ִ� ������ ���� �μ����� �ٹ��ϰ�
-- �ִ� ����� �����ȣ�� �̸��� ����϶�.
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO IN (SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%T%');

--6. 30�� �μ��� �ִ� ����� �߿��� ���� ���� ������ �޴� �������
-- ���� ������ �޴� ������� �̸�, �μ���ȣ, ������ ����϶�.
--(��, ALL �Ǵ� ANY �����ڸ� ����� ��)
SELECT ENAME, EMPNO, SAL
FROM EMP
WHERE SAL > ALL(SELECT SAL FROM EMP WHERE DEPTNO=30);

--7. 'DALLAS'���� �ٹ��ϰ� �ִ� ����� ���� �μ����� ���ϴ� �����
-- �̸�, �μ���ȣ, ������ ����϶�.
SELECT E.ENAME, D.DNAME, E.JOB
FROM EMP E
JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE D.LOC = 'DALLAS';

--8. SALES �μ����� ���ϴ� ������� �μ���ȣ, �̸�, ������ ����϶�.
SELECT D.DEPTNO, E.ENAME, E.JOB
FROM EMP E
JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE D.DNAME='SALES';

--9. 'KING'���� �����ϴ� ��� ����� �̸��� �޿��� ����϶�.
SELECT ENAME, SAL
FROM EMP
WHERE MGR=(SELECT EMPNO FROM EMP WHERE ENAME='KING');

--10. �ڽ��� �޿��� ��� �޿����� ����, �̸��� 'S'�� ����
-- ����� ������ �μ����� �ٹ��ϴ� ��� ����� �����ȣ, �̸�,
-- �޿��� ����϶�.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP)
AND DEPTNO IN (SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%S%');

--11. �� �μ����� �ּұ޿��� �޴� ����� �̸�,�޿�, �μ���ȣ�� ����϶�
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE (SAL, DEPTNO) IN (SELECT MIN(SAL), DEPTNO FROM EMP GROUP BY DEPTNO);

--12. 30�� �μ� ������ ���ް� Ŀ�̼��� ���� ����
-- ������� �̸�, ����, Ŀ�̼��� ����϶�.
SELECT ENAME, SAL, COMM
FROM EMP
WHERE (SAL, COMM) NOT IN (SELECT SAL, COMM FROM EMP WHERE DEPTNO=30);

--13.����� ���� �� �� �ִ� ����� ��� �޿����� �޿��� ����
--�޴� ����� ������ �������
SELECT *
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP WHERE EMPNO IN (SELECT MGR FROM EMP));

-- 14. ������ �޴� �޿��� ������ ���� �μ��� ��� �޿����� ���� �޿���
--		�޴� ����� �̸�, �޿�, �μ���ȣ�� ����϶�.
SELECT ENAME, SAL, DEPTNO
FROM EMP E
WHERE SAL < (SELECT AVG(SAL) FROM EMP EE WHERE E.DEPTNO = EE.DEPTNO);
