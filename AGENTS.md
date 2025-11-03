# Repository Guidelines

Follow this playbook to keep contributions consistent across Opinion Account modules.

| Topic | Guidance |
| --- | --- |
| Project Structure | `opinion-account/` hosts the Vert.x service entry point, while `opinion-account-common/` packages reusable DTOs and helpers; both mirror the Maven `src/main/java` and `src/test/java` layout with generated sources under `src/main/generated`. |
| Module Scaffolding | Every new module must include `src/test/resources/log4j2-test.xml` containing the shared console configuration (see snippet below) to keep test logging consistent. |
| Build Commands | `./mvnw clean compile` compiles all modules; `./mvnw -pl opinion-account test` runs service tests; swap the module name (e.g., `opinion-account-common`) to scope builds. |
| Packaging | `./mvnw package` assembles deployable jars plus source and javadoc bundles; attach profiles locally only when asserting release readiness. |
| Coding Style | Use Java 21, four-space indentation, and newline-terminated files; packages remain lowercase, classes PascalCase, members camelCase, and model types avoid public setters in favor of builders exposing `toJson()`. |
| Logging | Every new class declares `private static final Logger logger = LogManager.getLogger(ClassName.class);` and relies on Log4j2 for structured console output. |
| Testing | Write JUnit 5 and Vert.x JUnit tests that mirror production packages, name files `*Test`, add an empty `@BeforeEach void setUp(Vertx vertx) throws Exception {}` (or override it) to trigger Vert.x logging hooks, and aim for both success and failure coverage before review. |
| Commits & PRs | Keep commit subjects imperative and ≤72 characters (e.g., `Add OpinionAccountConfig model`), squash noisy history, and ensure pull requests link issues, list verification commands, and attach evidence for behavior changes. |
| Security | Store secrets outside the repo, validate configuration with `vertx-config` JSON locally, and schedule dependency refreshes using `./mvnw versions:use-latest-releases`. |

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT" follow="true">
            <PatternLayout>
                <pattern>%level{length=1} %d{HH:mm:ss.SSS}[%-26t]%c{...9} - %enc{%msg}{CRLF} %replace{%ex}{[\r\n]{1,2}}{|}%n%throwable</pattern>
            </PatternLayout>
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="io.netty" level="warn" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>
        <Logger name="com.mchange" level="warn" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>
        <Root level="all">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```
