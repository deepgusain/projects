This project is liquibase springboot project, it shows example how to do db operation using sql file, sql formated file and using xml tags.

Once we runsringboot application it automatically update the DB queries.
To rollback we need to run manual liquibase rollback command.

Below are the liquibase cmd to run update and roolback.

mvn liquibase:update -Dliquibase.url=jdbc:mysql://localhost:3306/cloud -Dliquibase.username=root -Dliquibase.password=root

mvn liquibase:rollback -Dliquibase.url=jdbc:mysql://localhost:3306/cloud -Dliquibase.username=root -Dliquibase.password=root -Dliquibase.rollbackDate="Mar 21, 2019"


mvn liquibase:rollback -Dliquibase.url=jdbc:mysql://localhost:3306/cloud -Dliquibase.username=root -Dliquibase.password=root -Dliquibase.rollbackCount=1

mvn liquibase:dropAll -Dliquibase.url=jdbc:mysql://localhost:3306/cloud -Dliquibase.username=root -Dliquibase.password=root

