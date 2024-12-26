package io.hhplus.cleanarchitecture.support.testcontainers

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.wait.strategy.Wait

class TestContainerManager {
    companion object {
        internal val mySQLContainer: MySQLContainer<*> = MySQLContainer("mysql:8.0.28")
            .withDatabaseName("hhplus-clean-architecture-lecture")
            .withUsername("root")
            .withPassword("1234")
            .withInitScript("sql/schema.sql")
            .waitingFor(Wait.forHttp("/"))
            .withReuse(true)

        internal fun start() {
            mySQLContainer.start()
        }

    }
}
