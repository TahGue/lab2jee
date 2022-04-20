# lab2jee

Tahar Guemir
Endpoint Path                                                    Request Method     Function

/student-management-system/api/v1/student	                          POST          Add Student
Jason File
{
"firstName":"your first name",
"lastName":"your last name",
"email":"exemple@mail.com",
"phonenumber": 070000000
}

/student-management-system/api/v1/teacher	                          POST              Add Teacher
Jason File
{
"firstName":"your first name",
"lastName":"your last name"
}

/student-management-system/api/v1/subject	                          POST              Add Subject
Jason File
{
"name":"your subject name"
}

/student-management-system/api/v1/student/{studentId}/{subjectId}     PUTCH             Add subject to student

/student-management-system/api/v1/teacher/{teacherId}/{subjectId}     PUTCH             Add subject to teacher	        GET

/student-management-system/api/v1/student                             GET               Get all student

/student-management-system/api/v1/subject                             GET               Get all subject

/student-management-system/api/v1/teacher                             GET               Get all teacher
