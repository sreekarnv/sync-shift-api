# Sync Shift (API)

This is a project made with [Spring Boot](https://spring.io/), [PostgreSQL](https://www.postgresql.org/) and
[Java](https://www.oracle.com/java/) for the BITS Pilani Course - Object Oriented Programming.
The project is a web application for scheduling slots for BITS Pilani students, staff and booking facilities.
Frontend is made with [React](https://react.dev) and [Typescript](https://typescriptorg.lang) and can be found
[here](https://github.com/sreekarnv/sync-shift-web.git)

<br />

## Problem Statement

Assume that you are required to develop a scheduler application for BITS community members
(student and staff) based on their availability. Members can register as staff/ student roles using
their BITS email ID. The application will have a sign-in page, from where members can register.
Registered members can log in via login page with BITS email and password set during
registration. After logging into the application members can look for the availability of other
community members and facilities. Members can also set their availability.

## Getting Started

Make sure you have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html),
[Intellij IDEA](https://www.jetbrains.com/idea/) or [Spring Initializr](https://start.spring.io/) installed

```bash
git clone https://github.com/sreekarnv/sync-shift-api.git
```

You can install PostgreSQL 15 Locally from [here](https://www.postgresql.org/download/) or connect to a hosting provider
online

Start the application with [Spring Initializr](https://start.spring.io/)
or [Intellij IDEA](https://www.jetbrains.com/idea/).
You can add fake data by running the scripts [users.sql](./sql/users.sql) and [facilities.sql](./sql/facilities.sql).
**The password value for users in [users.sql](./sql/users.sql) is Pass123#**

## Technologies Used

1. [Spring Boot](https://spring.io/)
2. [PostgreSQL](https://www.postgresql.org/)
3. [Java](https://www.oracle.com/java/)