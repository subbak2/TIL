-- 1. ������� �̸�, �μ���ȣ, �μ��̸��� ����϶�.
SELECT E.ENAME, E.DEPTNO, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO);

-- 2. DALLAS���� �ٹ��ϴ� ����� �̸�, ����, �μ���ȣ, �μ��̸��� ����϶�
SELECT E.ENAME, E.JOB, D.DEPTNO, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO)
WHERE D.LOC='DALLAS';

-- 3. �̸��� 'A'�� ���� ������� �̸��� �μ��̸��� ����϶�.
SELECT E.ENAME, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE E.ENAME LIKE '%A%';

-- 4. ����̸��� �� ����� ���� �μ��� �μ���, �׸��� ������
--����ϴµ� ������ 3000�̻��� ����� ����϶�
SELECT E.ENAME, D.DNAME, E.SAL
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO=D.DEPTNO)
WHERE E.SAL>=3000;

-- 5. ������ 'SALESMAN'�� ������� ������ �� ����̸�, �׸���
-- �� ����� ���� �μ� �̸��� ����϶�.
SELECT E.JOB, E.ENAME, D.DNAME
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
WHERE E.JOB='SALESMAN';

-- 6. Ŀ�̼��� å����(������) ������� �����ȣ, �̸�, ����, ����+Ŀ�̼�,
-- �޿������ ����ϵ�, ������ �÷����� '�����ȣ', '����̸�',
-- '����','�Ǳ޿�', '�޿����'���� �Ͽ� ����϶�.
SELECT E.EMPNO �����ȣ, E.ENAME ����̸�, E.SAL ����, E.SAL+E.COMM �Ǳ޿�, S.GRADE �޿����
FROM EMP E
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
WHERE E.COMM IS NOT NULL;

-- 7. �μ���ȣ�� 10���� ������� �μ���ȣ, �μ��̸�, ����̸�,
-- ����, �޿������ ����϶�
SELECT D.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E
LEFT JOIN DEPT D ON(E.DEPTNO = D.DEPTNO)
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
WHERE E.DEPTNO = 10;

-- 8. �μ���ȣ�� 10��, 20���� ������� �μ���ȣ, �μ��̸�,
-- ����̸�, ����, �޿������ ����϶�. �׸��� �� ��µ�
-- ������� �μ���ȣ�� ���� ������, ������ ���� ������
-- �����϶�.
SELECT D.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E
LEFT JOIN DEPT D ON (E.DEPTNO = D.DEPTNO)
LEFT JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL)
ORDER BY D.DEPTNO, E.SAL DESC;


-- 9. �����ȣ�� ����̸�, �׸��� �� ����� �����ϴ� ��������
-- �����ȣ�� ����̸��� ����ϵ� ������ �÷����� '�����ȣ',
-- '����̸�', '�����ڹ�ȣ', '�������̸�'���� �Ͽ� ����϶�.
SELECT E.EMPNO �����ȣ, E.ENAME ����̸�, M.EMPNO �����ڹ�ȣ, M.ENAME �������̸�
FROM EMP E
LEFT JOIN EMP M ON (E.MGR=M.EMPNO);

-- 10. --��� �̸� �� Ŀ�̼��� ����ϴµ�
--Ŀ�̼��� ���� �ʴ� ����� ��� 'No Commision'�� ǥ���ϰ� ���̸��� COMM���� �ۼ��Ѵ�
SELECT E.ENAME, CASE WHEN NVL(E.COMM, 0) > 0 THEN TO_CHAR(E.COMM)
                     ELSE 'No Commision'
                     END COMM
FROM EMP E;

-- 11. ��� �̸�, ��� ��ȣ, �ش� ����� ������ �̸�, ������ ��ȣ�� �Բ�
--ǥ���ϵ�,�����ڰ� ���� king(���)������ ��� ����� ǥ���Ѵ�
SELECT E.ENAME ����̸�, E.EMPNO �����ȣ, M.ENAME �������̸�, M.EMPNO �����ڹ�ȣ
FROM EMP E
LEFT JOIN EMP M ON (E.MGR = M.EMPNO);

-- 12.�ڽ��� �����ں��� ���� �Ի��� ��� ����� �̸� �� �Ի����� �ش�
--�������� �̸� ���Ի��ϰ� �԰� ǥ���ϰ� �� �̸��� ����
--EMPLOYEE,EMPHIREDATE,MANAGER,MGRHIREDATE�� �����Ѵ�.
SELECT E.ENAME EMPLOYEE, E.HIREDATE EMPHIREDATE, M.ENAME MANAGER, M.HIREDATE MGRHIREDATE
FROM EMP E
INNER JOIN EMP M ON (E.MGR = M.EMPNO)
WHERE E.HIREDATE<M.HIREDATE;

-- 13. ������ ������ ����� ���� ����϶�.
SELECT JOB, COUNT(*)
FROM EMP
GROUP BY JOB;

-- 14. ������ ��� ���� ������ ���� ǥ���ϰ� �� �̸���
--NUMBER OF MANAGERS�� ����.
SELECT DISTINCT COUNT(*) AS "NUMBER OF MANAGERS"
FROM (
    SELECT DISTINCT M.EMPNO
    FROM EMP E
    JOIN EMP M ON (E.MGR=M.EMPNO)
);

-- 15.�ش� �μ��� ��� ����� ���� �μ� �̸�, ��ġ, ��� �� �� ��� �޿���
--ǥ���ϴ� ���Ǹ� �ۼ��Ѵ�.
--�� �̸��� ���� DNAME,LOC,NUMBER OF PEOPLE,SALARY�� �Ѵ�.
SELECT D.DNAME, D.LOC, COUNT(*) "NUMBER OF PEOPLE", ROUND(AVG(SAL),0) "SALARY"
FROM DEPT D
JOIN EMP E ON (D.DEPTNO = E.DEPTNO)
GROUP BY D.DNAME, D.LOC;

-- 16. ������̺��� �޿��� ���� ������� ����� �ο��ϰ��� �Ѵ�.
--	   ������ ���� ����� ����϶�.(���� ������ ���� ������� ������ �����ο��ϰ� �� ���� ������ ���� ������ ���� ����� ����ŭ �ǳʶڴ�.)
--   �����      ���
--     KING		 1
--     FORD		 2
--     ....
SELECT ENAME AS "�����", SAL, RANK() OVER (ORDER BY SAL DESC) AS "���"
FROM EMP
WHERE SAL IS NOT NULL;

--SELECT �����, SAL, ROWNUM AS "���"
--FROM (SELECT ENAME AS "�����", SAL
--    FROM EMP
--    WHERE SAL IS NOT NULL
--    ORDER BY SAL DESC);



