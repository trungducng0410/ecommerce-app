<br/>
<p align="center">
  <h1 align="center">Pokémarket</h1>

  <p align="center">
    A personal project named Pokémarket, an ecommerce website clone to trade pokemon cards.
    <br/>
    <br/>
  </p>
</p>

## About the project
![Home page](https://github.com/user-attachments/assets/f1331bdf-10d5-4f25-9a88-816f2ac82bb9)

![Payment](https://github.com/user-attachments/assets/90f26d31-80ce-4507-bd42-6de0a2c55b2e)

This project is built with <b>Java Spring</b>, <b>ReactJS</b>, <b>MySQL</b> and integrated with <b>Stripe API</b> as payment methodology.


It's an ongoing project with new features and functionalities being added continuously. The end goal is to have a fully functional ecommerce website and practice implementing Spring framework.

## Getting Started

### Prerequisites

* Docker: https://www.docker.com/

### Installation

1. Clone the repo

```sh
git clone https://github.com/trungducng0410/ecommerce-app.git
```

2. Replace your Stripe secret key in [application.properties](backend/src/main/resources/application.properties) file

```
STRIPE_SECRET_KEY=YOUR_STRIPE_SECRET_KEY
```

3. Build application

```sh
docker compose build
```

4. Run application

```sh
docker compose up
```

The front-end app is accessible from http://localhost:8081


