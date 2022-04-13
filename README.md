# lab1jee

Tahar Guemir
Endpoint Path                                                    Request Method

/student-management-system/api/v1/student	                     Add Student
Jason File
{
"firstName":"your first name",
"lastName":"your last name",
"email":"exemple@mail.com",
"phonenumber": 070000000
}

/student-management-system/api/v1/teacher	                      Add Teacher
Jason File
{
"firstName":"your first name",
"lastName":"your last name"
}

/student-management-system/api/v1/subject	                      Add Subject
Jason File
{
"name":"your subject name"
}

/student-management-system/api/v1/student/{studentId}/{subjectId} Add subject to student

/student-management-system/api/v1/teacher/{teacherId}/{subjectId} Add subject to teacher	        GET


