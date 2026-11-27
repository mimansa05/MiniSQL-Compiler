# MiniSQL – Language Specification

**Project:** Compiler Design – MiniSQL Compiler  
**Version:** 1.0  
**Implementation Language:** Java

## 1. Supported SQL Statements

MiniSQL is a restricted SQL-like language that supports the following statements:

| Statement | Description | Example |
|---|---|---|
| CREATE TABLE | Creates a table with columns and data types | `CREATE TABLE Students (id INT, name TEXT);` |
| INSERT INTO | Defines insertion of values into a table | `INSERT INTO Students VALUES (1, 'Aman');` |
| SELECT | Selects specific or all columns | `SELECT name FROM Students;` |
| UPDATE | Defines modification of column values | `UPDATE Students SET marks = 90 WHERE id = 1;` |
| DELETE | Defines deletion of records using a condition | `DELETE FROM Students WHERE id = 1;` |

All statements must terminate with a semicolon (`;`).

## 2. Keywords

The following reserved keywords are supported:

```text
CREATE
TABLE
INSERT
INTO
VALUES
SELECT
FROM
WHERE
UPDATE
SET
DELETE
INT
FLOAT
TEXT
BOOLEAN
TRUE
FALSE
```

Keywords are case-insensitive.

## 3. Data Types

| Data Type | Description | Example |
|---|---|---|
| INT | Integer values | 10, 100 |
| FLOAT | Decimal values | 10.5, 25.75 |
| TEXT | String values | 'Aman', 'Hello' |
| BOOLEAN | Boolean values | TRUE, FALSE |

## 4. Operators

**Arithmetic Operators**

```text
+  -  *  /
```

**Relational Operators**

```text
=  !=  >  <  >=  <=
```

**Assignment Operator**

```text
=
```

The assignment operator is used in UPDATE statements.

## 5. Separators and Special Symbols

| Symbol | Description |
|---|---|
| `(` | Opening parenthesis |
| `)` | Closing parenthesis |
| `,` | Separates columns and values |
| `;` | Terminates a statement |
| `*` | Selects all columns |
| `.` | Decimal point in floating-point literals |

## 6. Identifiers

Identifiers represent table names and column names.

Examples:

```text
Students
student_id
marks
employeeName
```

Rules:
- Must begin with a letter or underscore.
- May contain letters, digits, and underscores.
- Cannot contain spaces or hyphens.
- Cannot be a reserved keyword.
- Identifiers are case-sensitive.

## 7. Literals

MiniSQL supports the following literal types:

| Type | Examples |
|---|---|
| Integer | `10`, `100`, `-5` |
| Float | `10.5`, `3.14` |
| String | `'Aman'`, `'Hello'` |
| Boolean | `TRUE`, `FALSE` |

String literals must be enclosed in single quotes.

## 8. Comments

Single-line comments begin with `--`.

Example:

```sql
-- Creating a student table
CREATE TABLE Students (id INT, name TEXT);
```

Comments are ignored by the lexical analyzer.

## 9. Language Constraints

- Every SQL statement must end with a semicolon.
- Table and column names must follow identifier rules.
- CREATE TABLE must contain at least one column.
- INSERT values must be separated by commas.
- SELECT must contain a column list or `*`, followed by FROM and a table name.
- WHERE supports comparison-based conditions.
- UPDATE and DELETE require a WHERE condition in Version 1.0.
- Data type compatibility and declaration validity are checked during semantic analysis.

## 10. Scope and Limitations

MiniSQL is a restricted SQL-like language and does not implement the complete SQL standard.

The initial version does not include:
- JOIN operations
- GROUP BY and HAVING
- ORDER BY and LIMIT
- Subqueries
- Combined AND/OR conditions
- Actual database connectivity or query execution

These features may be considered as future enhancements.

## 11. Compiler Input Example

```sql
CREATE TABLE Students (
    id INT,
    name TEXT,
    marks INT
);

INSERT INTO Students VALUES (1, 'Aman', 90);

SELECT name, marks
FROM Students
WHERE marks > 80;
```

The compiler processes this input through lexical analysis, syntax analysis, semantic analysis, intermediate code generation, code optimization, and target code generation.