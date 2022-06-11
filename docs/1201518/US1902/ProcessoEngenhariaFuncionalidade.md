# US1902 - As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.
=======================================


# 1. Requisitos

US1902 - As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.

# 2. Dados do Servidor da Order

| Servidor IP                             | Port  | Trusted Store     | Key Store Pass |
|-----------------------------------------|-------|-------------------|----------------|
| **Localhost**: 127.0.0.1<br>**Cloud**:  | 10000 | serverOrder_J.jks | forgotten      |


# 3. Dados do Cliente do Servidor Order

| Port  | Trusted Store     | Key Store Pass |
|-------|-------------------|----------------|
| 10000 | clientOrder_J.jks | forgotten      |


* Todos os clientes registados no sistema usam o mesmo certificado.

# 4. Certificados  

Todos os certificados, tanto do servidor como do cliente, de forma a estabelecer uma conexão TCP de forma segura de acordo com o protocolo SSL/TLS,  são gerados a partir do script do ficheiro make_certs.sh (certificates/make_certs.sh).

