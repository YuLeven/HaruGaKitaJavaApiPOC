name := """haru-rest-api"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1212"

// https://mvnrepository.com/artifact/com.auth0/java-jwt
libraryDependencies += "com.auth0" % "java-jwt" % "3.1.0"

// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.2"

libraryDependencies ++= Seq(javaJpa, javaWs, filters)