select count(*)
from
  employee
  inner join grade
    on employee.grade_code = grade.grade_code
