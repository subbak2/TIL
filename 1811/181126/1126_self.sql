--1. ����) EMP Table���� �̸�, �޿�, Ŀ�̼� �ݾ�, �Ѿ�(sal + comm)��
-- ���Ͽ� �Ѿ��� ���� ������ ����϶�. ��, Ŀ�̼��� NULL��
-- ����� �����Ѵ�.

SELECT  ENAME, SAL, COMM, SAL+COMM
FROM    EMP
WHERE   COMM IS NOT NULL ;

--2. ����) 10�� �μ��� ��� ����鿡�� �޿��� 13%�� ���ʽ��� �����ϱ��
-- �Ͽ���. �̸�, �޿�, ���ʽ� �ݾ�, �μ���ȣ�� ����϶�.

SELECT  ENAME AS �̸�, SAL AS �޿�, SAL*0.13 AS ���ʽ�, DEPTNO AS �μ���ȣ
FROM    EMP
WHERE   DEPTNO = 10;

--3. ����) 30�� �μ��� ������� ������ ����Ͽ� �̸�, �μ���ȣ, �޿�, ������
-- ����϶�. ��, ������ �޿��� 150%�� ���ʽ��� �����Ѵ�.

SELECT  ENAME �̸�, DEPTNO �μ���ȣ, SAL �޿�, SAL*13.5 ���� 
FROM    EMP
WHERE   DEPTNO = 30;

--4. ���� ) �μ���ȣ�� 20�� �μ��� �ð��� �ӱ��� ����Ͽ� ����϶�.
--��, 1���� �ٹ��ϼ��� 20���̰�, 1�� �ٹ��ð��� 5�ð��̴�.
--��¾���� �̸�, �޿�, �ð��� �ӱ�(�Ҽ����� ù ��° �ڸ�
-- ���� �ݿø�)�� ����϶�

SELECT  ENAME �̸�, SAL �޿�,ROUND(SAL/100) "�ð��� �ӱ�"
FROM    EMP
WHERE   DEPTNO=20;

--5. ����) �޿��� $1,500���� $3,000 ������ ����� �޿��� 15%�� ȸ���
-- �����ϱ�� �Ͽ���. �̸� �̸�, �޿�, ȸ��(�ʴ��� �ڸ�����
-- �ݿø�)�� ����϶�.

SELECT  ENAME �̸�, SAL �޿�, ROUND(SAL*0.15,-2) ȸ��
FROM    EMP
WHERE   SAL BETWEEN 1500 AND 3000;


--6. ����) �޿��� $2,000 �̻��� ��� ����� �޿��� 15%�� ������� ����
-- �� �Ͽ���. �̸�, �޿�, ������(�Ҽ��� ���� ����)�� ����϶�.

SELECT  ENAME �̸�, SAL �޿�, TRUNC(SAL*0.15) ������
FROM    EMP
WHERE   SAL>=2000;

--7. ����) ����� �μ���ȣ, �̸�, �Ի�
-- ��, ������, �ٹ��ϼ�(�Ҽ��� ���� ����), �ٹ����(�Ҽ����� 1�ڸ���, �ݿø�), �ٹ�������
--(�Ѵ� 30�� ����,�Ҽ��� ���� ����), �ٹ��ּ�(�Ҽ��� ���� ����)�� ����϶�.

SELECT  DEPTNO �μ���ȣ,
ENAME �̸�, 
HIREDATE �Ի���,
SYSDATE ������,
trunc(sysdate - HIREDATE) �ٹ��ϼ�,
extract(year from sysdate) - extract(year from hiredate) �ٹ����, 
trunc(months_between(sysdate,hiredate),0) �ٹ�������
FROM    EMP;

--8. ����) ��� ����� �Ǽ��ɾ��� ����Ͽ� ����϶�. ��, �޿��� ����
-- ������ �̸�, �޿�, �Ǽ��ɾ��� ����϶�.(�Ǽ��ɾ��� �ݿ���
-- ���� 10%�� ������ �� �ݾ�)

SELECT  ENAME �̸�, SAL �޿�, SAL*0.9 �Ǽ��ɾ�
FROM    EMP
ORDER BY SAL DESC;


--9. ����) ����̸�, �Ի���, �Ի��Ϸκ��� 90���� ���� ���� ��, �޿��� ����϶�.
SELECT  ENAME ����̸�, HIREDATE �Ի���, HIREDATE+90 "�Ի� 90�� ��", SAL �޿�
FROM    EMP;

--10. ����) ����� �Ի���, �Ի��Ϸκ��� 6������ ���� ���� ��¥, �޿�
-- �� ����϶�

SELECT  HIREDATE �Ի���, ADD_MONTHS(HIREDATE,6) "�Ի��Ϸκ��� 6���� ��", SAL �޿�
FROM    EMP;

--11. ����) ��� ����� �Ի��Ϸκ��� 60���� ���� ���� �������� �� ��,�� ��,
--�� �� �ΰ��� ���Ͽ� �̸�, �Ի���,��MONDAY���� ����϶�

SELECT  ENAME �̸� , HIREDATE �Ի���, NEXT_DAY(HIREDATE+60,1) "MONDAY"
FROM    EMP;

--12. ����) �Ի����� ��1996�� 5�� 14�ϡ��� ���·� �̸�, �Ի����� ���
-- �϶�.

SELECT  TO_CHAR(HIREDATE,'FMYYYY"��" MM"��" DD"��"') �Ի���
FROM    EMP;

--13. ����) �̸��� ���ڼ��� 6�� �̻��� ����� �̸��� �տ��� 3�ڸ� ����
-- �� �ҹ��ڷ� �̸����� ����϶�.

SELECT  SUBSTR(LOWER(ENAME),1,3)
FROM    EMP
WHERE   ENAME LIKE '%______%';


--14. ����) 10�� �μ��� ��ձ޿�, �ְ�޿�, �����޿�, �ο����� ���Ͽ� ����϶�.

SELECT      ROUND(AVG(SAL),0) ��ձ޿� , MAX(SAL) �ְ�޿�, MIN(SAL) �����޿�, COUNT(*) �ο���
FROM        EMP
WHERE       DEPTNO=10
GROUP BY    DEPTNO;

--15. ����) �� �μ��� �μ���ȣ,��ձ޿�, �ְ�޿�, �����޿�, �ο����� ���Ͽ� ����϶�.

SELECT      DEPTNO, ROUND(AVG(SAL),0) ��ձ޿� , MAX(SAL) �ְ�޿�, MIN(SAL) �����޿�, COUNT(*) �ο���
FROM        EMP
GROUP BY    DEPTNO;

--16. ����) �� �μ��� ���� ������ �ϴ� ������� ���Ͽ� �μ���ȣ,
--������, �ο����� ����϶�.

SELECT      DEPTNO, JOB, COUNT(*)
FROM        EMP
GROUP BY    DEPTNO, JOB;

--17. ����) ���� ������ �ϴ� ����� ���� 3�� �̻��� ������ �ο�����
-- ����϶�.

SELECT  JOB, COUNT(*)
FROM    EMP
GROUP BY JOB
HAVING  COUNT(*)>=3;

--18. ����) �� �μ��� ��� ����, ��ü ����, �ְ� ����, ���� ������ ����
-- �� ��� ������ ���� ������ ����϶�.

SELECT      ROUND(AVG(SAL)) "��� ����", SUM(SAL) "��ü ����", MAX(SAL) "�ְ� ����", MIN(SAL) "���� ����"
FROM        EMP
GROUP BY    DEPTNO
ORDER BY    AVG(SAL) DESC;

--19. ����) �μ��� �Ի���� ��ձ޿��� �Ʒ� �������� ����϶�
--    1��   2��  3�� 4�� 5�� 6�� 7�� 8�� 9�� 10�� 11�� 12��
-- 10 1300
-- 20
-- 30       1425
SELECT      DEPTNO, AVG("1��"), AVG("2��"), AVG("3��"), AVG("4��"), AVG("5��"), AVG("6��"), AVG("7��"), AVG("8��"), AVG("9��"), AVG("10��"), AVG("11��"), AVG("12��")
FROM        (SELECT DEPTNO, CASE WHEN extract(month from hiredate)=1 THEN SAL END "1��",
                            CASE WHEN extract(month from hiredate)=2 THEN SAL END "2��",
                            CASE WHEN extract(month from hiredate)=3 THEN SAL END "3��",
                            CASE WHEN extract(month from hiredate)=4 THEN SAL END "4��",
                            CASE WHEN extract(month from hiredate)=5 THEN SAL END "5��",
                            CASE WHEN extract(month from hiredate)=6 THEN SAL END "6��",
                            CASE WHEN extract(month from hiredate)=7 THEN SAL END "7��",
                            CASE WHEN extract(month from hiredate)=8 THEN SAL END "8��",
                            CASE WHEN extract(month from hiredate)=9 THEN SAL END "9��",
                            CASE WHEN extract(month from hiredate)=10 THEN SAL END "10��",
                            CASE WHEN extract(month from hiredate)=11 THEN SAL END "11��",
                            CASE WHEN extract(month from hiredate)=12 THEN SAL END "12��"
            FROM EMP
            )
GROUP BY    DEPTNO
ORDER BY    DEPTNO;

