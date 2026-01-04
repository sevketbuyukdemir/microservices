[← Return to home](../README.md)

# Local Development

## Running the Application

1. Clone repository

```
git clone https://github.com/sevketbuyukdemir/microservices.git
```

2. Run services via Docker Desktop

- Create and start container for the first time:

```
docker-compose up -d
```

- Start an existing container:

```
docker-compose start
```

- Stop the running container:

```
docker-compose stop
```

- Perform a clean restart (stops containers and removes volumes):

```
docker-compose down -v
```

> [!NOTE]
> Windows NAT issue
> If you face with error like below while running docker compose services in Windows PC,
> ```
> Error response from daemon: Ports are not available: exposing port TCP 0.0.0.0:3888 -> 0.0.0.0:0: listen tcp 0.0.0.0:3888: bind: An attempt was made to access a socket in a way forbidden by its access permissions.
> ```
> Solution:
> 1. Run terminal (cmd) as administrator
> 2. ```net stop WinNat```
> 3. Run docker compose services
> 4. ```net start WinNat```