# Proyecto: Aumento de Salario

Este es un proyecto Java que permite gestionar empleados y calcular su aumento de salario. Usa una base de datos MySQL y se ejecuta con Java 17.

---

## 📦 Requisitos

- Java 17
- MySQL (local o remoto)
- IntelliJ IDEA o cualquier IDE compatible
- Conector JDBC de MySQL (`mysql-connector-j-x.x.x.jar`)

---

## 🛠️ Configuración inicial

### 1️⃣ Crear la base de datos

Ejecuta los siguientes comandos en tu cliente de MySQL (como MySQL Workbench, phpMyAdmin o consola):

```sql
CREATE DATABASE empresa;

USE empresa;

CREATE TABLE empleados (
    documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(20),
    tipo VARCHAR(20),
    horas INT,
    salario DOUBLE,
    aumento DOUBLE
);
