package io.hhplus.cleanarchitecture.support

import io.hhplus.cleanarchitecture.support.database.AfterDatabaseCleanUp
import io.hhplus.cleanarchitecture.support.testcontainers.TestContainerManager
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestConstructor
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest
@AfterDatabaseCleanUp
@ActiveProfiles("test")
@Testcontainers
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class IntegrationTestSupport {

    companion object {
        val mySQLContainer = TestContainerManager.mySQLContainer

        @JvmStatic
        @DynamicPropertySource
        internal fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
        }


        @JvmStatic
        @BeforeAll
        internal fun start() {
            TestContainerManager.start()
        }

    }
}
