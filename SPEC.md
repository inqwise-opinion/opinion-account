# Opinion Account Architecture

This document is an abstract, concrete overview of architecture and dataflow for Opinion Account modules.

## Architecture Overview

- **Modules**
  - `opinion-account/`: Vert.x service entry point (HTTP/OpenAPI router, service implementation, DAO mappers).
  - `opinion-account-common/`: DTOs, interfaces, and shared helpers.

- **Layering**
  - **OpenAPI Contract** (`accounts.yaml`) defines paths, operationIds, request/response schemas.
  - **Router** (`AccountOpenApiRouterBuilder`) binds operationIds to handlers.
  - **Service** (`AccountService` + `DefaultAccountService`) implements business operations.
  - **DAO** (`DaoMappers`) holds SQL templates and parameter/row mappers.
  - **DTOs** live in `opinion-account-common` and drive JSON I/O.

## Dataflow (Request → Response)

1. **OpenAPI validation**
   - `RouterBuilder` uses `accounts.yaml` to validate requests and bind to `operationId`.
2. **Router handler**
   - Parses path params and request body into DTOs (`ModifyRequest`, `CreateRequest`, etc.).
   - Constructs `AccountIdentity` from `accountUid` using `Uid.parse`.
3. **Service**
   - Orchestrates validation and DB access.
   - Returns DTOs or simple status codes.
4. **DAO**
   - Uses `SqlTemplate` with named parameters.
   - Maps result rows into DTOs (e.g., `Account`, `AccountBusinessDetails`).

