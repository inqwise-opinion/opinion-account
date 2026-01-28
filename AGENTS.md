# Repository Guidelines

Follow this playbook to keep contributions consistent across Opinion Account modules.

| Topic | Guidance |
| --- | --- |
| Project Structure | `opinion-account/` hosts the Vert.x service entry point, while `opinion-account-common/` packages reusable DTOs and helpers; both mirror the Maven `src/main/java` and `src/test/java` layout with generated sources under `src/main/generated`. |
| Module Scaffolding | Every new module must include `src/test/resources/log4j2-test.xml` containing the shared console configuration (see snippet below) and update `coverage-report/pom.xml` with its source/class paths to keep test logging and coverage consistent. |
| Build Commands | `./mvnw clean compile` compiles all modules; `./mvnw -pl opinion-account test` runs service tests; swap the module name (e.g., `opinion-account-common`) to scope builds. |
| Packaging | `./mvnw package` assembles deployable jars plus source and javadoc bundles; attach profiles locally only when asserting release readiness. |
| Coding Style | Use Java 21, tab indentation, and newline-terminated files; packages remain lowercase, classes PascalCase, members camelCase, and model types avoid public setters in favor of builders exposing `toJson()`. |
| Logging | Concrete classes declare `private static final Logger logger = LogManager.getLogger(ClassName.class);`; do not redeclare in subclasses, and use a `protected static final Logger` when the base type is abstract. |
| Gitignore | Maintain a single repository-wide `.gitignore` in the root; remove or update module-level ignores when scaffolding new packages. |
| Testing | Write JUnit 5 and Vert.x JUnit tests that mirror production packages, name files `*Test`, add an empty `@BeforeEach void setUp(Vertx vertx) throws Exception {}` (or override it) to trigger Vert.x logging hooks, and aim for both success and failure coverage before review. |
| Commits & PRs | Keep commit subjects imperative and ≤72 characters (e.g., `Add OpinionAccountConfig model`), squash noisy history, and ensure pull requests link issues, list verification commands, and attach evidence for behavior changes. |
| Security | Store secrets outside the repo, validate configuration with `vertx-config` JSON locally, and schedule dependency refreshes using `./mvnw versions:use-latest-releases`. |

Additional conventions for agents are listed below. Architecture and dataflow live in `SPEC.md`.

## OpenAPI Contract Conventions

- Operation IDs must match router names.
- `accountUid` path parameter is mandatory for account-specific routes.
- Colon paths are not allowed with OpenApiRouter; avoid `:` in OpenAPI paths.
- Success codes: `201` for create with response body, `204` for mutations without body, `200` for reads.
- Error response uses `#/components/schemas/Error`.

## DTO/JSON Conventions

- JSON keys use snake_case.
- DTOs expose `toJson()` and build from `JsonObject`.
- `ModifyRequest` fields are operation-specific; reuse is intentional.
- Enumerations serialize as numeric values.

## DAO Conventions

- Named parameters use `p_` prefix (e.g., `p_account_id`).
- Stored procedures are invoked via `CALL ...`.
- `RowMapper` and `TupleMapper` live in `DaoMappers`.

## Service Conventions

- Validate input with `ErrorTickets.checkAnyNotNull`.
- Log entry for each public method.
- Use `getInternal(identity, includeDeleted)` for account existence checks.

## Javadoc

- Keep Javadoc up to date for public classes and methods; add brief summaries for non-obvious helpers.
- Update Javadoc when signatures or behavior changes.

## Prepare for Commit

- Run `./mvnw javadoc:javadoc -B` to validate doclint.
- Run scoped tests for touched modules (e.g., `./mvnw -pl opinion-account test`).

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
