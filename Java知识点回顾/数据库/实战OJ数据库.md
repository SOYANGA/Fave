# 实战OJ数据库

## 批量插入数据

```mysql
insert into actor(actor_id,first_name,last_name,last_update) values
(1,'PENELOPE','GUINESS','2006-02-15 12:34:33 '),
(2,'NICK','WAHLBERG','2006-02-15 12:34:33');
```



## 找出所有员工当前薪水salary的情况

```mysql
select distinct salary from salaries where to_date = '9999-01-01' order by salary desc;
```



## 查找最晚入职员工的所有信息

```mysql
select * from employees order by hire_date desc limit 1;
```



## 查找入职员工时间排名倒数第三名的员工的所有信息

```mysql
select * from employees order by hire_date desc limit 1 offset 2;
```



## 查找薪水涨幅超过15次的员工工号emp,no以及其对应的涨次数t

```mysql
select emp_no, count(distinct salary) as t from salaries group by emp_no having t > 15;
```



## 获取所有部门打那个前manager的当前薪水情况，给出dept_no,emp_no 以及salary，当前表示to_date = ‘9999-01-01’

```mysql
select dept_no, d.emp_no,salary from dept_manager as d,salaries as s
where s.emp_no = d.emp_no and
d.to_date = '9999-01-01' and
s.to_date = '9999-01-01';
```



## 从title表获取按照title进行分组

```mysql
select title ,count(title) as t from titles group by title having t >= 2;
```



## 查找重复电子邮箱

```mysql
# Write your MySQL query statement below
 select Email from Person group by Email having (count(Email) > 1);
# select Email from Person group by Email having count(distinct Id) > 1;
```



## 大国家

```mysql
# Write your MySQL query statement below
select name, population ,area from World where area >= 3000000 or population >= 25000000;
```





## 第N高的薪水

```mysql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  declare M Int;
  Set M = N-1;
  RETURN (
      # Write your MySQL query statement below.
      select distinct Salary from Employee  order by Salary desc LIMIT M,1
      # SELECT Salary FROM Employee GROUP BY Salary ORDER BY Salary DESC LIMIT N,1
  );
END
```



## 查找字符串‘10,A,B’中逗号出现的次数cnt

```mysql
select length('10,A,B')-length(replace('10,A,B', ',' ,'')) as cnt;
```



