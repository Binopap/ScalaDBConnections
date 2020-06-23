package com.info.database

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class EmployeeDAO {
  var driverName:String = "com.mysql.cj.jdbc.Driver"
  var url:String = "jdbc:mysql://localhost:3306/mydb?user=root&password=root"
  val db = Database.forURL(url, driver = driverName )

  // creating mapping class
  private class EmployeeTable(tag: Tag) extends Table[EmployeeRow](tag, "Employee"){
    def employeeId = column[Int]("EMPLOYEEID", O.PrimaryKey, O.AutoInc)

    def employeeName = column[String]("EMPLOYEENAME")
    def gender = column[String]("GENDER")
    def salary = column[Double]("salary")

    def * = (employeeId, employeeName, gender, salary)<> ((EmployeeRow.apply _).tupled, EmployeeRow.unapply)
  }

  // creating instance of mapping class
  private val employees = TableQuery[EmployeeTable]

  def createEmployee(emp:EmployeeRow): Future[Int] ={
    var action = employees.insertOrUpdate(emp)      // generating insert query
    var result:Future[Int] = db.run(action)         // executing insert query
    return result
  }

  def createEmployeeWithPlus(emp:EmployeeRow): Unit ={
    val action = employees += emp;
    //action.statements.foreach(println(_))
    val result = db.run(action)
  }

  def createListofEmployees(emps:List[EmployeeRow]): Unit ={
    var action = employees ++= (emps)
    action.statements.foreach(println(_))
    db.run(action)
  }

  def deleteEmployee(empId:Int, emp:EmployeeRow): Unit ={
    var x = employees.filter(rows=> rows.employeeId === empId)
    println(x)
    var action = x.delete
    action.statements.foreach(println(_))
    db.run(action)

  }

  def getAllEmployee(): Unit ={
    var action = employees.result
    action.statements.foreach(println(_))

  }

}
