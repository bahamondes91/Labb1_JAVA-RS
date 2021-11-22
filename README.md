# Labb1_JAVA-RS

ENDPOINTS:

createStudent POST: http://localhost:8080/student-management-system/api/v1/student/new

vid fel: {"Error when creating new student "}

updateLastName PATCH: http://localhost:8080/student-management-system/api/v1/student/updatename/{id}

vid fel: {"ID NOT FOUND ": {id}}

getAllStudents GET: http://localhost:8080/student-management-system/api/v1/student/getall

vid fel: { "List is empty "}

getOneStudent GET: http://localhost:8080/student-management-system/api/v1/student/{id}

vid fel: {"ID NOT FOUND ": {id}}

deleteStudent DELETE: http://localhost:8080/student-management-system/api/v1/student/{id}

succes: {"ID TO DELETE ": {id}}
vid fel: {"ID NOT FOUND ": {id}}

