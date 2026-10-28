# CodeForge · API de Autenticación

API REST encargada de las credenciales: registro de credenciales e inicio de sesión.

**Despliegue:** https://codeforge-autenticacion.onrender.com

## Tecnologías
Java 21 · Spring Boot · Maven · PostgreSQL · Docker

## Endpoints principales
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/auth/login` | Inicio de sesión |
| POST | `/api/v1/auth/internal/credenciales` | Creación de credenciales (uso interno entre APIs) |

