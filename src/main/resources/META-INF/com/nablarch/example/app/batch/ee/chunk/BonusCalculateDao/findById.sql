select
  employee.employee_id,
  employee.full_name,
  employee.basic_salary,
  employee.grade_code,
  grade.bonus_magnification,
  grade.fixed_bonus
from
  employee
  inner join grade
    on employee.grade_code = grade.grade_code
where employee_id = /*id*/1
